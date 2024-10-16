package repairjava.entity.ast;

import com.github.gumtreediff.gen.javaparser.JavaParserGenerator;
import com.github.gumtreediff.tree.Tree;
import com.github.gumtreediff.tree.TreeContext;
import repairjava.entity.cfs.CFS;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;


public class JdtTreeContext {
    private final String filePath;
    private final String fileName;//HelloWorld.java
    private final String parentPath;//src/main/java/monitor
    private final String classPath;//src/main/java
    private final String className;//monitor.HelloWorld
    private final String fileContext;
    private final TreeContext treeContext;
    //    private final CFS methodCFS;
    private final List<CFS> methodCFSList;

    public String getFilePath() {

        return filePath;
    }

    public String getFileName() {

        return fileName;
    }

    public String getParentPath() {
        return parentPath;
    }

    public String getClassName() {
        return className;
    }

    public String getClassPath() {
        return classPath;
    }

    public TreeContext getTreeContext() {
        return treeContext;
    }

    public String getFileContext() {
        return fileContext;
    }

    public JdtTreeContext(String parentPath, String fileName, String classPath,
                          String className) throws IOException {
        this.filePath = Paths.get(parentPath, fileName).toString();
        this.fileName = fileName;
        this.parentPath = parentPath;
        this.className = className;
        this.classPath = classPath;

        File file = new File(filePath);
        BufferedReader bf = new BufferedReader(new FileReader(file));
        String content = "";
        StringBuilder sb = new StringBuilder();
        content = bf.readLine();
        while (content != null) {
            sb.append(content + "\n");
            content = bf.readLine();
        }
        bf.close();
        fileContext = sb.toString();
        this.treeContext = new JavaParserGenerator().generateFrom().file(file);
        this.methodCFSList = AstDiff.getMethodCFS(this.treeContext);
    }

    public CFS getMethodCFS(String methodName) {
        for (CFS cfs : methodCFSList) {
            if (cfs.getMethodName().equals(methodName)) {
                return cfs;
            }
        }
        return null;
    }

    public Tree getMethodTree(String methodName) {

        return AstDiff.getMethodTree(methodName, this.treeContext);
    }

}

