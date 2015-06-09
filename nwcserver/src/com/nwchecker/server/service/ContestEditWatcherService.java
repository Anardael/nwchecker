package com.nwchecker.server.service;

import org.springframework.web.context.request.async.DeferredResult;

import java.util.Map;

/**
 * <h1>Contest Edit Watcher Service</h1>
 * Service that can change list of Users that can edit
 * some Contest.
 * <p>
 *
 * @author Roman Zayats
 * @version 1.0
 * @since 2015-02-21
 */
public interface ContestEditWatcherService {
    void addParameters(int contestId, String username);

    boolean checkContestIsEditedById(int contestId);
}
