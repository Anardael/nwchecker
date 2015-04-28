package com.nwchecker.server.dao;

import com.nwchecker.server.model.Type;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("TypeDAO")
public class TypeDAOImpl extends HibernateDaoSupport implements TypeDAO {

    @Autowired
    public void init(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @Override
    @Transactional
    public void createType(Type type) {
        getHibernateTemplate().save(type);
    }

    @Override
    @Transactional
    public Type readTypeById(int id) {
        return getHibernateTemplate().load(Type.class, id);
    }

    @Override
    @Transactional
    public void updateType(Type type) {
        getHibernateTemplate().update(type);
    }

    @Override
    @Transactional
    public void deleteTypeById(int id) {
        getHibernateTemplate().delete(getHibernateTemplate().load(Type.class, id));
    }

    @Override
    @Transactional
    public List<Type> getAllTypes() {
        @SuppressWarnings("unchecked")    //TODO
        List<Type> result = (List<Type>) getHibernateTemplate().find("from Type");
        return result;
    }
}
