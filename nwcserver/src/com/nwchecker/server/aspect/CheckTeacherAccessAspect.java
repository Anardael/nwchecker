/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nwchecker.server.aspect;

import com.nwchecker.server.controller.ContestController;
import com.nwchecker.server.json.ValidationResponse;
import com.nwchecker.server.service.ContestService;
import java.lang.reflect.Method;
import java.security.Principal;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

/**
 *
 * @author Роман
 */
@Aspect
public class CheckTeacherAccessAspect {

    @Autowired
    private ContestService contestService;

    private static final Logger LOG
            = Logger.getLogger(ContestController.class);

    //use only in methods where declared annotation:
    @Around("@annotation(com.nwchecker.server.aspect.CheckTeacherAccess)")
    public Object teacherAccessChecker(ProceedingJoinPoint pjp) throws Throwable {
        Object result = null;
        //get invoked method:
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        method.getReturnType();
        //get received args:
        Object[] methodArgs = pjp.getArgs();
        //Contract for annotation which is currently using is described in CheckTeacherAccess.java
        //so, get first argument-contestId:
        int contestId = (int) methodArgs[0];
        //get second argument- Principal:
        Principal principal = (Principal) methodArgs[1];

        //write to log:
        LOG.info("\"" + principal.getName() + "\" tries to edit contest (id=" + contestId + ")");

        //check if teacher have access to edit contest:
        boolean haveAccess = contestService.checkIfUserHaveAccessToContest(principal.getName(), contestId);
        if (haveAccess == true) {
            LOG.info("\"" + principal.getName() + "\" passed verification for contest editing (id=" + contestId + ")");
            //if teacher have access keep going method execution:
            result = pjp.proceed();
        } else {
            LOG.info("\"" + principal.getName() + "\" haven't passed verification for contest editing (id=" + contestId + ")");
            //return error:
            if (method.getReturnType().equals(String.class)) {
                //if controller return string- so it's JSP:
                //return 403:
                return "accessDenied403";
            } else {
                if (method.getReturnType().equals(ValidationResponse.class)) {
                    //controller return JSON, so set FAILES status:
                    ValidationResponse jsonResult = new ValidationResponse();
                    jsonResult.setStatus("FAIL");
                    return jsonResult;
                }
            }
        }
        return result;
    }
}
