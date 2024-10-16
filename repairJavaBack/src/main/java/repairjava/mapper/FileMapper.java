package repairjava.mapper;

import org.apache.ibatis.annotations.Mapper;
import repairjava.utils.FileRecord;

import java.util.List;
import java.util.Map;

@Mapper
public interface FileMapper {
    public void uploadFile(FileRecord fileRecord);
    public int getUploadFileId();
    public Map<String, Object> updateUploadFileTable();
    public void updateUploadFileRefactored(int id);
    public List<Map<String, Object>> getUploadFilesByProblemId(int problemId);
    public FileRecord getUploadFileById(int id);
    public FileRecord getTestFileById(int id);
}