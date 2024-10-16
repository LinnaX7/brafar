package repairjava.utils;

import com.github.javaparser.Range;
import com.github.javaparser.ast.Node;
import repairjava.entity.program.block.BlockNode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepairResult implements Serializable {
    private List<Block> wrongBlocks;
    private List<Block> correctBlocks;
    private List<Var> wrongVars;
    private List<Var> correctVars;
    private List<TreeNode> treeNodes;
    private List<Match> replaceMatchs;
    private List<Match> insertMatchs;
    private List<Match> deleteMatchs;
    private List<Match> reorderMatchs;
    private String wrongCode;
    private String correctCode;
    private String varMatchedCode;
    private String beforeRepairingCode;
    private String repairedCode;

    public RepairResult(){
        wrongBlocks = new ArrayList<>();
        correctBlocks = new ArrayList<>();
        wrongVars = new ArrayList<>();
        correctVars = new ArrayList<>();
        treeNodes = new ArrayList<>();
        replaceMatchs = new ArrayList<>();
        insertMatchs = new ArrayList<>();
        deleteMatchs = new ArrayList<>();
        reorderMatchs = new ArrayList<>();
    }

    public void addWrongBlock(String type, List<String> codes){
        wrongBlocks.add(new Block(type, codes));
    }

    public void addCorrectBlock(String type, List<String> codes){
        correctBlocks.add(new Block(type, codes));
    }

    public void addWrongVar(String type, String name, Optional<Range> range, String kind){
        wrongVars.add(new Var(type, name, range, kind));
    }

    public void addCorrectVar(String type, String name, Optional<Range> range, String kind){
        correctVars.add(new Var(type, name, range, kind));
    }

    public void addTreeNodes(BlockNode blockNode){
        treeNodes.add(createTreeNode(blockNode));
    }

    public void addReplaceMatchs(Node nodeA, Node nodeB){
        replaceMatchs.add(new Match(nodeA.toString(), nodeB.toString(), nodeA.getRange(), nodeB.getRange()));
    }

    public void addInsertMatchs(String code, Optional<Range> range){
        insertMatchs.add(new Match(code, range));
    }

    public void addDeleteMatchs(String code, Optional<Range> range){
        deleteMatchs.add(new Match(code, range));
    }

    public void addReorderMatchs(String code, Optional<Range> range){
        reorderMatchs.add(new Match(code, range));
    }

    public TreeNode createTreeNode(BlockNode blockNode){
        TreeNode treeNode = new TreeNode(blockNode.getBlockType().toString(), blockNode.getMetaIndex(), blockNode.getTreeNodes(), blockNode.getLineNumbers());
        for (BlockNode childrenBlockNode : blockNode.getChildBlocks()){
            treeNode.childrenNodes.add(createTreeNode(childrenBlockNode));
        }
        return treeNode;
    }

    public void setTreeNodesFault(int metaIndex){
        for (TreeNode treeNode : treeNodes){
            setFault(treeNode, metaIndex);
        }
    }

    public boolean setFault(TreeNode treeNode, int metaIndex){
        if (treeNode.metaIndex == metaIndex){
            treeNode.isFault = true;
            return true;
        }
        else {
            boolean isFault = false;
            for (TreeNode childrenTreeNode : treeNode.getChildrenNodes()){
                isFault |= setFault(childrenTreeNode, metaIndex);
            }
            if (isFault){
                treeNode.isFault = true;
            }
            return isFault;
        }
    }

    public void setWrongBlocks(List<Block> wrongBlocks){
        this.wrongBlocks = wrongBlocks;
    }

    public void setCorrectBlocks(List<Block> correctBlocks){
        this.correctBlocks = correctBlocks;
    }

    public void setWrongVars(List<Var> wrongVars){
        this.wrongVars = wrongVars;
    }

    public void setCorrectVars(List<Var> correctVars){
        this.correctVars = correctVars;
    }

    public void setTreeNodes(List<TreeNode> treeNodes) {
        this.treeNodes = treeNodes;
    }

    public void setReplaceMatchs(List<Match> replaceMatchs) {
        this.replaceMatchs = replaceMatchs;
    }

    public void setInsertMatchs(List<Match> insertMatchs) {
        this.insertMatchs = insertMatchs;
    }

    public void setDeleteMatchs(List<Match> deleteMatchs) {
        this.deleteMatchs = deleteMatchs;
    }

    public void setReorderMatchs(List<Match> reorderMatchs) {
        this.reorderMatchs = reorderMatchs;
    }

    public void setWrongCode(String wrongCode){
        this.wrongCode = wrongCode;
    }

    public void setCorrectCode(String correctCode){
        this.correctCode = correctCode;
    }

    public void setVarMatchedCode(String varMatchedCode) {
        this.varMatchedCode = varMatchedCode;
    }

    public void setBeforeRepairingCode(String beforeRepairingCode) {
        this.beforeRepairingCode = beforeRepairingCode;
    }

    public void setRepairedCode(String repairedCode) {
        this.repairedCode = repairedCode;
    }

    public List<Block> getWrongBlocks(){
        return wrongBlocks;
    }

    public List<Block> getCorrectBlocks(){
        return correctBlocks;
    }

    public List<Var> getWrongVars() {
        return wrongVars;
    }

    public List<Var> getCorrectVars() {
        return correctVars;
    }

    public List<TreeNode> getTreeNodes() {
        return treeNodes;
    }

    public List<Match> getReplaceMatchs() {
        return replaceMatchs;
    }

    public List<Match> getInsertMatchs() {
        return insertMatchs;
    }

    public List<Match> getDeleteMatchs() {
        return deleteMatchs;
    }

    public List<Match> getReorderMatchs() {
        return reorderMatchs;
    }

    public String getWrongCode() {
        return wrongCode;
    }

    public String getCorrectCode() {
        return correctCode;
    }

    public String getVarMatchedCode() {
        return varMatchedCode;
    }

    public String getBeforeRepairingCode() {
        return beforeRepairingCode;
    }

    public String getRepairedCode() {
        return repairedCode;
    }

    public static class Block{
        private String type;
        private List<String> codes;

        public Block(String type, List<String> codes){
            this.type = type;
            this.codes = new ArrayList<>();
            for (String code : codes){
                this.codes.add(code);
            }
        }

        public void setType(String type){
            this.type = type;
        }

        public void setCodes(List<String> codes){
            this.codes = codes;
        }

        public String getType() {
            return type;
        }

        public List<String> getCodes() {
            return codes;
        }
    }

    public static class Var{
        private String type;
        private String name;
        private Range range;
        private String kind;

        public Var() {}

        public Var (String type, String name, Optional<Range> range, String kind){
            this.type = type;
            this.name = name;
            if (range.isPresent()){
                this.range = range.get();
            }
            this.kind = kind;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setRange(Range range) {
            this.range = range;
        }

        public void setKind(String kind) {
            this.kind = kind;
        }

        public String getType() {
            return type;
        }

        public String getName() {
            return name;
        }

        public Range getRange() {
            return range;
        }

        public String getKind() {
            return kind;
        }
    }

    public static class TreeNode{
        private String type;
        private int metaIndex;
        private List<String> codes;
        private List<String> lines;
        private List<TreeNode> childrenNodes;
        private boolean isFault;

        public TreeNode(){}

        public TreeNode(String type, int metaIndex, List<Node> nodes, List<String> lines){
            this.type = type;
            this.metaIndex = metaIndex;
            this.codes = new ArrayList<>();
            this.lines = new ArrayList<>();
            this.childrenNodes = new ArrayList<>();
            this.isFault = false;
            for (Node node : nodes){
                this.codes.add(node.toString());
            }
            for (String line : lines){
                this.lines.add(line);
            }
        }

        public void setType(String type){
            this.type = type;
        }

        public void setMetaIndex(int metaIndex) {
            this.metaIndex = metaIndex;
        }

        public void setCodes(List<String> codes){
            this.codes = codes;
        }

        public void setLines(List<String> lines) {
            this.lines = lines;
        }

        public void setChildrenNodes(List<TreeNode> childrenNodes) {
            this.childrenNodes = childrenNodes;
        }

        public void setFault(boolean fault) {
            isFault = fault;
        }

        public String getType() {
            return type;
        }

        public int getMetaIndex() {
            return metaIndex;
        }

        public List<String> getCodes() {
            return codes;
        }

        public List<String> getLines() {
            return lines;
        }

        public List<TreeNode> getChildrenNodes() {
            return childrenNodes;
        }

        public boolean getFault() {
            return isFault;
        }
    }

    public static class Match{
        private String codeA;
        private String codeB;
        private Range rangeA;
        private Range rangeB;

        public Match(){}

        public Match(String codeA, String codeB, Optional<Range> rangeA, Optional<Range> rangeB){
            this.codeA = codeA;
            this.codeB = codeB;
            if (rangeA.isPresent()) {
                this.rangeA = rangeA.get();
            }
            if (rangeB.isPresent()) {
                this.rangeB = rangeB.get();
            }
        }

        public Match(String codeA, Optional<Range> rangeA){
            this.codeA = codeA;
            if (rangeA.isPresent()) {
                this.rangeA = rangeA.get();
            }
        }

        public void setCodeA(String codeA) {
            this.codeA = codeA;
        }

        public void setCodeB(String codeB) {
            this.codeB = codeB;
        }

        public void setRangeA(Range rangeA) {
            this.rangeA = rangeA;
        }

        public void setRangeB(Range rangeB) {
            this.rangeB = rangeB;
        }

        public String getCodeA() {
            return codeA;
        }

        public String getCodeB() {
            return codeB;
        }

        public Range getRangeA() {
            return rangeA;
        }

        public Range getRangeB() {
            return rangeB;
        }
    }

}
