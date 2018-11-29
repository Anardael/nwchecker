package com.nwchecker.server.dao;

import com.nwchecker.server.model.Contest;
import com.nwchecker.server.model.ContestPass;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("ContestPassDAO")
public class ContestPassDAOImpl extends HibernateDaoSupport implements ContestPassDAO {

    @Autowired
    public void init(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @Override
    @Transactional
    public void saveContestPass(ContestPass contestPass) {
        getHibernateTemplate().save(contestPass);
    }

    @Override
    @Transactional
    public void updateContestPass(ContestPass contestPass) {
        Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        session.update(contestPass);        		
    }

    @SuppressWarnings("unchecked")
	@Override
    @Transactional
    public List<ContestPass> getContestPasses(int contestId) {
        Session session = getHibernateTemplate().getSessionFactory()
                .getCurrentSession();
        Query query = session
                .createQuery("from ContestPass where contest_id =: contentId");
        query.setParameter("contentId", contestId);
        return (List<ContestPass>) query.list();
    }
    
    @SuppressWarnings("unchecked")
	@Override
    @Transactional
	public List<ContestPass> getValidContestPasses(int contestId) {
        Session session = getHibernateTemplate().getSessionFactory()
                .getCurrentSession();
        Query query = session
                .createQuery("from ContestPass where contest_id =: contentId and rank > 0");
        query.setParameter("contentId", contestId);
        return (List<ContestPass>) query.list();
	}

    @SuppressWarnings("unchecked")
	@Override
    @Transactional
    public ContestPass getContestPassByUserIdAndContestId(int userId, int contestId){
        Session session = getHibernateTemplate().getSessionFactory()
                .getCurrentSession();
        Query query = session
                .createQuery("from ContestPass where contest_id=:contestId and user_userId=:user_userId");
        query.setParameter("contestId", contestId);
        query.setParameter("user_userId", userId);
        return (ContestPass)query.list().get(0);
    }

	
}
