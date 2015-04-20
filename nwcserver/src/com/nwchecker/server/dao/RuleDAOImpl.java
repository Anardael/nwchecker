package com.nwchecker.server.dao;


import com.nwchecker.server.model.Rule;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("RuleDAO")
public class RuleDAOImpl extends HibernateDaoSupport implements RuleDAO{

    @Autowired
    public void init(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @Override
    @Transactional()
    public void createRule(Rule rule) {
        getHibernateTemplate().save(rule);
    }

    @Override
    @Transactional()
    public Rule readRuleById(int id) {
        return getHibernateTemplate().load(Rule.class, id);
    }

    @Override
    @Transactional()
    public void updateRule(Rule rule) {
        getHibernateTemplate().update(rule);
    }

    @Override
    @Transactional()
    public void deleteRuleById(int id) {
        getHibernateTemplate().delete(getHibernateTemplate().load(Rule.class, id));
    }

    @Override
    @Transactional()
    public List<Rule> getAllRules() {
        //@SuppressWarnings("unchecked")    //TODO
        List<Rule> result = (List<Rule>) getHibernateTemplate().find("from rules");
        return result;
    }
}
