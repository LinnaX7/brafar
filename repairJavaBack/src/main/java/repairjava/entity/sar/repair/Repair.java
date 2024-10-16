package repairjava.entity.sar.repair;

import com.github.gumtreediff.utils.Pair;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.VariableDeclarator;
import repairjava.entity.program.MethodBuilder;
import repairjava.entity.program.block.BlockNode;
import repairjava.entity.sar.TestResult;
import repairjava.entity.sar.repair.utils.Common;
import repairjava.entity.sar.repair.utils.RepairUtils;
import repairjava.entity.variables.GraphAdjacencyMatrix;
import repairjava.entity.variables.Variable;
import repairjava.entity.variables.VariableDependency;
import repairjava.entity.variables.VariableMatch;

import java.util.ArrayList;
import java.util.List;

public class Repair {
    Common common;
    RepairMatch repairMatch;
    List<RepairMatch> repairMatchList;
    RepairReorder repairReorder;
    String beforeRepairingCode;

    TestResult.JavaException javaException;
    public Repair(MethodBuilder buggyM, MethodBuilder correctM, VariableMatch variableMatch, TestResult.JavaException javaException) {
        common = new Common(buggyM,correctM,variableMatch);
        this.javaException = javaException;
        repairMatchList = new ArrayList<>();
    }

    public void match(){
        repairMatch =new RepairMatch(common);
        repairMatch.match();
        repairMatchList.add(repairMatch);
    }

    public void replace(){
        BlockNode buggyBlock=common.buggyMethodBuilder.getMetaBlockNodes().get(common.blockIndex.peek());
        for(Pair<Node,Node> pair: repairMatch.getReplaceList()){
            RepairReplace repairReplace =new RepairReplace(common);
            repairReplace.replaceNode(buggyBlock.getParentNode(),pair.first,pair.second);
        }
    }

    public void insert(){
        BlockNode buggyBlock=common.buggyMethodBuilder.getMetaBlockNodes().get(common.blockIndex.peek());
        for(Node node: repairMatch.getInsertList()){
            Node clone=node.clone();
            RepairInsert repairInsert = new RepairInsert(common);
            repairInsert.repairInsert(buggyBlock.getParentNode(),clone,node);
        }
    }

    public void delete() {
        BlockNode buggyBlock = common.buggyMethodBuilder.getMetaBlockNodes().get(common.blockIndex.peek());
        ArrayList<Node> defList = new ArrayList<>();
        for(Node node: repairMatch.getDeleteList()) {
            if (!RepairUtils.isDef(node)) {
                RepairDelete repairDelete =new RepairDelete(common);
                repairDelete.deleteNode(buggyBlock.getParentNode(), node);
            }else{
                defList.add(node);
            }
        }
        if(defList.isEmpty()) {
            return;
        }
        for (int i = defList.size() - 1; i >= 0; --i) {
            Node node = defList.get(i);
            List<VariableDeclarator> def = RepairUtils.getVariableDecl(node);
            for (int j = def.size() - 1; j >= 0; j--) {
                VariableDeclarator v = def.get(j);
                GraphAdjacencyMatrix matrix;
                if (buggyBlock.getParentBlock() == null) {
                    matrix = VariableDependency.getGraphAdjacencyMatrixFromMethodD(common.buggyMethodBuilder.getMethodDeclaration(), common.buggyVariableBuilder);
                } else {
                    matrix = VariableDependency.getGraphAdjacencyMatrixFromBlock(buggyBlock.getParentBlock(), common.buggyVariableBuilder);
                }
                if (v.getInitializer().isPresent()) {
                    Variable b = common.buggyVariableBuilder.getVariable(v.getName().getIdentifier(),
                            common.buggyMethodBuilder.getMetaBlockNodes().get(common.blockIndex.peek()).getParentNode());
                    if (matrix.getBeRelied(common.buggyVariableBuilder.getIndex(b)).size() == 0) {
                        if (def.size() == 1) {
                            RepairDelete repairDelete = new RepairDelete(common);
                            repairDelete.deleteNode(buggyBlock.getParentNode(), node);
                        } else {
                            if (v.getParentNode().isPresent()) {
                                v.getParentNode().get().remove(v);
                            }
                        }
                    }
                    else if(javaException!=null){
                        boolean flag = false;
                        if(node.getBegin().isPresent()) {
                            if(javaException.getLocation()==node.getBegin().get().line){
                                flag = true;
                            }
                            else if (javaException.getLocation()>node.getBegin().get().line){
                                if(node.getEnd().isPresent()){
                                    if (javaException.getLocation()<=node.getEnd().get().line) {
                                        flag = true;
                                    }
                                }
                            }
                        }
                        if(flag){
                            if(b.getType().isPrimitiveType()) {
                                if (b.getType().asPrimitiveType().getType().ordinal() == 0) {
                                    v.setInitializer("false");
                                }
                                else {
                                    v.setInitializer("0");
                                }
                            }
                            else {
                                v.setInitializer("null");
                            }
                        }
                    }
                }
            }
        }
    }
    public void repair(int index){
        common.blockIndex.push(index);

        /*语句匹配*/
        match();

        insert();
        replace();
        delete();

    }
    public void repair(BlockNode blockNode, ArrayList<Integer> fixed){
        if (blockNode==null){
            return;
        }
        /*从基本块开始修复*/
        if (blockNode.getMetaIndex() != -1){
            repair(blockNode.getMetaIndex());
            fixed.add(blockNode.getMetaIndex());
        }
        else{
            for(BlockNode child:blockNode.getChildBlocks()){
                repair(child,fixed);
            }
        }
    }

    public void execRepair(BlockNode blockNode, ArrayList<Integer> fixed){
        RepairUtils.initDefinition(common);
        beforeRepairingCode = common.buggyMethodBuilder.getProgramBuilder().getCompilationUnit().toString();
        repair(blockNode,fixed);

        repairReorder = new RepairReorder(common);
        repairReorder.reorder();//一轮修复后节点重新编号

        RepairUtils.castReturnExp(common);//retutn值的类型转换

        RepairUtils.initDefinition(common);
    }
    public Common getCommon() {
        return common;
    }

    public List<RepairMatch> getRepairMatchList() {
        return repairMatchList;
    }

    public RepairReorder getRepairReorder() {
        return repairReorder;
    }

    public String getBeforeRepairingCode() {
        return beforeRepairingCode;
    }
}
