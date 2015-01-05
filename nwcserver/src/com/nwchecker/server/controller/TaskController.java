/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nwchecker.server.controller;

import com.nwchecker.server.model.Task;
import com.nwchecker.server.model.TaskData;
import com.nwchecker.server.model.TaskTheoryLink;
import com.nwchecker.server.service.TaskService;
import com.nwchecker.server.validators.TaskValidator;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    @RequestMapping("/getTasks")
    public String getTasks(HttpSession session, Model model) {
        List<Task> tasks = taskService.getTasks();
        model.addAttribute("tasks", tasks);
        return "task";
    }

    @RequestMapping(value = "/getTaskById", method = RequestMethod.GET)
    public String getTaskById(@RequestParam("id") String id, HttpSession session, Model model) {
        int value = Integer.parseInt(id);
        Task task = taskService.getTaskById(value);
        model.addAttribute("task", task);
        return "taskLocal";
    }

    @RequestMapping("/taskCreating")
    public String taskCreating(HttpSession session, Model model) {
        System.out.println("");
        return "taskCreate";
    }

    @RequestMapping(value = "/addTask", method = RequestMethod.POST)
    public String addTask(Model model, HttpSession session, HttpServletRequest request,
            @RequestParam("inputFile") MultipartFile inFile,
            @RequestParam("outputFile") MultipartFile outFile) {
        try {
            //get parameters:
            String title = new String(request.getParameter("title")
                    .getBytes("iso-8859-1"), "UTF-8");
            String memoryLimit = request.getParameter("memoryLimit");
            String timeLimit = request.getParameter("timeLimit");
            String rate = request.getParameter("rate");
            String complexity = request.getParameter("complexity");
            String inputFileName = new String(request.getParameter("inputFileName")
                    .getBytes("iso-8859-1"), "UTF-8");
            String outputFileName = new String(request.getParameter("outputFileName")
                    .getBytes("iso-8859-1"), "UTF-8");
            String description = new String(request.getParameter("description")
                    .getBytes("iso-8859-1"), "UTF-8");
            String verificationScript = new String(request.getParameter("verificationScript")
                    .getBytes("iso-8859-1"), "UTF-8");
            String forumLink = request.getParameter("forumLink");
            //theoryLinks array:
            LinkedList<TaskTheoryLink> theoryLinksList = new LinkedList<TaskTheoryLink>();
            for (String link : request.getParameterValues("theoryLinks[]")) {
                theoryLinksList.add(new TaskTheoryLink(link));
            }
            //in/out data list:
            LinkedList<TaskData> ioTaskData = new LinkedList<TaskData>();

            //Get io data from uploaded files:
            if (!inFile.isEmpty() || !outFile.isEmpty()) {
                //Strings of data from uploaded files:
                String[] inString;
                String[] outString;
                byte[] bytes = inFile.getBytes();
                String inBuf = new String(bytes, "UTF-8");
                if (inBuf.contains("\n")) {
                    inString = inBuf.split("\n");
                } else {
                    inString = new String[1];
                    inString[0] = inBuf;
                }
                bytes = outFile.getBytes();
                String outBuf = new String(bytes, "UTF-8");
                if (outBuf.contains("\n")) {
                    outString = outBuf.split("\n");
                } else {
                    outString = new String[1];
                    outString[0] = outBuf;
                }
                if (inString.length != outString.length) {
                    model.addAttribute("result", "The task is not added");
                    model.addAttribute("error", new Exception("int/out files length are different"));
                    return "result";
                }
                for (int i = 0; i < inString.length; i++) {
                    if (inString[i].contains("\r")) {
                        inString[i] = inString[i].replaceAll("\r", "");
                    }
                    if (outString[i].contains("\r")) {
                        outString[i] = outString[i].replaceAll("\r", "");
                    }
                    ioTaskData.add(new TaskData(inString[i], outString[i]));
                }
            } else {
                model.addAttribute("result", "The task is not added");
                model.addAttribute("error", new Exception("in/out files are empty"));
                return "result";
            }
            //validate parameters:
            LinkedList<Exception> result = TaskValidator.validateTask(title,
                    description, timeLimit, memoryLimit, rate, complexity,
                    inputFileName, outputFileName, verificationScript, forumLink);
            if (result.size() != 0) {
                model.addAttribute("result", "The task is not added");
                model.addAttribute("error", result);
                return "result";
            }
            result = TaskValidator.validateTaskData(ioTaskData);
            if (result.size() != 0) {
                model.addAttribute("result", "The task is not added");
                model.addAttribute("error", result);
                return "result";
            }
            result = TaskValidator.validateTheoryLinks(theoryLinksList);
            if (result.size() != 0) {
                model.addAttribute("result", "The task is not added");
                model.addAttribute("error", result);
                return "result";
            }
            //validation past.
            //creating Task:
            Task currTask = new Task();
            currTask.setTitle(title);
            if (memoryLimit != null) {
                currTask.setMemoryLimit(Integer.parseInt(memoryLimit));
            }
            if (timeLimit != null) {
                currTask.setTimeLimit(Integer.parseInt(timeLimit));
            }
            if (rate != null) {
                currTask.setMaxMark(Integer.parseInt(rate));
            }
            if (complexity != null) {
                currTask.setDifficulty(Integer.parseInt(complexity));
            }
            currTask.setDescription(description);
            currTask.setInputFileName(inputFileName);
            currTask.setOutputFileName(outputFileName);
            currTask.setFoumLink(forumLink);

            //add task:
            taskService.addTask(currTask,ioTaskData,theoryLinksList);
        } catch (Exception e) {
            model.addAttribute("result", "The task is not added");
            model.addAttribute("error", e);
            return "result";
        }
        model.addAttribute("result", "Task have been successfully added");
        return "result";
    }
}
