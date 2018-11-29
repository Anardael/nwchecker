package com.nwchecker.server.dao;

import com.nwchecker.server.model.Compiler;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("CompilerDAO")
public class CompilerDAOImpl extends HibernateDaoSupport implements CompilerDAO {

    @Autowired
    public void init(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @Override
    public List<Compiler> getAllCompilers() {
        @SuppressWarnings("unchecked")
        List<Compiler> result = (List<Compiler>) getHibernateTemplate().find("from Compiler");
        return result;
    }
    
    @Override
    public Compiler getCompilerById(int id){
    	Compiler compiler = getHibernateTemplate().get(Compiler.class, id);
    	return compiler;
    }
}
