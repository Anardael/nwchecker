package com.nwchecker.server.service;

import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Роман on 11.02.2015.
 */
@Service(value = "CheckerService")
public class CheckerServiceImpl implements CheckerService {
    @Override
    public Map<String, Object> checkTask(int taskId, byte[] file, int compilerId) {
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
            return result;
        }
    }
}
