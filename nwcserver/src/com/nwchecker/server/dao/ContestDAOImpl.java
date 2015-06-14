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

    @SuppressWarnings("unchecked")
	@Override
    @Transactional
    public List<Contest> getContestByStatus(Contest.Status status) {
        return (List<Contest>) getHibernateTemplate().find("from Contest where status=?", status);
    }

    @SuppressWarnings("unchecked")
	@Override
    @Transactional
    public List<Contest> getContestsForRating() {
        return (List<Contest>) getHibernateTemplate().find("from Contest where " +
                        "(status=?  or (typeContest.dynamic=? and status=?)) and hidden=? order by starts desc",
                Contest.Status.ARCHIVE, true, Contest.Status.GOING, false);
    }

    @SuppressWarnings("unchecked")
	@Transactional
    @Override
    public List<Contest> getUnhiddenContests() {
        return (List<Contest>) getHibernateTemplate().find("from Contest where hidden=?", false);
    }

    @SuppressWarnings("unchecked")
	@Transactional
    @Override
    public List<Contest> getHiddenContestsByUserId(int userId) {
        return (List<Contest>) getHibernateTemplate().find("select c from Contest as c join c.users as cu" +
                " where (cu.userId=? and hidden=?)", userId, true);
    }

    @SuppressWarnings("unchecked")
	@Transactional
    @Override
    public List<Contest> getHiddenContestsByUserIdAndStatus(int userId, Contest.Status status) {
        return (List<Contest>) getHibernateTemplate().find("select c from Contest as c join c.users as cu" +
                " where cu.userId=? and c.hidden=? and c.status=?", userId, true, status);
    }

    @SuppressWarnings("unchecked")
	@Transactional
    @Override
    public List<Contest> getUnhiddenContestsByUserId(int userId) {
        List<Contest> result = (List<Contest>) getHibernateTemplate().find("select c from Contest as c join c.users as cu" +
                " where (cu.userId=? and hidden=?)", userId, true);
        result.addAll((List<Contest>) getHibernateTemplate().find("from Contest where hidden=?", false));

        return result;
    }

    @SuppressWarnings("unchecked")
	@Transactional
    @Override
    public List<Contest> getUnhiddenContestsByUserIdAndStatus(int userId, Contest.Status status) {
        List<Contest> result = (List<Contest>) getHibernateTemplate().find("select c from Contest as c join c.users as cu" +
                " where cu.userId=? and c.hidden=? and c.status=?", userId, true, status);
        result.addAll((List<Contest>) getHibernateTemplate().find("from Contest where hidden=? and status=?", false, status));

        return result;
    }

    @SuppressWarnings("unchecked")
	@Transactional
    @Override
    public List<Contest> getUnhiddenContestsByStatus(Contest.Status status) {
        return (List<Contest>) getHibernateTemplate().find("from Contest where (status=? and hidden=?)", status, false);
    }

    @SuppressWarnings("unchecked")
	@Override
    @Transactional
    public Contest getNearestContest() {
        List <Contest> result = (List<Contest>) getHibernateTemplate().find("from Contest where status='RELEASE' and hidden=false order by starts asc");
        return result.isEmpty() ? null : result.get(0);
    }

    @SuppressWarnings("unchecked")
	@Override
    @Transactional
    public Contest getLastArchivedContest(){
        List <Contest> result = (List<Contest>) getHibernateTemplate().find("from Contest where status='ARCHIVE' order by starts desc");
        return result.isEmpty() ? null : result.get(0);
    }
}