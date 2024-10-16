package repairjava.entity.evaluate;

import com.github.gumtreediff.tree.TreeContext;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.google.gson.Gson;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.io.FileUtils;
import repairjava.entity.ast.AstDiff;
import repairjava.entity.ast.JdtTreeContext;
import repairjava.entity.cfs.CFSVisitor;
import repairjava.entity.cfs.Format;
import repairjava.entity.cfs.guider.CFBuilder;
import repairjava.entity.program.ProgramBuilder;
import repairjava.entity.sar.Fixer;
import repairjava.entity.sar.config.CmdOptions;
import repairjava.entity.sar.config.Config;
import repairjava.entity.sar.config.ConfigBuilder;
import repairjava.entity.variables.VariableMatch;
import repairjava.utils.RepairResult;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static repairjava.entity.cfs.guider.CFGuider.K;
import static repairjava.entity.cfs.guider.CFGuider.findKthClosestPrograms;
import static repairjava.entity.sar.Main.parseCommandLine;

public class Evaluate {

    public static ArrayList<ProgramBuilder> getProgramsFromFolder(String folderPath, String className, String method) throws IOException {
        ArrayList<ProgramBuilder> programs = new ArrayList<>();
        File folder = new File(folderPath);
        if (!folder.isDirectory() || Objects.requireNonNull(folder.listFiles()).length == 0) {
            System.out.printf("Give a wrong folder to get programs of %s!\n", folderPath);
            return programs;
        }
        File[] files = folder.listFiles();
        if(files == null) {
            return programs;
        }
        for (File file : files) {
            if (file.getPath().contains(".DS_Store") ||file.getPath().contains(".idea")) {
                continue;
            }
            String classPath = Paths.get(file.getPath()).toString();

            ProgramBuilder program = new ProgramBuilder(classPath, className);
            MethodDeclaration targetMethodD = program.getMethodDeclarationMap().get(method);
            if(targetMethodD == null) {
                System.out.printf("error parser file %s%n", file.getPath());
            }
            else {
                CFSVisitor newCFS = new CFSVisitor(targetMethodD);
                program.setMethodToFixCFS(newCFS);
                program.initMethodBuilder(method);
                programs.add(program);
            }
        }
        return programs;
    }

    public static String formatFile(String dirPath, String className, String method) throws IOException{
        ProgramBuilder program = new ProgramBuilder(dirPath, className);
        MethodDeclaration targetMethodD = program.getMethodDeclarationMap().get(method);
        Format.formatProgram(targetMethodD);
        program.toPath(Paths.get(dirPath, "formated").toString());
        return dirPath + "/formated";
    }

    public static JdtTreeContext getClosestJdtTree(TreeContext src, List<JdtTreeContext> jdtTreeContexts, String methodName) {
        double dist = 10000000;
        if (jdtTreeContexts.size() == 0) {
            return null;
        }
        JdtTreeContext tempJdtTree = jdtTreeContexts.get(0);
        for (JdtTreeContext jdtDst : jdtTreeContexts) {
            double temp = AstDiff.myCompute(src, jdtDst.getTreeContext()).astSimilarity(methodName);
            if (temp < dist) {
                dist = temp;
                tempJdtTree = jdtDst;
            }
        }
        return tempJdtTree;
    }

    public static String searchByCFSandAST(String wrongFileDirPath, String referenceFilesDirPath, String className, String methodName) throws IOException{

        ProgramBuilder wrongProgramBuilder = new ProgramBuilder(wrongFileDirPath, className);
        MethodDeclaration targetMethodD = wrongProgramBuilder.getMethodDeclarationMap().get(methodName);
        CFSVisitor newCFS = new CFSVisitor(targetMethodD);
        wrongProgramBuilder.setMethodToFixCFS(newCFS);
        wrongProgramBuilder.initMethodBuilder(methodName);

        ArrayList<ProgramBuilder> correctPs = getProgramsFromFolder(referenceFilesDirPath, className, methodName);

        ArrayList<CFBuilder> kthClosestPrograms = findKthClosestPrograms(wrongProgramBuilder, correctPs, methodName);
        JdtTreeContext wrongJdt = new JdtTreeContext(wrongProgramBuilder.getClassPath(), className + ".java", wrongProgramBuilder.getClassPath(), className);
        ArrayList<JdtTreeContext> kthClosestJdTrees = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            kthClosestJdTrees.add(new JdtTreeContext(kthClosestPrograms.get(i).getProgramBuilder().getClassPath(), className + ".java", kthClosestPrograms.get(i).getProgramBuilder().getClassPath(), className));
        }
        JdtTreeContext closestJdtTree = getClosestJdtTree(wrongJdt.getTreeContext(), kthClosestJdTrees, methodName);
        return closestJdtTree.getClassPath();
    }

    public static Config setConfig(String buggySourceDir, String correctSourceDir, String methodToFix, String testSourceDir, String testClass) throws Exception{
        if (new File("tmp").exists()) {
            FileUtils.cleanDirectory(new File("tmp"));
        }

        /*命令行参数 错误文件夹；正确文件夹；待修复方法（方法名+类名）；测试文件夹；测试类*/
        String[] cmdArgs = String.format("--BuggyProgramSourceDir %s --CorrectProgramSourceDir %s " +
                "--MethodToFix %s --ProgramTestSourceDir %s --ProgramTestClass %s", buggySourceDir, correctSourceDir, methodToFix, testSourceDir, testClass).split(" ");

        CommandLine commandLine = parseCommandLine(CmdOptions.getCmdOptions(), cmdArgs);//产生命令行
        /*配置fixMethod 和 correct、wrong、test program*/
        ConfigBuilder configBuilder = new ConfigBuilder();
        configBuilder.buildConfig(commandLine);

        return configBuilder.getConfig();
    }

    public static boolean repair(Config config, String filePath) throws Exception {

        if (config != null) {
            Fixer fixer = new Fixer();
            writeRepairResult(fixer.execute(config), filePath);
            VariableMatch.clearPreMatch();
            return fixer.getFixResult().getResult();
        }
        return false;
    }

    public static void writeRepairResult(List<RepairResult> repairResultList, String filePath)throws Exception{
        String repairResultJSON = new Gson().toJson(repairResultList);
        FileWriter fileWriter = new FileWriter(new File(filePath));
        fileWriter.write(repairResultJSON);
        fileWriter.flush();
        fileWriter.close();
    }

}
