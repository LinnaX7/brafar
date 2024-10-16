package repairjava.service;

import java.util.List;
import java.util.Map;

public interface ProblemService {
    public List<Map<String, Object>> getProblems();
    public Map<String, Object> getProblemById(int id);
}
