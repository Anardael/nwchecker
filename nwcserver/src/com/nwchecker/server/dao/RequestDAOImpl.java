package com.nwchecker.server.dao;

import com.nwchecker.server.model.Request;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ReaktorDTR on 26.01.2015.
 */
@Repository("requestDAO")
@Transactional
public class RequestDAOImpl extends HibernateDaoSupport implements RequestDAO{

    @Autowired
    public void init(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @Override
    public void addRequest(Request request) {
        getHibernateTemplate().save(request);
    }

    @Override
    public void updateRequest(Request request) {
        getHibernateTemplate().update(request);
    }

    @Override
    public void deleteRequest(Request request) {
        getHibernateTemplate().delete(request);
    }

    @Override
    public List<Request> getListRequestsWithTypeByObjectId(int objectId, String typeRequest) {
        String hql = "SELECT request FROM Request request WHERE request.objectId = " + objectId + " AND typeRequest = '" + typeRequest + "'";
        List <Request> list = (List<Request>) getHibernateTemplate().find(hql);
        return list;
    }
}
