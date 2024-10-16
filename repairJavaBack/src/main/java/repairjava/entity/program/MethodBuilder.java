package repairjava.entity.program;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.ast.stmt.SwitchStmt;
import com.github.javaparser.ast.stmt.WhileStmt;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import repairjava.entity.program.block.BlockNode;
import repairjava.entity.program.block.BlockType;
import repairjava.entity.program.block.JumpBlock;
import repairjava.entity.variables.Variable;
import repairjava.entity.variables.VariableBuilder;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MethodBuilder {
    private final String methodName;
    private final BlockBuilder blockBuilder;
    private final VariableBuilder variableBuilder;
    private int blockIndex;
    private final ArrayList<BlockNode> metaBlockNodes; //基本块
    private ArrayList<String> breakPointLines;
    private final MethodDeclaration methodDeclaration;

    private ProgramBuilder programBuilder;

    private final ArrayList<JumpBlock> returnBlocks;

    public void setProgramBuilder(ProgramBuilder programBuilder) {
        this.programBuilder = programBuilder;
    }

    public int getMethodLOC(){
        assert methodDeclaration.getEnd().isPresent();
        assert methodDeclaration.getBegin().isPresent();
        return methodDeclaration.getEnd().get().line-methodDeclaration.getBegin().get().line+1;
    }

    public ProgramBuilder getProgramBuilder() {
        return programBuilder;
    }

    public MethodBuilder(MethodDeclaration methodDeclaration) {
        this.methodDeclaration=methodDeclaration;
        this.methodName = methodDeclaration.getName().toString();

        blockBuilder = new BlockBuilder(methodDeclaration);
        variableBuilder =new VariableBuilder(methodDeclaration);

        blockIndex = 0;
        metaBlockNodes = new ArrayList<>();
        returnBlocks = new ArrayList<>();
        SetVariablesDU();

    }
    public MethodDeclaration getMethodDeclaration() {
        return methodDeclaration;
    }

    public String getMethodName() {
        return methodName;
    }

    public ArrayList<String> getBreakPointLines() {
        return breakPointLines;
    }

    public VariableBuilder getVariableBuilder(){
        return variableBuilder;
    }

    public void clearBlocksBLP(){
        blockBuilder.clearBlockBPL();
    }

    public void setVariableValues(String executeLog, String packageName) throws IOException {
        for(Variable var: variableBuilder.getVariableList()) {
            var.getLineValues().clear();
        }
        this.breakPointLines = new ArrayList<>();
        FileInputStream fStream = new FileInputStream(executeLog);
        BufferedReader br = new BufferedReader(new InputStreamReader(fStream));
        String line = br.readLine();
        int index = 0;
        while (line!=null){
            if(line.startsWith(String.format("Variables at %s:", packageName))){
                String lineNumber = line.split(":")[1];
                br.readLine();
                line = br.readLine();
                while (!line.equals("]")){
                    line.replaceAll("[\b\r\n\t\u0000\u0001]*", "");
                    JSONObject var;
                    try {
                        var = JSONObject.fromObject(line);
                    }catch (JSONException e){
                        int i = line.indexOf("\"value");
                        line = line.substring(0,63)+"\"value\":\"\"}";
//                        System.out.println(line);
                        var = JSONObject.fromObject(line);
                    }
                    String name = var.getString("name");
                    if(variableBuilder.isIdentical(name)){
                        variableBuilder.getVariable(name).putLineValues(index,var.get("value"));
                    }else{
                        variableBuilder.getVariable(name, Integer.parseInt(lineNumber)).putLineValues(index,var.get("value"));
                    }

                    line = br.readLine();
                }
                breakPointLines.add(lineNumber);
                index+=1;
            }
            line = br.readLine();
        }
        blockBuilder.setBodyChildrenBPL(null,0, breakPointLines.size(), breakPointLines);
        setVariableBlockValue();
        br.close();
    }


    public void setVariableBlockValue(){
        for(Variable var: variableBuilder.getVariableList()) {
            var.getInValues().clear();
            var.getValues().clear();
            for (int i = 0; i < blockIndex; i++) {
                ArrayList<Integer> indexes = metaBlockNodes.get(i).getBreakPointIndexes();
                ArrayList<Integer> inIndexes = metaBlockNodes.get(i).getBreakPointInIndexes();
                var.addValuesFromIndexes(indexes, inIndexes);
            }
        }
    }

    public void analyseExp(int blk, Expression expression) {
        if (expression instanceof VariableDeclarationExpr) {
            ((VariableDeclarationExpr) expression).getVariables().forEach(v -> {
                Variable variable = variableBuilder.getVariable(v.getName().getIdentifier(),expression);
                if(variable != null) {
                    variable.setDefine(blk);
                    variable.addDefUseMark(metaBlockNodes.get(blk).getBlockMark());
                    metaBlockNodes.get(blk).addRelatedVar(variable);
                }
            });
        }else if(expression instanceof NameExpr) {
            Variable variable = variableBuilder.getVariable(((NameExpr) expression).getName().getIdentifier(),expression);
            if (variable != null) {
                variable.setUses(blk);
                variable.addDefUseMark(metaBlockNodes.get(blk).getBlockMark());
                metaBlockNodes.get(blk).addRelatedVar(variable);
            }
        }
    }

    public void visitNode(int blk, Node n){
        if( n instanceof IfStmt){
            visitNode(blk,((IfStmt)n).getCondition());
            return;
        } else if(n instanceof WhileStmt){
            visitNode(blk,((WhileStmt)n).getCondition());
            return;
        } else if(n instanceof SwitchStmt){
            visitNode(blk,((SwitchStmt)n).getSelector());
            return;
        }else if(n instanceof  Expression){
            analyseExp(blk,(Expression) n);
        }else if(n instanceof ReturnStmt){
            ReturnStmt returnStmt=(ReturnStmt) n;
            if(returnStmt.getExpression().isPresent()) {
                Expression exp=returnStmt.getExpression().get();
                Variable variable= variableBuilder.getReturnVariable(exp);
                variable.setDefine(blk);
                variable.setReturnUses(blk);
                variable.addDefUseMark(metaBlockNodes.get(blk).getBlockMark());
                variable.addDefUseMark(metaBlockNodes.get(blk).getJumpBlock().getBlockMark());
                metaBlockNodes.get(blk).addRelatedVar(variable);
                for(NameExpr nameExpr:returnStmt.getExpression().get().findAll(NameExpr.class)) {
                    Variable var = variableBuilder.getVariable(nameExpr.getName().getIdentifier(), returnStmt.getExpression().get());
                    if (var != null) {
                        var.setReturnUses(blk);
                        var.addDefUseMark(metaBlockNodes.get(blk).getJumpBlock().getBlockMark());
                        metaBlockNodes.get(blk).addRelatedVar(var);
                        metaBlockNodes.get(blk).getJumpBlock().addRelatedVar(var);
                    }

                }
            }
        }
        for(Node node:n.getChildNodes()){
            visitNode(blk,node);
        }
    }

    public void setVariableUsage(BlockNode blockNode){
        /*查找叶子节点*/
        if(blockNode.getChildBlocks().size()==0){

            metaBlockNodes.add(blockNode);
            blockNode.setMetaIndex(blockIndex);

            /*查找return block*/
            if(blockNode.getJumpBlock()!=null && blockNode.getJumpBlock().getType()== BlockType.RETURN){
                returnBlocks.add(blockNode.getJumpBlock());
                blockNode.getJumpBlock().setMetaIndex(blockIndex);
            }
            /* empty block的处理*/
            if(blockNode.getEmpty() && (blockNode.getJumpBlock()!=null && blockNode.getJumpBlock().getType()== BlockType.RETURN)){
                visitNode(blockIndex, blockNode.getJumpBlock().getTreeNode());
            }
            else if(!blockNode.getEmpty()){
                    blockNode.getTreeNodes().forEach(node -> visitNode(blockIndex, node));
                    if(blockNode.getJumpBlock()!=null && blockNode.getJumpBlock().getType()==BlockType.RETURN) {
                        visitNode(blockIndex, blockNode.getJumpBlock().getTreeNode());
                    }
            }

            blockIndex += 1;
        }
        else{
            for (BlockNode child:blockNode.getChildBlocks()){
                setVariableUsage(child);
            }
        }
    }

    public ArrayList<JumpBlock> getReturnBlocks() {
        return returnBlocks;
    }

    public void SetVariablesDU() {
        for(BlockNode blockNode: blockBuilder.getBlockNodes()){
            setVariableUsage(blockNode);
        }
    }

    public BlockBuilder getBlockBuilder() {
        return blockBuilder;
    }

    public ArrayList<BlockNode> getMetaBlockNodes() {
        return metaBlockNodes;
    }

}

