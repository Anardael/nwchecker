package com.nwchecker.server.service;

import com.nwchecker.server.model.Task;

import java.util.Map;

/**
 * <h1>Checker Service</h1>
 * Service that checks Tasks.
 * <p>
 *
 * @author Roman Zayats
 * @version 1.0
 * @since 2015-02-11
 */
public interface CheckerService {

    /**
     * Check some Task and return result.
     * <p>
     *
     * @param task Task for checking
     * @param file Source code files
     * @param compilerId Unique ID of compiler
     * @return Result of Task checking
     */
    Map<String, Object> checkTask(Task task, byte[] file, int compilerId);
}
