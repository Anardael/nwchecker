package com.nwchecker.server.service;

import com.nwchecker.server.model.Task;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service(value = "CheckerService")
public class CheckerServiceImpl implements CheckerService {

    private static List<String> compilerErrors = Arrays.asList("NullPointerException", "Compiler error",
            "RunTimeException", "IOException");

    @Override
    public Map<String, Object> checkTask(Task task, byte[] file, int compilerId) {
        Map<String, Object> result = new LinkedHashMap<String, Object>();
        Random rd = new Random();

        boolean passed = rd.nextBoolean();
        if (passed) {
            result.put("passed", true);
            //generate result:
            result.put("time", rd.nextInt(1000));
            result.put("memory", rd.nextInt(1000));
            return result;
        } else {
            result.put("passed", false);
            result.put("time", 1000 + rd.nextInt(3000));
            result.put("memory", 1000 + rd.nextInt(3000));
            result.put("message", compilerErrors.get(rd.nextInt(compilerErrors.size())));
            return result;
        }
    }
}
