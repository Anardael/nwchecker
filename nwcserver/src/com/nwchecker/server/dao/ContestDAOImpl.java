/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nwchecker.server.dao;

import com.nwchecker.server.model.Contest;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Роман
 */
@Repository("ContestDAO")
public class ContestDAOImpl extends HibernateDaoSupport implements ContestDAO {

    @Autowired
    public void init(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @Override
    public void addContest(Contest c) {
        getHibernateTemplate().save(c);
    }

    @Override
    public void updateContest(Contest c) {
        getHibernateTemplate().saveOrUpdate(c);
    }

    @Override
    public void mergeContest(Contest c) {
        getHibernateTemplate().merge(c);
    }

    @Override
    public List<Contest> getContests() {
        @SuppressWarnings("unchecked")
        List<Contest> result = (List<Contest>) getHibernateTemplate().find("from Contest");
        return result;
    }

    @Override
    public Contest getContestByID(int id) {
        @SuppressWarnings("unchecked")
        List<Contest> result = (List<Contest>) getHibernateTemplate().find("from Contest where id=?", id);
        return result.get(0);
    }

    @Override
    public List<Contest> getContestByStatus(Contest.Status status) {
        return (List<Contest>) getHibernateTemplate().find("from Contest where status=?", status);
    }
}
