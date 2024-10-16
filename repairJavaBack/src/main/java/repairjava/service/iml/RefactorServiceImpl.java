package repairjava.service.iml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repairjava.entity.cfs.guider.CFGuider;
import repairjava.entity.evaluate.Evaluate;
import repairjava.mapper.FileMapper;
import repairjava.mapper.ProblemMapper;
import repairjava.service.RefactorService;
import repairjava.utils.FileRecord;

import java.util.Map;

@Service("refactorService")
public class RefactorServiceImpl implements RefactorService {
    @Autowired
    private FileMapper fileMapper;

    @Autowired
    private ProblemMapper problemMapper;

    @Override
    public Map<String, Object> getRefactor(int uploadFileId) throws Exception{
        FileRecord uploadFileRecord = fileMapper.getUploadFileById(uploadFileId);
        String uploadFilePath = uploadFileRecord.getPath();
        String uploadFileDirPath = uploadFilePath.substring(0, uploadFilePath.lastIndexOf('/'));

        int problemId = uploadFileRecord.getProblemId();
        Map<String, Object> map = problemMapper.getProblemById(problemId);
        String referenceFilesDirPath = map.get("referenceFilesDirPath").toString();
        String className = map.get("className").toString();
        String methodName = map.get("methodName").toString();
        String uploadFileFormatedDirPath = Evaluate.formatFile(uploadFileDirPath, className, methodName);

        Map<String, Object> controlFlowGuide = CFGuider.controlFlowGuide(uploadFileFormatedDirPath, Evaluate.searchByCFSandAST(uploadFileFormatedDirPath, referenceFilesDirPath, className, methodName), className, methodName);
        fileMapper.updateUploadFileRefactored(uploadFileId);
        return controlFlowGuide;
    }
}
