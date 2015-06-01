package com.nwchecker.server.dao;

import com.nwchecker.server.model.TypeContest;

import java.util.List;

public interface TypeContestDAO {
    void createType(TypeContest typeContest);

    TypeContest readTypeById(int id);

    void updateType(TypeContest typeContest);

    void deleteTypeById(int id);

    List<TypeContest> getAllTypes();
}
