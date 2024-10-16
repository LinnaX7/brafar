package repairjava.entity.cfs.guider;

import com.github.javaparser.ast.body.MethodDeclaration;
import repairjava.entity.cfs.guider.action.RefactorAction;
import repairjava.entity.cfs.guider.action.RefactorInsert;
import repairjava.entity.cfs.guider.action.RefactorMove;
import repairjava.entity.cfs.guider.mapping.LegalMapping;
import repairjava.entity.cfs.guider.mapping.LocalMapping;
import repairjava.utils.ControlStructureResult;

import java.util.ArrayList;
import java.util.HashMap;

public class EditScript {

    ArrayList<RefactorAction> srcRefactorActions = null;
    ArrayList<RefactorAction> dstRefactorActions = null;
    int dstEdit = 0;
    int srcEdit = 0;
    ArrayList<String> guidance;
    boolean flag;
    LegalMapping legalMapping;
    LocalMapping localMapping;
    ControlStructureResult srcLegalMappingControlStructureResult;
    ControlStructureResult dstLegalMappingControlStructureResult;

    ControlStructureResult srcMainRefactorControlStructureResult;
    ControlStructureResult dstMainRefactorControlStructureResult;

    ControlStructureResult srcLocalMappingControlStructureResult;
    ControlStructureResult dstLocalMappingControlStructureResult;

    ControlStructureResult srcFinalRefactorControlStructureResult;
    ControlStructureResult dstFinalRefactorControlStructureResult;

    public EditScript(MethodDeclaration buggyMethod, MethodDeclaration correctMethod){
        this.flag = true;
        this.guidance = new ArrayList<>();
        initLegalMapping(buggyMethod, correctMethod);
        initLegalMappingControlStructureResult();
        /*如果不存在非匹配的for节点则进行重构*/
        refactoringGuide();
    }

    private void initLegalMapping(MethodDeclaration buggyMethod, MethodDeclaration correctMethod){
        CSNode buggyMNode = new CSNode(CSNode.getBlockNodeFromFW(buggyMethod), 0, CSNode.CSType.METHOD_DECLARATION, null, 0);
        CSNode correctMNode = new CSNode(CSNode.getBlockNodeFromFW(correctMethod), 0, CSNode.CSType.METHOD_DECLARATION, null, 0);

        /*完成匹配*/
        legalMapping = new LegalMapping(buggyMNode, correctMNode);
        legalMapping.mapping();

        /*找到并交换if分支*/
        ArrayList<CSNode> ifNodesToBeChanged = LegalMapping.getIfCSNodeToChangeBranch(legalMapping.getSrcToDst());
        for(CSNode ifCSNode:ifNodesToBeChanged){
            ifCSNode.branchChange();
        }

        /*再次匹配*/
        if (ifNodesToBeChanged.size() != 0){
            legalMapping = new LegalMapping(buggyMNode, correctMNode);
            legalMapping.mapping();
        }
    }

    private void initLocalMapping(){
        localMapping = new LocalMapping(legalMapping.getSrc(), legalMapping.getDst(), legalMapping);

        ArrayList<CSNode> ifNodesToBeChanged = localMapping.getAllIfNodesToBeChanged();
        for(CSNode ifCSNode:ifNodesToBeChanged){
            ifCSNode.branchChange();
        }

        /*再次匹配*/
        if (ifNodesToBeChanged.size() != 0){
            localMapping = new LocalMapping(legalMapping.getSrc(), legalMapping.getDst(), legalMapping);
        }
    }

    /*单向重构,插入if分支节点*/
    public ArrayList<RefactorAction> unidirectionalRefactor(ArrayList<CSNode> srcNodes, HashMap<CSNode, CSNode> srcToDst, HashMap<CSNode,CSNode> dstToSrc){
        ArrayList<RefactorAction> refactorActions = new ArrayList<>();
        for (CSNode srcNode : srcNodes){
            if(srcToDst.get(srcNode) == null){
                CSNode dstNodeP = srcToDst.get(srcNode.getParent());
                if(dstNodeP != null){
                    switch (srcNode.getCsType()){
                        case IF_STMT:
                        case WHILE_STMT:
                        case FOR_STMT:
                        case FOREACH_STMT:
                            RefactorInsert ifRefactorInsert = new RefactorInsert(dstNodeP, srcNode, dstToSrc, srcToDst);
                            refactorActions.add(ifRefactorInsert);
                            break;
                        case ELSE_BRANCH:
                        case THEN_BRANCH:
                            RefactorInsert thenRefactorInsert = new RefactorInsert(dstNodeP, srcNode, dstToSrc, srcToDst);
                            new RefactorMove(thenRefactorInsert.getNewCSNode(), srcNode, srcToDst);
                            break;
                        default:
                            flag = false;
                    }
                }
            }
            if(!flag) {
                return refactorActions;
            }
        }
        return refactorActions;
    }

    private void refactoringGuideLocalMapping(){
        /*局部重构*/
        this.dstRefactorActions.addAll(unidirectionalRefactor(localMapping.getSrcNodes(), legalMapping.getSrcToDst(), legalMapping.getDstToSrc()));
        if(!flag) {
            return;
        }
        this.srcRefactorActions.addAll(unidirectionalRefactor(localMapping.getDstNodes(), legalMapping.getDstToSrc(), legalMapping.getSrcToDst()));
    }

