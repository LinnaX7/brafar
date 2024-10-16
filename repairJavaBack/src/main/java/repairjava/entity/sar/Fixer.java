package repairjava.entity.sar;

import com.github.gumtreediff.utils.Pair;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.Node;
import repairjava.entity.cfs.Format;
import repairjava.entity.monitor.JDIDebuggerExecutor;
import repairjava.entity.program.MethodBuilder;
import repairjava.entity.program.ProgramBuilder;
import repairjava.entity.program.TesterBuilder;
import repairjava.entity.program.block.BlockNode;
import repairjava.entity.sar.config.Config;
import repairjava.entity.sar.config.ConfigBuilder;
import repairjava.entity.sar.repair.Repair;
import repairjava.entity.sar.repair.RepairMatch;
import repairjava.entity.sar.repair.utils.RepairUtils;
import repairjava.entity.variables.Variable;
import repairjava.entity.variables.VariableMatch;
import repairjava.utils.RepairResult;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Fixer {
    protected ProgramBuilder buggyProgram;
    protected ProgramBuilder correctProgram;
    protected TesterBuilder testerProgram;
    protected String methodToFix;

    protected List<String> failedTesterMethods = new ArrayList<>();
    public static final String LOG_PATH = Paths.get(Executor.ROOT_PATH,"tmp", "log.log").toString();
    public static final String EXECUTE_LOG_PATH = Paths.get(Executor.ROOT_PATH,"tmp", "execute.log").toString();
    public static final String COMPILE_LOG_PATH = Paths.get( Executor.ROOT_PATH,"tmp", "compile.log").toString();
    private TestResult fixResult;
    public static final int MAX_PATCHES = 35;


    public TestResult getFixResult() {
        return fixResult;
    }

    public void format(ProgramBuilder pb, ProgramBuilder tb) throws IOException {
        Format.formatCompositeNode(pb.getCompilationUnit());
        Format.formatCompositeNode(tb.getCompilationUnit());
        pb.toPath(Paths.get(Executor.ROOT_PATH, "tmp","format","wrong","src").toString());
        tb.toPath(Paths.get(Executor.ROOT_PATH,"tmp","format","correct","src").toString());
    }

    /*没有正确代码中的引入则加入引用*/
    public void alignImport(){
        for(Map.Entry<String, ImportDeclaration> entry:correctProgram.getImportDeclarationMap().entrySet()){
            if(!buggyProgram.getImportDeclarationMap().containsKey(entry.getKey())){
                buggyProgram.getCompilationUnit().addImport(entry.getValue().clone());
            }
        }
    }

    public boolean isCompileError() throws IOException {
        if(new File(COMPILE_LOG_PATH).exists()) {
            FileInputStream fStream = new FileInputStream(COMPILE_LOG_PATH);
            BufferedReader br = new BufferedReader(new InputStreamReader(fStream,"GBK"));
            String line = br.readLine();
            while(line!=null) {
                if(line.contains("错误")||line.contains("error")) {
                    return true;
                }
                line = br.readLine();
            }
            fStream.close();
            br.close();
        }
        return false;
    }

    public static void clearInfoForFile(String fileName) {
        File file =new File(fileName);
        try {
            if(!file.exists()) {
                return;
            }
            FileWriter fileWriter =new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<RepairResult> execute(Config config) throws Exception{
        List<RepairResult> repairResultList = new ArrayList<>();

        buggyProgram = config.getBuggyProgram();
        correctProgram = config.getCorrectProgram();
        testerProgram = config.getTesterProgram();
        methodToFix = config.getFaultyMethodSignature();

        /*加入方法集*/
        buggyProgram.initMethodBuilder(config.getFaultyMethodSignature());
        correctProgram.initMethodBuilder(config.getFaultyMethodSignature());
        /*加入引用*/
        alignImport();


        JDIDebuggerExecutor.preCompile();//编译相关class
        JDIDebuggerExecutor.compileTest(buggyProgram, testerProgram);//编译testee和tester
        JDIDebuggerExecutor.executeAllTest(buggyProgram, testerProgram, methodToFix);//执行JDID

        initFailedTesterMethods();//找出错误测试点

        int patch = 1;
        boolean afl_stop=false;
        ArrayList<Integer>fixed=new ArrayList<>();

        System.out.println("--------------------------------------Init Code----------------------------------------");
        System.out.println(buggyProgram.getCompilationUnit());
        System.out.println();
        this.fixResult=new TestResult(failedTesterMethods.size()==0);
        while(failedTesterMethods.size()!=0) {
            if (patch > MAX_PATCHES || afl_stop) {
                break;
            }
            for (String failedTesterMethod : failedTesterMethods) {
                /*测试正确程序*/
                JDIDebuggerExecutor.compileTest(correctProgram, testerProgram);
                JDIDebuggerExecutor.executeTest(correctProgram, testerProgram, methodToFix, failedTesterMethod);//单独测试

                clearBlockValue(correctProgram);
                initBlockValue(correctProgram);

                do {
                    /*测试错误程序*/
                    JDIDebuggerExecutor.compileTest(buggyProgram, testerProgram);
                    if(!isCompileError()) {
                        JDIDebuggerExecutor.executeTest(buggyProgram, testerProgram, methodToFix, failedTesterMethod);
                    }else{
                        clearInfoForFile(LOG_PATH);
                        clearInfoForFile(EXECUTE_LOG_PATH);
                    }

                    clearBlockValue(buggyProgram);
                    initBlockValue(buggyProgram);

                    TestResult testResult= getTestResult();

                    if (testResult.getResult()) {
                        break;
                    }
                    if (patch > MAX_PATCHES||afl_stop) {
                        break;
                    }
                    //Repair
                    System.out.println("-----------------------------------------------------Repair " + patch +
                            "------------------------------------------------------");
                    RepairResult repairResult = new RepairResult();

                    MethodBuilder buggyM = buggyProgram.getMethodByName(methodToFix);
                    MethodBuilder correctM = correctProgram.getMethodByName(methodToFix);
                    repairResult.setWrongCode(buggyProgram.getCompilationUnit().toString());
                    repairResult.setCorrectCode(correctProgram.getCompilationUnit().toString());
                    for (BlockNode blockNode : buggyM.getMetaBlockNodes()){
                        repairResult.addWrongBlock(blockNode.getBlockType().toString(), blockNode.getLineCodes());
                    }

                    for (BlockNode blockNode : correctM.getMetaBlockNodes()){
                        repairResult.addCorrectBlock(blockNode.getBlockType().toString(), blockNode.getLineCodes());
                    }

                    if(testResult.isExcept()){
                        testResult.getException().setLocation(Integer.parseInt(buggyM.getBreakPointLines().get(buggyM.getBreakPointLines().size()-1)));
                    }
                    VariableMatch variableMatch = new VariableMatch(buggyM, correctM);//变量匹配
                    for (Map.Entry<Variable, Variable> entry : variableMatch.getB2cMatch().entrySet()){
                        Variable wrongVariable = entry.getKey();
                        Variable correctVariable = entry.getValue();
                        repairResult.addWrongVar(wrongVariable.getType().toString(), wrongVariable.getName(), wrongVariable.getRange(), wrongVariable.specialType.toString());
                        repairResult.addCorrectVar(correctVariable.getType().toString(), correctVariable.getName(), correctVariable.getRange(), correctVariable.specialType.toString());
                    }
                    repairResult.setVarMatchedCode(buggyProgram.getCompilationUnit().toString());

                    for (BlockNode blockNode : buggyM.getBlockBuilder().getBlockNodes()){
                        repairResult.addTreeNodes(blockNode);
                    }


                    AFLW afl = new AFLW(buggyM, correctM, variableMatch, fixed);
                    Repair repair = new Repair(buggyM, correctM, variableMatch, testResult.getException());
                    if (afl.isNeedStop()) {
                        afl_stop = true;
                        RepairUtils.changeFloatToDouble(repair.getCommon());
                        RepairUtils.castReturnExp(repair.getCommon());
                        repairResult.setBeforeRepairingCode(buggyProgram.getCompilationUnit().toString());
                    }else {
                        /*从定位到的错误位置开始修复*/
                        dfsFaultBlock(afl.getFaultBlock(), repairResult);
                        repair.execRepair(afl.getFaultBlock(), fixed);
                        repairResult.setBeforeRepairingCode(repair.getBeforeRepairingCode());
                        for (RepairMatch repairMatch : repair.getRepairMatchList()) {
                            for (Pair<Node, Node> pair : repairMatch.getReplaceList()) {
                                repairResult.addReplaceMatchs(pair.first, pair.second);
                            }

                            for (Node node : repairMatch.getInsertList()) {
                                repairResult.addInsertMatchs(node.toString(), node.getRange());
                            }

                            for (Node node : repairMatch.getDeleteList()) {
                                repairResult.addDeleteMatchs(node.toString(), node.getRange());
                            }
                        }
                        for (Node node : repair.getRepairReorder().getReorderList()) {
                            repairResult.addReorderMatchs(node.toString(), node.getRange());
                        }
                    }
                    String patchPath = Paths.get(Executor.ROOT_PATH, "tmp", String.format("patch%03d", patch),
                            "src").toString();
                    buggyProgram.toPath(patchPath);

                    /*构建下一次修复*/
                    patch += 1;
                    buggyProgram = ConfigBuilder.getProgram(config, patchPath);
                    buggyProgram.initMethodBuilder(config.getFaultyMethodSignature());

                    System.out.println("--------------------------------------Repair Result----------------------------------------");
                    System.out.println(buggyProgram.getCompilationUnit());
                    System.out.println();
                    repairResult.setRepairedCode(buggyProgram.getCompilationUnit().toString());
                    repairResultList.add(repairResult);

                } while (true);
                if(getTestResult().getResult()){
                    continue;
                }
                if (patch > MAX_PATCHES || afl_stop) {
                    break;
                }
            }
            /*再次测试*/
            JDIDebuggerExecutor.compileTest(buggyProgram, testerProgram);
            JDIDebuggerExecutor.executeAllTest(buggyProgram, testerProgram, methodToFix);
            initFailedTesterMethods();
        }
        System.out.println("----------------------------------------------------------------------------------------");
        this.fixResult.setResult(failedTesterMethods.size()==0);
        System.out.println("Result:"+(this.fixResult.getResult()));
        return repairResultList;
    }

    public void initBlockValue(ProgramBuilder pb) throws IOException {
        pb.getMethodByName(methodToFix).setVariableValues(EXECUTE_LOG_PATH, pb.getClassName());
    }

    public void clearBlockValue(ProgramBuilder pb){
        pb.getMethodByName(methodToFix).clearBlocksBLP();
    }

    /*从log.log中获取测试信息*/
    public void initFailedTesterMethods() throws IOException {
        failedTesterMethods.clear();
        if(new File(LOG_PATH).exists()) {
            FileInputStream fStream = new FileInputStream(LOG_PATH);
            BufferedReader br = new BufferedReader(new InputStreamReader(fStream));
            String line = br.readLine();
            while (line != null) {
                if(line.contains("(")){
                    failedTesterMethods.add(line.substring(0, line.indexOf("(")));
                }
                line = br.readLine();
            }
            fStream.close();
            br.close();
        }
    }

    private void dfsFaultBlock(BlockNode faultBlock, RepairResult repairResult){
        if (faultBlock.getMetaIndex() == -1){
            for (BlockNode childBlockNode: faultBlock.getChildBlocks()) {
                dfsFaultBlock(childBlockNode, repairResult);
            }
        }
        else {
            repairResult.setTreeNodesFault(faultBlock.getMetaIndex());
        }
    }

    public TestResult getTestResult()throws IOException {
        return new TestResult(LOG_PATH);
    }
}