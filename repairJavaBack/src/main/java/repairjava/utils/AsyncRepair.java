package repairjava.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import repairjava.entity.evaluate.Evaluate;
import repairjava.entity.sar.config.Config;
import repairjava.mapper.RequestMapper;

@Component
public class AsyncRepair {
    @Autowired
    RequestMapper requestMapper;

    @Async
    public void executeRepair(String wrongFileDirPath, String correctFileDirPath, String methodToFix, String testFileDirPath, String testClassName, int requestId, String filePath) throws Exception{
        Config config = Evaluate.setConfig(wrongFileDirPath, correctFileDirPath, methodToFix, testFileDirPath, testClassName);
        long startTime = System.currentTimeMillis();
        boolean result = Evaluate.repair(config, filePath);
        long endTime = System.currentTimeMillis();
        requestMapper.updateRequestStatus(requestId, (int)((endTime - startTime) / 1000));
        requestMapper.updateRequestResult(requestId, result);
    }
}
