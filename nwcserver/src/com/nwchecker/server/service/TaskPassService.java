package com.nwchecker.server.service;


import com.nwchecker.server.model.Task;
import com.nwchecker.server.model.User;

import java.util.Map;

public interface TaskPassService {
    public Map<String,Object> checkTask(User user, Task task, int compilerId, byte[] file);
}
