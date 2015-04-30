package com.nwchecker.server.wrapper;

import com.nwchecker.server.model.TypeContest;

import java.util.ArrayList;
import java.util.List;


public class TypeContestWrapper {
    private List<TypeContest> typeContestList = null;

    public TypeContestWrapper() {
        this.typeContestList = new ArrayList<TypeContest>();
    }

    public TypeContestWrapper(List<TypeContest> typeContestList) {
        this.typeContestList = typeContestList;
    }

    public void setTypeContestList(List<TypeContest> typeContestList) {
        this.typeContestList = typeContestList;
    }

    public List<TypeContest> getTypeContestList() {
        return this.typeContestList;
    }
}
