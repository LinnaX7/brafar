package repairjava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import repairjava.service.RepairService;
import repairjava.utils.RepairResult;
import repairjava.utils.ResponseResult;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/repair")
public class RepairController {
    @Autowired
    private RepairService repairService;

    @PostMapping("/createRequest")
    public ResponseResult<Integer> createRequest(@RequestParam("uploadFileId")int uploadFileId){
        try {
            return new ResponseResult<>(200, "success", repairService.createRequest(uploadFileId));
        }
        catch (Exception e){
            return new ResponseResult<>(400, "error");
        }
    }

    @GetMapping("/getRequests")
    public ResponseResult<List<Map<String, Object>>> getRequest(){
        try {
            return new ResponseResult<>(200, "success", repairService.getRequests());
        }
        catch (Exception e){
            return new ResponseResult<>(400, "error");
        }
    }

    @PostMapping("/getResults")
    public ResponseResult<List<RepairResult>> getResults(@RequestParam("requestId")int requestId){
        try {
            return new ResponseResult<>(200, "success", repairService.getResults(requestId));
        }
        catch (Exception e){
            return new ResponseResult<>(400, "error");
        }
    }
}
