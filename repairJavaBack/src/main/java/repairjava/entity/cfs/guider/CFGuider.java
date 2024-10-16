package repairjava.entity.cfs.guider;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;;
import repairjava.entity.cfs.guider.mapping.LoopMapping;
import repairjava.entity.cfs.guider.mapping.RoughMapping;
import repairjava.entity.program.ProgramBuilder;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class CFGuider {

    public static int K = 5;

    public static ArrayList<CFBuilder> findKthClosestPrograms(ProgramBuilder buggyP, ArrayList<ProgramBuilder> correctPs, String methodName){
        ArrayList<CFBuilder> kthCorrectPs = new ArrayList<>();
        Set<CFBuilder> sortedCorrectPs = new TreeSet<>();
        CSNode buggyCS = new CSNode(buggyP.getTargetMethodDeclaration(methodName));
        for(ProgramBuilder correctP:correctPs) {
            CFBuilder cfBuilder = new CFBuilder(correctP, methodName);
            LoopMapping loopMapping = new LoopMapping(buggyCS, cfBuilder.getCsNode());
            loopMapping.mapping();
            cfBuilder.setLoopPair(loopMapping.getMappingScore());
            RoughMapping roughMapping = new RoughMapping(buggyCS, cfBuilder.getCsNode());
            roughMapping.mapping();
            cfBuilder.setNodesCount(roughMapping.getDstNodes().size());
            cfBuilder.setRoughPair(roughMapping.getMappingScore());
            sortedCorrectPs.add(cfBuilder);
        }
        for(CFBuilder cfBuilder:sortedCorrectPs){
            kthCorrectPs.add(cfBuilder);
            if(kthCorrectPs.size()==K){
                break;
            }

        }
        return kthCorrectPs;
    }

    public static CompilationUnit getRefactoredCM(ProgramBuilder pb, MethodDeclaration newMD){
        assert newMD.getBody().isPresent();
        CompilationUnit refactoredCM = pb.getCompilationUnit().clone();
        for(MethodDeclaration methodDeclaration:refactoredCM.findAll(MethodDeclaration.class)){
            if(methodDeclaration.getName().getIdentifier().equals("method")){
                methodDeclaration.setBody(newMD.getBody().get());
            }
        }
        return refactoredCM;
    }

    public static Map<String, Object> controlFlowGuide(String wrongFileFolderPath, String correctFileFolderPath, String className, String methodName) throws IOException {
        Map<String, Object> map = new HashMap<>();

        ProgramBuilder wrongProgramBuilder = new ProgramBuilder(wrongFileFolderPath, className);
        ProgramBuilder correctProgramBuilder = new ProgramBuilder(correctFileFolderPath, className);
        MethodDeclaration buggyMethond = wrongProgramBuilder.getTargetMethodDeclaration(methodName);
        MethodDeclaration correctMethod = correctProgramBuilder.getTargetMethodDeclaration(methodName);

        EditScript editScript = new EditScript(buggyMethond, correctMethod);

        map.put("wrongLegalMappingControlStructure", editScript.getSrcLegalMappingControlStructureResult());
        map.put("correctLegalMappingControlStructure", editScript.getDstLegalMappingControlStructureResult());
        map.put("wrongMainRefactorControlStructure", editScript.getSrcMainRefactorControlStructureResult());
        map.put("correctMainRefactorControlStructure", editScript.getDstMainRefactorControlStructureResult());
        map.put("wrongLocalMappingControlStructure", editScript.getSrcLocalMappingControlStructureResult());
        map.put("correctLocalMappingControlStructure", editScript.getDstLocalMappingControlStructureResult());
        map.put("wrongFinalRefactorControlStructure", editScript.getSrcFinalRefactorControlStructureResult());
        map.put("correctFinalRefactorControlStructure", editScript.getDstFinalRefactorControlStructureResult());

        alignImport(wrongProgramBuilder, correctProgramBuilder);

        CompilationUnit refactoredWrongCM = getRefactoredCM(wrongProgramBuilder, buggyMethond);
        CompilationUnit refactoredCorrectCM = getRefactoredCM(correctProgramBuilder, correctMethod);
        wrongProgramBuilder.toPath(Paths.get(wrongFileFolderPath.substring(0, wrongFileFolderPath.lastIndexOf('/')),"refactoredWrong").toString(), refactoredWrongCM);
        correctProgramBuilder.toPath(Paths.get(wrongFileFolderPath.substring(0, wrongFileFolderPath.lastIndexOf('/')),"refactoredCorrect").toString(), refactoredCorrectCM);

        return map;
    }

    public static void alignImport(ProgramBuilder wrongProgramBuilder, ProgramBuilder correctProgramBuilder){
        for(Map.Entry<String, ImportDeclaration> entry:correctProgramBuilder.getImportDeclarationMap().entrySet()){
            if(!wrongProgramBuilder.getImportDeclarationMap().containsKey(entry.getKey())){
                wrongProgramBuilder.getCompilationUnit().addImport(entry.getValue().clone());
            }
        }
    }

}
