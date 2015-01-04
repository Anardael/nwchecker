/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nwchecker.server.controller;

import com.nwchecker.server.exceptions.TaskException;
import com.nwchecker.server.model.Task;
import com.nwchecker.server.model.TaskData;
import com.nwchecker.server.model.TaskTheoryLink;
import com.nwchecker.server.service.TaskService;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
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
    public String getTasks(Model model) {
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

    @RequestMapping(value = "/taskCreating")
    public String taskCreating() {
        return "taskCreate";
    }

    @RequestMapping(value = "/addTask", method = RequestMethod.POST)
    public String addTask(Model model,
            @RequestParam("title") String title,
            @RequestParam("memoryLimit") String memoryLimit,
            @RequestParam("timeLimit") String timeLimit,
            @RequestParam("rate") String rate,
            @RequestParam("complexity") String complexity,
            @RequestParam("inputFileName") String inputFileName,
            @RequestParam("outputFileName") String outputFileName,
            @RequestParam("description") String description,
            @RequestParam("verificationScript") String verificationScript,
            @RequestParam("forumLink") String forumLink,
            @RequestParam("theoryLinks[]") List<String> theoryLinks,
            @RequestParam("inputFile") MultipartFile inputFile,
            @RequestParam("outputFile") MultipartFile outputFile
    ) {
        List<TaskException> resultExceptions = new LinkedList<TaskException>();
        //nafig validation
        System.out.println("asdlasdlaslsdl");
        int intMemoryLimit = Integer.parseInt(memoryLimit);
        int intTimeLimit = Integer.parseInt(timeLimit);
        int intRate = Integer.parseInt(rate);
        int intComplexity = Integer.parseInt(complexity);

        Task newTask = new Task();
        newTask.setTitle(title);
        newTask.setMemoryLimit(intMemoryLimit);
        newTask.setTimeLimit(intTimeLimit);
        newTask.setRate(intRate);
        newTask.setComplexity(intComplexity);
        newTask.setInputFileName(inputFileName);
        newTask.setOutputFileName(outputFileName);
        newTask.setDescription(description);
        newTask.setScriptForVerification(verificationScript);
        newTask.setFoumLink(forumLink);
        //theorys add:
        List<TaskTheoryLink> tl = new LinkedList<TaskTheoryLink>();
        for (String s : theoryLinks) {
            tl.add(new TaskTheoryLink(s));
        }
        newTask.setTheoryLinks(tl);

        byte[] inBytes = null;
        byte[] outBytes = null;
        try {
            //Get io data from uploaded files:
            inBytes = inputFile.getBytes();
            outBytes = outputFile.getBytes();
            System.out.println(outBytes);
        } catch (IOException ex) {
            resultExceptions.add(new TaskException("Error while parsing in/out "
                    + "file data. Check files data"));
            model.addAttribute("error", resultExceptions);
            return "result";
        }
        List<TaskData> td = new LinkedList<TaskData>();
        td.add(new TaskData(inBytes, outBytes));
        newTask.setInOutData(td);
        taskService.addTask(newTask);


        /*
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
         taskService.addTask(currTask, ioTaskData, theoryLinksList);
         } catch (Exception e) {
         model.addAttribute("result", "The task is not added");
         model.addAttribute("error", e);
         return "result";
         }
         model.addAttribute("result", "Task have been successfully added");
         return "result";*/
        return "result";
    }
}
