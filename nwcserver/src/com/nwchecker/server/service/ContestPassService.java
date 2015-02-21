package com.nwchecker.server.service;


import com.nwchecker.server.model.ContestPass;
import com.nwchecker.server.model.Task;

import java.util.Map;

public interface ContestPassService {

    void saveContestPass(ContestPass contestPass);

    void updateContestPass(ContestPass contestPass);

    Map<String, Object> checkTask(boolean save, ContestPass contestPass, Task task, int compilerId, byte[] file);
}
