package com.nwchecker.server.controller;

import com.nwchecker.server.model.Task;
import com.nwchecker.server.model.TaskData;
import com.nwchecker.server.service.TaskService;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @Autowired
    private TaskService taskService;

    @RequestMapping("/index.do")
    public String main(HttpSession session, Model model) throws IllegalArgumentException {
        return "index";
    }
}
