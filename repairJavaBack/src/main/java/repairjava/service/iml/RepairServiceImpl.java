package repairjava.service.iml;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import repairjava.mapper.FileMapper;
import repairjava.mapper.ProblemMapper;
import repairjava.mapper.RequestMapper;
import repairjava.service.RepairService;
import repairjava.utils.AsyncRepair;
import repairjava.utils.FileRecord;
import repairjava.utils.RepairResult;
import repairjava.utils.RequestRecord;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("repairService")
public class RepairServiceImpl implements RepairService {
    @Value("${data-path}") private String dataPath;

    @Autowired
    private FileMapper fileMapper;

    @Autowired
    private ProblemMapper problemMapper;

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private AsyncRepair asyncRepair;

    @Override
    public List<Map<String, Object>> getRequests(){
        List<Map<String, Object>> requests = requestMapper.getRequests();
        for (Map<String, Object> map :requests){
            map.put("problemName", problemMapper.getProblemById(Integer.parseInt(map.get("problemId").toString())).get("className"));
            map.put("uploadFileName", fileMapper.getUploadFileById(Integer.parseInt(map.get("uploadFileId").toString())).getName());
        }
        return requests;
    }

    @Override
    public int createRequest(int uploadFileId) throws Exception{
        FileRecord uploadFileRecord = fileMapper.getUploadFileById(uploadFileId);
        String uploadFilePath = uploadFileRecord.getPath();
        String uploadFileDirPath = uploadFilePath.substring(0, uploadFilePath.lastIndexOf('/'));

        int problemId = uploadFileRecord.getProblemId();
        Map<String, Object> map = problemMapper.getProblemById(problemId);
        String className = map.get("className").toString();
        String methodName = map.get("methodName").toString();
        String testFileDirPath = map.get("testFileDirPath").toString();
        String testClassName = map.get("testClassName").toString();

        requestMapper.updateRepairRequestTable();
        int requestId = requestMapper.getRequestId();
        String dir = dataPath + "/repairRequest/" + Integer.toString(problemId) + "/" + Integer.toString(uploadFileId) + "/" + Integer.toString(requestId);
        String resultPath = dir + "/repairResult.json";
        File dirFile = new File(dir);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        asyncRepair.executeRepair(uploadFileDirPath + "/refactoredWrong", uploadFileDirPath + "/refactoredCorrect", methodName + "@" + className, testFileDirPath, testClassName, requestId, resultPath);
        Date date = new Date( );
        SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
        requestMapper.saveRequest(new RequestRecord(problemId, uploadFileId, resultPath, dateFormat.format(date)));
        return requestId;
    }

    @Override
    public List<RepairResult> getResults(int requestId) throws Exception{
        List<RepairResult> repairResultList;
        String filePath = requestMapper.getRequestById(requestId).get("resultPath").toString();
        File file = new File(filePath);
        String content= FileUtils.readFileToString(file,"UTF-8");
        repairResultList = new Gson().fromJson(content, new TypeToken<List<RepairResult>>() {}.getType());
        return repairResultList;
    }
}
