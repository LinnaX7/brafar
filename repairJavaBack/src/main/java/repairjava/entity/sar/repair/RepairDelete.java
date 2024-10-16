package repairjava.entity.sar.repair;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.Statement;
import repairjava.entity.program.block.BlockNode;
import repairjava.entity.sar.repair.utils.Common;
import repairjava.entity.sar.repair.utils.RepairUtils;
import repairjava.entity.variables.Variable;
import repairjava.entity.variables.VariableMatch;

import java.util.List;

public class RepairDelete {
    Common common;
    BlockNode buggyBlock;
    public RepairDelete(Common common) {
        this.common=common;
        this.buggyBlock=common.buggyMethodBuilder.getMetaBlockNodes().get(common.blockIndex.peek());
    }
    //delete node
    public void deleteNode(Node parent, Node node){
        if(RepairUtils.isDef(node)){
            List<VariableDeclarator> vars= RepairUtils.getVariableDecl(node);
            for(VariableDeclarator var:vars){
                VariableMatch.removePreMatch(var.getName().getIdentifier(), common.buggyVariableBuilder.getScopeIndex(
                        Variable.getScope(var)));
            }
        }
        if(parent instanceof BlockStmt && node instanceof Statement){
            BlockStmt parentBlock=(BlockStmt) parent;
            int index= RepairUtils.getIndex((BlockStmt) parent,(Statement) node);
            assert(index!=-1);
            parentBlock.getStatements().remove(index);
        }else{
            parent.remove(node);
        }
        int index= RepairUtils.getIndex(buggyBlock.getTreeNodes(),node);
        if(index!=-1) {
            buggyBlock.getTreeNodes().remove(index);
        }
        if(buggyBlock.getJumpBlock()!=null){
            if(buggyBlock.getJumpBlock().getTreeNode()==node){
                buggyBlock.setJumpBlock(null);
            }
        }
    }
}
