package repairjava.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import repairjava.service.ProblemService;
import repairjava.utils.ResponseResult;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/problem")
public class ProblemController {
    @Autowired
    private ProblemService problemService;

    @GetMapping("/getProblems")
    public ResponseResult<List<Map<String, Object>>> getProblems(){
        try {
            List<Map<String, Object>> problems = problemService.getProblems();
            return new ResponseResult<>(200, "success", problems);
        }
        catch (Exception e){
            return new ResponseResult<>(400, "error");
        }
    }
    @PostMapping("/getProblemById")
    public ResponseResult<Map<String, Object>> getProblemById(@Param("id") int id){
        try {
            Map<String, Object> map = problemService.getProblemById(id);
            return new ResponseResult<>(200, "success", map);
        }
        catch (Exception e){
            return new ResponseResult<>(400, "error");
        }
    }


}
