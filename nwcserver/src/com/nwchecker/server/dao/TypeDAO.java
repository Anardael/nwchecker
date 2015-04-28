package com.nwchecker.server.dao;

import com.nwchecker.server.model.Type;

import java.util.List;

public interface TypeDAO {
    void createType(Type type);
    Type readTypeById(int id);
    void updateType(Type type);
    void deleteTypeById(int id);
    List<Type> getAllTypes();
}
