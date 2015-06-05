package com.nwchecker.server.utils;


import com.nwchecker.server.model.Contest;

public final class CheckSortType {

    public static boolean isBoolean(String str){
        if (str.equals("true") || str.equals("false")){
            return true;
        } else {
            return false;
        }
    }

    public static boolean isStatus(String str){
        for (Contest.Status status : Contest.Status.values()) {
            if(status.name().equals(str)) { return true; }
        }
        return false;
    }

}
