/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nwchecker.server.aspect;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface CheckTeacherAccess {
    /*This annotation is used by controller methods where we shoul check if 
     *teacher have access to edit contest. 
     *
     *Contract for using this annotation is:
     *Method shoul receive: 
     *first argument: int contestId;
     *second argument: Principal.
     */
}
