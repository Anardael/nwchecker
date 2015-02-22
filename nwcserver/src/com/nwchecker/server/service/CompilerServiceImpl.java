package com.nwchecker.server.service;

import com.nwchecker.server.dao.CompilerDAO;
import com.nwchecker.server.model.Compiler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Станіслав on 22.02.2015.
 */
@Service(value = "CompilerService")
public class CompilerServiceImpl implements CompilerService {

    @Autowired
    private CompilerDAO compilerDAO;

    @Override
    public List<Compiler> getAllCompilers() {
        return compilerDAO.getAllCompilers();
    }
}
