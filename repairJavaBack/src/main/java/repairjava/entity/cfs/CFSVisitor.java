package repairjava.entity.cfs;

import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.comments.BlockComment;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.comments.LineComment;
import com.github.javaparser.ast.stmt.*;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class CFSVisitor extends VoidVisitorAdapter<Object> {
    private String strCFS="";
    private boolean isFuncName;
    private boolean isFirstNode;


    public CFSVisitor(MethodDeclaration methodDeclaration){
        isFuncName = isFirstNode = false;
        visit(methodDeclaration, null);
    }

    /**
     * @methodsName: addCFSNode
     * @description: 添加CFS节点到cfsFlow，按打印规范添加节点名称到strCFS
     * @param: 节点名称node，节点类型type
     */
    private void addCFSNode(String node){

        if(isFuncName){
            strCFS += node;
            strCFS += "():";
            isFuncName = false;
            isFirstNode = true;
        }else if(isFirstNode){
            strCFS += node;
            isFirstNode = false;
        }else{
            strCFS += ",";
            strCFS += node;
        }
    }

    /**
     * @methodsName: visit
     * @description: 重写对构造函数的visit方法
     * @param:
     */
    @Override
    public void visit(ConstructorDeclaration n, Object arg) {

        String tempName ="";
        tempName += n.getName();
        isFuncName = true;
        addCFSNode(tempName);
        super.visit(n, arg);
    }

    /**
     * @methodsName: visit
     * @description: 重写对类的visit方法
     * @param: 类声明的AST节点n，参数arg
     */
    @Override
    public void visit(MethodDeclaration n, Object arg) {

        String tempName ="";
        tempName += n.getName();
        isFuncName = true;
        addCFSNode(tempName);
        super.visit(n, arg);
    }

    /**
     * @methodsName: visit
     * @description: 重写对语句块的visit方法
     * @param: 语句块的AST节点n，参数arg
     */
    @Override
    public void visit(BlockStmt n, Object arg){

        NodeList<Statement> StmtList = n.getStatements();
        for(Statement stmt: StmtList) {
            visit(stmt,arg);
        }
    }

    /**
     * @methodsName: visit
     * @description: 重写对if语句的visit方法
     * @param: if语句的AST节点n，参数arg
     */
    @Override
    public void visit(IfStmt n, Object arg){
        addCFSNode("If_start");
        //获取thenStmt情况
        visit(n.getThenStmt(),arg);
        addCFSNode("If_end");
        //对存在的ELSE情况进行处理
        if(n.hasElseBlock()||n.hasElseBranch()){
            if(n.getElseStmt().isPresent()){
                addCFSNode("Else_start");
                visit(n.getElseStmt().get(),arg);
                addCFSNode("Else_end");
            }
        }
    }

    /**
     * @methodsName: visit
     * @description: 重写对switch语句的visit方法
     * @param: switch语句的AST节点n，参数arg
     */
    @Override
    public void visit(SwitchStmt n, Object arg){
        addCFSNode("Switch_start");
        NodeList<SwitchEntry> EntryList = n.getEntries();
        for(SwitchEntry entry: EntryList){
            addCFSNode("Switch_Entry");

            NodeList<Statement> StmtList = entry.getStatements();
            for(Statement stmt: StmtList) {
                visit(stmt,arg);
            }
        }
        addCFSNode("Switch_end");
    }

    /**
     * @methodsName: visit
     * @description: 重写对for语句的visit方法
     * @param: for语句的AST节点n，参数arg
     */
    @Override
    public void visit(ForStmt n, Object arg){
        addCFSNode("For_start");
        visit(n.getBody(),arg);
        addCFSNode("For_end");
    }

    /**
     * @methodsName: visit
     * @description: 重写对foreach语句的visit方法
     * @param: foreach语句的AST节点n，参数arg
     */
    @Override
    public void visit(ForEachStmt n, Object arg)
    {
        addCFSNode("ForEach_start");
        visit(n.getBody(),arg);
        addCFSNode("ForEach_end");
    }

    /**
     * @methodsName: visit
     * @description: 重写对while语句的visit方法
     * @param: while语句的AST节点n，参数arg
     */
    @Override
    public void visit(WhileStmt n, Object arg){
        addCFSNode("While_start");

        visit(n.getBody(),arg);

        addCFSNode("While_end");
    }

    /**
     * @methodsName: visit
     * @description: 重写对do-while语句的visit方法
     * @param: do-while语句的AST节点n，参数arg
     */
    @Override
    public void visit(DoStmt n, Object arg){
        //do-while循环结构
        addCFSNode("Do_start");

        visit(n.getBody(),arg);

        addCFSNode("Do_end");
    }

//    /**
//     * @methodsName: visit
//     * @description: 重写对continue语句的visit方法
//     * @param: continue语句的AST节点n，参数arg
//     */
//    @Override
//    public void visit(ContinueStmt n, Object arg){
//        addCFSNode("Continue");
//    }

//    /**
//     * @methodsName: visit
//     * @description: 重写对break语句的visit方法
//     * @param: break语句的AST节点n，参数arg
//     */
//    @Override
//    public void visit(BreakStmt n, Object arg){
//        addCFSNode("Break");
//    }

//    /**
//     * @methodsName: visit
//     * @description: 重写对return语句的visit方法
//     * @param: return语句的AST节点n，参数arg
//     */
//    @Override
//    public void visit(ReturnStmt n, Object arg){
//        addCFSNode("Return");
//    }

    /**
     * @methodsName: visit
     * @description: 重写visit LabeledStmt
     * @param: labeled statement的AST节点n，参数arg
     */
    @Override
    public void visit(LabeledStmt n, Object arg)
    {

    }

    /**
     * @methodsName: visit
     * @description: 重写visit UnParsableStmt
     * @param: 语法错误的AST节点n，参数arg
     */
    @Override
    public void visit(UnparsableStmt n, Object arg)
    {

    }

    /**
     * @methodsName: visit
     * @description: 重写visit BlockComment
     * @param: block comment的AST节点n，参数arg
     */
    @Override
    public void visit(BlockComment n, Object arg) {

    }

    /**
     * @methodsName: visit
     * @description: 重写visit JavadocComment
     * @param: javadoc comment的AST节点n，参数arg
     */
    @Override
    public void visit(final JavadocComment n, final Object arg) {

    }

    /**
     * @methodsName: visit
     * @description: 重写visit LineComment
     * @param: line comment的AST节点n，参数arg
     */
    @Override
    public void visit(LineComment n, Object arg) {

    }
    /**
     * @methodsName: visit
     * @description: 对语句的遍历，转到相应的visit函数进行处理
     * @param: 语句的AST节点statement，参数arg
     */
    public void visit(Statement statement,Object arg){
        if(statement instanceof  BlockStmt){
            visit((BlockStmt) statement,arg);
        }else if(statement instanceof IfStmt){
            visit((IfStmt) statement,arg);
        }else if(statement instanceof SwitchStmt){
            visit((SwitchStmt) statement,arg);
        }else if(statement instanceof ForStmt){
            visit((ForStmt) statement,arg);
        }else if(statement instanceof ForEachStmt){
            visit((ForEachStmt) statement,arg);
        }else if(statement instanceof WhileStmt){
            visit((WhileStmt) statement,arg);
        }else if(statement instanceof DoStmt){
            visit((DoStmt) statement,arg);
        }else if(statement instanceof ContinueStmt){
            visit((ContinueStmt) statement,arg);
        }else if(statement instanceof BreakStmt){
            visit((BreakStmt) statement,arg);
        }else if(statement instanceof ReturnStmt){
            visit((ReturnStmt) statement,arg);
        }
    }

    /**
     * @methodsName: getStrCFS
     * @description: 获得要输出的控制流结构字符串
     * @return: string
     */
    public String getStrCFS(){
        return strCFS;
    }

}