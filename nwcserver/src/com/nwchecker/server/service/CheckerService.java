package com.nwchecker.server.service;

import com.nwchecker.server.model.Task;

import java.util.Map;

/**
 * Created by Роман on 11.02.2015.
 */
public interface CheckerService {

    Map<String, Object> checkTask(Task task, byte[] file, int compilerId);
}
