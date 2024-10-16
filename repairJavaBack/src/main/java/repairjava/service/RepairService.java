package repairjava.service;

import repairjava.utils.RepairResult;

import java.util.List;
import java.util.Map;

public interface RepairService {
    public int createRequest(int uploadFileId) throws Exception;
    public List<Map<String, Object>> getRequests();
    public List<RepairResult> getResults(int requestId) throws Exception;
}
