package com.nwchecker.server.service;

import java.util.Map;

/**
 * Created by Роман on 11.02.2015.
 */
public interface CheckerService {

    Map<String, Object> checkTask(int taskId, byte[] file, int compilerId);
}
