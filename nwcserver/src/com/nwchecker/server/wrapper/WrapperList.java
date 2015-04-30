package com.nwchecker.server.wrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chicken on 30.04.2015.
 */
public class WrapperList<T> {
    private List<T> dataList = null;

    public WrapperList() {
        this.dataList = new ArrayList<T>();
    }

    public WrapperList(List<T> dataList) {
        this.dataList = dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public List<T> getDataList() {
        return this.dataList;
    }
}
