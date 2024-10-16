package repairjava.entity.sar.repair;

import com.github.gumtreediff.utils.Pair;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.Statement;
import repairjava.entity.program.block.BlockNode;
import repairjava.entity.sar.repair.utils.Common;
import repairjava.entity.sar.repair.utils.RepairUtils;

import java.util.*;

public class RepairReorder {
    Common common;
    public RepairReorder(Common common){
        this.common=common;
        this.reorderList = new ArrayList<>();
    }
    private List<Node> reorderList;

    public void reorder(){
        System.out.println("-------------------------------Reorder---------------------------------------------------");

        Set<Integer> isOrder=new HashSet<>();
        common.order.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(map -> {

                            BlockNode blockNode = common.buggyMethodBuilder.getMetaBlockNodes().get(map.getKey().block);

                            Node parent = blockNode.getParentNode();

                            Node node = map.getValue();

                            Pair<Integer,Integer> range = RepairUtils.getBlockRange(common.buggyMethodBuilder,map.getKey().block);
                            int index = range.first;
                            RepairUtils.getBlockRange(common.buggyMethodBuilder,map.getKey().block);

                            if(map.getKey().sequence != -1){
                                if(!RepairUtils.isDef(node)){
                                    index+=map.getKey().sequence;
                                }
                            }
                            while (isOrder.contains(index)){
                                index++;
                            }
                            isOrder.add(index);
                            if(parent instanceof BlockStmt && node instanceof Statement){
                                ((BlockStmt)parent).getStatements().remove(node);
                                RepairUtils.insertStmt((BlockStmt) parent,(Statement) node,index);
                            }
                            blockNode.getTreeNodes().remove(node);
                            blockNode.getTreeNodes().add(node);
                            System.out.println(map.getKey().toString()+ map.getValue().toString());
                            reorderList.add(map.getValue());
                        }
                );
        for(BlockNode block : common.buggyMethodBuilder.getMetaBlockNodes()){
            Node parent=block.getParentNode();
            if(parent instanceof BlockStmt && block.getJumpBlock()!=null){
                Node node=block.getJumpBlock().getTreeNode();
                if(node instanceof Statement){
                    int index=parent.getChildNodes().size()-1;
                    if(((BlockStmt)parent).getStatements().remove(node)) {
                        RepairUtils.insertStmt((BlockStmt) parent, (Statement) node, index);
                    }
                }
            }
        }
    }

    public List<Node> getReorderList() {
        return reorderList;
    }
}
