package com.nwchecker.server.dao;

import com.nwchecker.server.model.Compiler;

import java.util.List;

/**
 * <h1>Compiler DAO</h1>
 * DAO for working with Compiler Entity table.
 * <p>
 *
 * @author Stanislav Krasovskyi
 * @version 1.0
 * @since 2015-02-22
 */
public interface CompilerDAO {

    /**
     * Returns list of compilers from database.
     * <p>
     *
     * @return List of compilers
     */
    public List<Compiler> getAllCompilers();
    
    public Compiler getCompilerById(int id);

}
