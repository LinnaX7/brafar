package repairjava.mapper;

import org.apache.ibatis.annotations.Mapper;
import repairjava.utils.RequestRecord;

import java.util.List;
import java.util.Map;

@Mapper
public interface RequestMapper {
    public List<Map<String, Object>> getRequests();
    public Map<String, Object> getRequestById(int id);
    public Map<String, Object> updateRepairRequestTable();
    public int getRequestId();
    public void saveRequest(RequestRecord requestRecord);
    public void updateRequestStatus(int id,int repairTime);
    public void updateRequestResult(int id,boolean result);
}
