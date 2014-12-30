/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nwchecker.server.valicators;

import com.nwchecker.server.model.TaskData;
import com.nwchecker.server.model.TaskTheoryLink;
import java.util.LinkedList;

/**
 *
 * @author Ðîìàí
 */
public class TaskValidator {

    public static LinkedList<Exception> validateTask(String title, String description,
            String timeLimit, String memoryLimit, String rate,
            String complexity, String inputFileName, String outputFileName,
            String verificationScript, String forumLink) {
        LinkedList<Exception> result = new LinkedList<Exception>();
        //title:
        if (!title.matches("[0-9a-zA-Zà-ÿ³¿ºÀ-ß²¯ª ]{1,}") || title.length() > 100) {
            result.add(new Exception("Wrong title name"));
        }
        if (description.length() > 5000) {
            result.add(new Exception("wrong description length"));
        }
        if (timeLimit != null) {
            if (!timeLimit.matches("[0-9]{1,4}")) {
                result.add(new Exception("wrong time limit value"));
            }
        }
        if (memoryLimit != null) {
            if (!memoryLimit.matches("[0-9]{1,4}")) {
                result.add(new Exception("wrong memory limit value"));
            }
        }
        if (rate != null) {
            if (!rate.matches("[0-9]{1,3}")) {
                result.add(new Exception("wrong rate value"));
            }
        }
        if (complexity != null) {
            if (!complexity.matches("[0-9]{1,3}")) {
                result.add(new Exception("wrong complexity value"));
            } else {
                if (Integer.parseInt(complexity) > 100) {
                    result.add(new Exception("wrong complexity value (max value=100)"));
                }
            }
        }
        if (!inputFileName.matches("[0-9a-zA-Zà-ÿ³¿ºÀ-ß²¯ª ]{1,}")) {
            result.add(new Exception("wrong input file name"));
        }
        if (inputFileName.length() > 60) {
            result.add(new Exception("wrong input file name length (max value=60)"));
        }
        if (!outputFileName.matches("[0-9a-zA-Zà-ÿ³¿ºÀ-ß²¯ª ]{1,}")) {
            result.add(new Exception("wrong output file name"));
        }
        if (outputFileName.length() > 60) {
            result.add(new Exception("wrong output file name length (max value=60)"));
        }
        if (verificationScript.length() > 1000) {
            result.add(new Exception("wrong verification script length (max value=1000)"));
        }
        if (forumLink.length() > 500) {
            result.add(new Exception("wrong forum Link (max length=500)"));
        }
        if (!forumLink.startsWith("http")) {
            result.add(new Exception("forum link may should starts with \"http\""));
        }
        return result;
    }

    public static LinkedList<Exception> validateTaskData(LinkedList<TaskData> taskData) {
        LinkedList<Exception> result = new LinkedList<Exception>();
        int index = 0;
        for (TaskData tData : taskData) {
            if (tData.getInputData().length() > 100 || tData.getOutputData().length() > 100) {
                result.add(new Exception("wrong in/out data length (max value=100) tData index=Row" + index));
            }
            if (!tData.getInputData().matches("[0-9a-zA-Zà-ÿ³º¿À-ß²ª¯ ]{1,}")) {
                result.add(new Exception("Wrong input data format "
                        + "(can be used only [0-9a-zA-Zà-ÿ³º¿À-ß²ª¯ ] characters) index=Row" + index));
            }
            if (!tData.getOutputData().matches("[0-9a-zA-Zà-ÿ³º¿À-ß²ª¯ ]{1,}")) {
                result.add(new Exception("Wrong output data format "
                        + "(can be used only [0-9a-zA-Zà-ÿ³º¿À-ß²ª¯ ] characters) index=Row" + index));
            }
            index++;
        }
        return result;
    }

    public static LinkedList<Exception> validateTheoryLinks(LinkedList<TaskTheoryLink> theoryLinks) {
        LinkedList<Exception> result = new LinkedList<Exception>();

        return result;
    }
}
