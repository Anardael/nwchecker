package com.nwchecker.server.dao;

import com.nwchecker.server.model.Contest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Repository("ContestDAO")
public class ContestDAOImpl extends HibernateDaoSupport implements ContestDAO {

    @Autowired
    public void init(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @Override
    public void addContest(Contest c) { getHibernateTemplate().save(c);
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

    @Transactional
    @Override
    public Contest getContestByID(int id) {
        Session session = getHibernateTemplate().getSessionFactory()
                .getCurrentSession();
        Query query = session
                .createQuery("from Contest where id = :id");
        query.setParameter("id", id);
        return  (Contest) query.list().get(0);

    }

    @SuppressWarnings("unchecked")
	@Override
    @Transactional
    public List<Contest> getContestByStatus(Contest.Status status) {
        Session session = getHibernateTemplate().getSessionFactory()
                .getCurrentSession();
        Query query = session
                .createQuery("from Contest where status = :status");
        query.setParameter("status", status);
        return (List<Contest>) query.list();
    }

    @SuppressWarnings("unchecked")
	@Override
    @Transactional
    public List<Contest> getContestsForRating() {
        Session session = getHibernateTemplate().getSessionFactory()
                .getCurrentSession();
        Query query = session.createQuery("from Contest where (status = :status or (typeContest.dynamic=:dynamic and status= :status)) and hidden =: hidden order by starts desc");
        query.setParameter("status", Contest.Status.ARCHIVE);
        query.setParameter("dynamic", true);
        query.setParameter("status", Contest.Status.GOING);
        query.setParameter("hidden", false);
        return (List<Contest>) query.list();
    }

    @SuppressWarnings("unchecked")
	@Transactional
    @Override
    public List<Contest> getUnhiddenContests() {
        Session session = getHibernateTemplate().getSessionFactory()
                .getCurrentSession();
        Query query = session
                .createQuery("from Contest where hidden = :hidden");
        query.setParameter("hidden", false);
        return (List<Contest>) query.list();
    }

    @SuppressWarnings("unchecked")
	@Transactional
    @Override
    public List<Contest> getHiddenContestsByUserId(int userId) {
        Session session = getHibernateTemplate().getSessionFactory()
                .getCurrentSession();
        Query query = session
                .createQuery("select c from Contest as c join c.users as cu + where(cu.userId=:userId and hidden=:hidden)");
        query.setParameter("userId", userId);
        query.setParameter("hidden", true);
        return (List<Contest>) query.list();
    }

    @SuppressWarnings("unchecked")
	@Transactional
    @Override
    public List<Contest> getHiddenContestsByUserIdAndStatus(int userId, Contest.Status status) {
        Session session = getHibernateTemplate().getSessionFactory()
                .getCurrentSession();
        Query query = session
                .createQuery("select c from Contest as c join c.users as cu" +
                " where cu.userId=:userId and c.hidden=:hidden and c.status=:status");
        query.setParameter("userId", userId);
        query.setParameter("hidden", true);
        query.setParameter("status", status);
        return (List<Contest>) query.list();
    }

    @SuppressWarnings("unchecked")
	@Transactional
    @Override
    public List<Contest> getUnhiddenContestsByUserId(int userId) {
        Session session = getHibernateTemplate().getSessionFactory()
                .getCurrentSession();
        Query query = session.createQuery("select c from Contest as c join c.users as cu" +
                " where (cu.userId=:userId and hidden =:hidden)");
        Query query2 = session.createQuery("from Contest where hidden=:hidden");
        query.setParameter("userId",userId);
        query.setParameter("hidden", true);
        query2.setParameter("hidden", false);
        List<Contest> contests = new ArrayList<>();
        contests.addAll((List<Contest>) query.list());
        contests.addAll((List<Contest>) query2.list());
        return contests;
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
        Session session = getHibernateTemplate().getSessionFactory()
                .getCurrentSession();
        Query query = session
                .createQuery("from Contest where (status = :status and hidden =: hidden)");
        query.setParameter("status", status);
        query.setParameter("hidden", false);
        return (List<Contest>) query.list();
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