    private void refactoringGuide(){
        /*目的主控制结构操作*/
        this.dstRefactorActions = unidirectionalRefactor(legalMapping.getSrcCSNodes(), legalMapping.getSrcToDst(), legalMapping.getDstToSrc());
        if(!flag) {
            initMainRefactorControlStructureResult();
            initLocalMappingControlStructureResult();
            initFinalRefactorControlStructureResult();
            return;
        }
        /*源主控制结构操作*/
        this.srcRefactorActions = unidirectionalRefactor(legalMapping.getDstCSNodes(), legalMapping.getDstToSrc(), legalMapping.getSrcToDst());
        if(!flag) {
            initMainRefactorControlStructureResult();
            initLocalMappingControlStructureResult();
            initFinalRefactorControlStructureResult();
            return;
        }

        initMainRefactorControlStructureResult();

        initLocalMapping();

        initLocalMappingControlStructureResult();

        refactoringGuideLocalMapping();

        initFinalRefactorControlStructureResult();
        if(!flag) {
            return;
        }


        this.srcEdit = this.srcRefactorActions.size();
        this.dstEdit = this.dstRefactorActions.size();
    }

    public void initLegalMappingControlStructureResult(){
        srcLegalMappingControlStructureResult = initSrcControlStructureResult(legalMapping.getSrc());
        dstLegalMappingControlStructureResult = initDstControlStructureResult(legalMapping.getDst());
    }

    public void initLocalMappingControlStructureResult(){
        legalMapping.reDFSNode();
        srcLocalMappingControlStructureResult = initSrcControlStructureResult(legalMapping.getSrc());
        dstLocalMappingControlStructureResult = initDstControlStructureResult(legalMapping.getDst());
    }

    public void initMainRefactorControlStructureResult(){
        legalMapping.reDFSNode();
        srcMainRefactorControlStructureResult = initSrcControlStructureResult(legalMapping.getSrc());
        dstMainRefactorControlStructureResult = initDstControlStructureResult(legalMapping.getDst());
    }

    public void initFinalRefactorControlStructureResult(){
        legalMapping.reDFSNode();
        srcFinalRefactorControlStructureResult = initSrcControlStructureResult(legalMapping.getSrc());
        dstFinalRefactorControlStructureResult = initDstControlStructureResult(legalMapping.getDst());
    }

    public ControlStructureResult initSrcControlStructureResult(CSNode node){
        String id = String.format("10%02d", legalMapping.getSrcNodesIndexes().get(node));
        String type = node.getCsType().toString();
        boolean controlStructure = legalMapping.getSrcCSNodes().contains(node);
        boolean insert = node.isNew();
        boolean move = node.isMove();
        CSNode matchNode = legalMapping.getSrcToDst().containsKey(node) ? legalMapping.getSrcToDst().get(node) : null;
        String matchId = matchNode != null ? String.format("20%02d", legalMapping.getDstNodesIndexes().get(matchNode)) : "-1";

        ControlStructureResult srcControlStructureResult = new ControlStructureResult(id, type, controlStructure, insert, move, matchId);
        for (CSNode child : node.getChildren()){
            srcControlStructureResult.addChild(initSrcControlStructureResult(child));
        }
        return srcControlStructureResult;
    }

    public ControlStructureResult initDstControlStructureResult(CSNode node){
        String id = String.format("20%02d", legalMapping.getDstNodesIndexes().get(node));
        String type = node.getCsType().toString();
        boolean controlStructure = legalMapping.getDstCSNodes().contains(node);
        boolean insert = node.isNew();
        boolean move = node.isMove();
        CSNode matchNode = legalMapping.getDstToSrc().containsKey(node) ? legalMapping.getDstToSrc().get(node) : null;
        String matchId = matchNode != null ? String.format("10%02d",legalMapping.getSrcNodesIndexes().get(matchNode)) : "-1";

        ControlStructureResult dstControlStructureResult = new ControlStructureResult(id, type, controlStructure, insert, move, matchId);
        for (CSNode child : node.getChildren()){
            dstControlStructureResult.addChild(initDstControlStructureResult(child));
        }
        return dstControlStructureResult;
    }

    public ControlStructureResult getSrcLegalMappingControlStructureResult(){
        return srcLegalMappingControlStructureResult;
    }

    public ControlStructureResult getDstLegalMappingControlStructureResult(){
        return dstLegalMappingControlStructureResult;
    }

    public ControlStructureResult getSrcLocalMappingControlStructureResult(){
        return srcLocalMappingControlStructureResult;
    }

    public ControlStructureResult getDstLocalMappingControlStructureResult(){
        return dstLocalMappingControlStructureResult;
    }

    public ControlStructureResult getSrcMainRefactorControlStructureResult(){
        return srcMainRefactorControlStructureResult;
    }

    public ControlStructureResult getDstMainRefactorControlStructureResult(){
        return dstMainRefactorControlStructureResult;
    }

    public ControlStructureResult getSrcFinalRefactorControlStructureResult(){
        return srcFinalRefactorControlStructureResult;
    }

    public ControlStructureResult getDstFinalRefactorControlStructureResult(){
        return dstFinalRefactorControlStructureResult;
    }

}

