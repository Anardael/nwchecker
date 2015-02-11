package com.nwchecker.server.service;


import com.nwchecker.server.model.User;

import java.util.Map;

public interface TaskPassService {
    public boolean checkTask(User user, int taskId, int compilerId, byte[] file);
}
