package com.nwchecker.server.dao;

import com.nwchecker.server.model.ContestPass;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * Created by Роман on 21.02.2015.
 */
@Repository("ContestPassDAO")
public class ContestPassDAOImpl extends HibernateDaoSupport implements ContestPassDAO {

    @Autowired
    public void init(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @Override
    public void saveContestPass(ContestPass contestPass) {
        getHibernateTemplate().save(contestPass);
    }

    @Override
    public void updateContestPass(ContestPass contestPass) {
        getHibernateTemplate().update(contestPass);
    }
}
