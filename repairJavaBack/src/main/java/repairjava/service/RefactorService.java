package repairjava.service;

import java.util.Map;

public interface RefactorService {
    public Map<String, Object> getRefactor(int uploadFileId) throws Exception;
}
