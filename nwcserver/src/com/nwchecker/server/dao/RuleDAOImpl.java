package com.nwchecker.server.dao;


import com.nwchecker.server.model.Contest;
import com.nwchecker.server.model.Rule;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("RuleDAO")
public class RuleDAOImpl extends HibernateDaoSupport implements RuleDAO {

    @Autowired
    public void init(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @Override
     
    public void createRule(Rule rule) {
        getHibernateTemplate().save(rule);
    }

    @Override
     
    public Rule readRuleById(int id) {
        return getHibernateTemplate().load(Rule.class, id);
    }

    @Override
     
    public void updateRule(Rule rule) {
        getHibernateTemplate().update(rule);
    }

    @Override
     
    public void deleteRuleById(int id) {
        getHibernateTemplate().delete(getHibernateTemplate().load(Rule.class, id));
    }

    @SuppressWarnings("unchecked")
	@Override
     
    public List<Rule> getRulesByLanguageTag(String tag) {
        Session session = getHibernateTemplate().getSessionFactory()
                .getCurrentSession();
        Query query = session
                .createQuery("select r from Rule as r join r.language as rl where rl.tag=:tag");
        query.setParameter("tag", tag);
        return (List<Rule>) query.list();
    }

    @Override
     
    public void updateRuleContentById(int id, String content) {
        Session session = getHibernateTemplate().getSessionFactory()
                .getCurrentSession();
        Query query = session
                .createQuery("update Rule set content = :content where id = :id")
                .setParameter("id", id)
                .setParameter("content", content);
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
	@Override
     
    public List<Rule> getAllRules() {
        List<Rule> result = (List<Rule>) getHibernateTemplate().find("from Rule");
        return result;
    }
}
