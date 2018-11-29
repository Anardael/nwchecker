package com.nwchecker.server.dao;

import com.nwchecker.server.model.TypeContest;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("TypeDAO")
public class TypeContestDAOImpl extends HibernateDaoSupport implements TypeContestDAO {

    @Autowired
    public void init(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @Override
    @Transactional
    public void createType(TypeContest typeContest) {
        getHibernateTemplate().save(typeContest);
    }

    @Override
    @Transactional
    public TypeContest readTypeById(int id) {
        return getHibernateTemplate().load(TypeContest.class, id);
    }

    @Override
    @Transactional
    public void updateType(TypeContest typeContest) {
        getHibernateTemplate().update(typeContest);
    }

    @Override
    @Transactional
    public void deleteTypeById(int id) {
        getHibernateTemplate().delete(getHibernateTemplate().load(TypeContest.class, id));
    }

    @Override
    @Transactional
    public List<TypeContest> getAllTypes() {
        @SuppressWarnings("unchecked")   
                List<TypeContest> result = (List<TypeContest>) getHibernateTemplate().find("from TypeContest");
        return result;
    }
}
