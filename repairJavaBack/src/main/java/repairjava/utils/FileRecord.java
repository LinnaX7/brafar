package repairjava.utils;

public class FileRecord {
    private String name;
    private String path;
    private int problemId;
    private boolean refactored;
    public FileRecord(String name, String path, int problemId){
        this.name = name;
        this.path = path;
        this.problemId = problemId;
        this.refactored = false;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setProblemId(int problemId) {
        this.problemId = problemId;
    }

    public void setRefactored(boolean refactored) {
        this.refactored = refactored;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public int getProblemId() {
        return problemId;
    }

    public boolean isRefactored() {
        return refactored;
    }
}
