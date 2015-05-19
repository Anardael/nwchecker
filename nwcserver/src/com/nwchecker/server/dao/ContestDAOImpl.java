package com.nwchecker.server.dao;

import com.nwchecker.server.model.Contest;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("ContestDAO")
public class ContestDAOImpl extends HibernateDaoSupport implements ContestDAO {

    @Autowired
    public void init(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @Override
    @Transactional
    public void addContest(Contest c) {
        getHibernateTemplate().save(c);
    }

    @Override
    @Transactional
    public void updateContest(Contest c) {
        getHibernateTemplate().saveOrUpdate(c);
    }

    @Override
    @Transactional
    public void mergeContest(Contest c) {
        getHibernateTemplate().merge(c);
    }

    @Override
    @Transactional
    public List<Contest> getContests() {
        @SuppressWarnings("unchecked")
        List<Contest> result = (List<Contest>) getHibernateTemplate().find("from Contest");
        return result;
    }

    @Override
    @Transactional
    public Contest getContestByID(int id) {
        @SuppressWarnings("unchecked")
        List<Contest> result = (List<Contest>) getHibernateTemplate().find("from Contest where id=?", id);
        return result.get(0);
    }

    @Override
    @Transactional
    public List<Contest> getContestByStatus(Contest.Status status) {
        return (List<Contest>) getHibernateTemplate().find("from Contest where status=?", status);
    }

    @Override
    @Transactional
    public List<Contest> getContestsWithDynamicRating() {
        return (List<Contest>) getHibernateTemplate().find("from Contest where typeContest.dynamic=?", true);
    }

    @Override
    @Transactional
    public List<Contest> getContestsForRating() {
        return (List<Contest>) getHibernateTemplate().find("from Contest where " +
                        "(status=?  or (typeContest.dynamic=? and status=?)) and hidden=? order by starts desc",
                Contest.Status.ARCHIVE, true, Contest.Status.GOING, false);
    }
}
