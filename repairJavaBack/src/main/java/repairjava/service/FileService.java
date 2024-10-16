package repairjava.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface FileService {
    public int uploadFile(int problemId, MultipartFile file) throws Exception;
    public List<Map<String, Object>> getUploadFilesByProblemId(int problemId);
}
