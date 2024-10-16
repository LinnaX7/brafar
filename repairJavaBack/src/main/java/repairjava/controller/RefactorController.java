package repairjava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import repairjava.service.RefactorService;
import repairjava.utils.ResponseResult;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/refactor")
public class RefactorController {

    @Autowired
    private RefactorService refactorService;

    @PostMapping("")
    public ResponseResult<Map> refactor(@RequestParam("uploadFileId")int uploadFileId){
        try {
            Map map = refactorService.getRefactor(uploadFileId);
            return new ResponseResult<>(200, "success", map);
        }
        catch (Exception e){
            return new ResponseResult<>(400, "error");
        }
    }
}
