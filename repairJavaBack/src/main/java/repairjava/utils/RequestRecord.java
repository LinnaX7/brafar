package repairjava.utils;

public class RequestRecord {
    private int problemId;
    private int uploadFileId;
    private String resultPath;
    private String status;
    private String timeStamp;
    public RequestRecord(int problemId, int uploadFileId, String resultPath, String timeStamp){
        this.problemId = problemId;
        this.uploadFileId = uploadFileId;
        this.resultPath = resultPath;
        this.timeStamp = timeStamp;
        this.status = "repairing";
    }

    public void setProblemId(int problemId) {
        this.problemId = problemId;
    }

    public void setUploadFileId(int uploadFileId) {
        this.uploadFileId = uploadFileId;
    }

    public void setResultPath(String resultPath) {
        this.resultPath = resultPath;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getProblemId() {
        return problemId;
    }

    public int getUploadFileId() {
        return uploadFileId;
    }

    public String getResultPath() {
        return resultPath;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getStatus() {
        return status;
    }
}