package repairjava.service.iml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import repairjava.mapper.FileMapper;
import repairjava.service.FileService;
import repairjava.utils.FileRecord;

import java.io.File;
import java.util.List;
import java.util.Map;

@Service("fileService")
public class FileServiceImpl implements FileService {
    @Value("${data-path}") private String dataPath;

    @Autowired
    private FileMapper fileMapper;

    @Override
    public int uploadFile(int problemId, MultipartFile file) throws Exception{
        String filename = file.getOriginalFilename();
        fileMapper.updateUploadFileTable();
        int fileId = fileMapper.getUploadFileId();
        String dir = dataPath + "/uploadFile/" + Integer.toString(problemId) + "/" + Integer.toString(fileId);
        String filePath = dir + "/" + filename;
        File dirFile = new File(dir);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        file.transferTo(new File(filePath));
        fileMapper.uploadFile(new FileRecord(filename, filePath, problemId));
        return fileId;
    }

    @Override
    public List<Map<String, Object>> getUploadFilesByProblemId(int problemId){
        return fileMapper.getUploadFilesByProblemId(problemId);
    }
}
