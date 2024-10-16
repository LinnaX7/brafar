package repairjava.service.iml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repairjava.mapper.ProblemMapper;
import repairjava.service.ProblemService;

import java.util.List;
import java.util.Map;

@Service("problemService")
public class ProblemServiceImpl implements ProblemService {
    @Autowired
    private ProblemMapper problemMapper;

    @Override
    public List<Map<String, Object>> getProblems(){
        return problemMapper.getProblems();
    }

    @Override
    public Map<String, Object> getProblemById(int id) {
        return problemMapper.getProblemById(id);
    }
}
