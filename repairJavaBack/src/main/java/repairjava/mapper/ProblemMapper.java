package repairjava.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProblemMapper {
    public List<Map<String, Object>> getProblems();
    public Map<String, Object> getProblemById(int id);
}
