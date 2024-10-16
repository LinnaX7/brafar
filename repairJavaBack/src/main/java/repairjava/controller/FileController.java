package repairjava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import repairjava.service.FileService;
import repairjava.utils.ResponseResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public ResponseResult<List> upload(@RequestParam("problemId")int problemId, @RequestParam("fileList")MultipartFile fileList[]){
        List<Integer> fileIdList = new ArrayList<>();
        for (MultipartFile file : fileList){
            try {
                fileIdList.add(fileService.uploadFile(problemId, file));
            }
            catch (Exception e){
                return new ResponseResult<>(400, "error");
            }
        }
        return new ResponseResult<>(200, "success", fileIdList);
    }

    @PostMapping("/getUploadFilesByProblemId")
    public ResponseResult<List> getUploadFilesByProblemId(@RequestParam("problemId")int problemId){
        List<Map<String,Object>> list = fileService.getUploadFilesByProblemId(problemId);
        return new ResponseResult<>(200, "success", list);
    }

}
