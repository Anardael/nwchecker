package com.nwchecker.server.dao;

import com.nwchecker.server.model.Language;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("LanguageDAO")
public class LanguageDAOImpl extends HibernateDaoSupport implements LanguageDAO {

    @Autowired
    public void init(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @Override
    @Transactional
    public void createLanguage(Language language) {
        getHibernateTemplate().save(language);
    }

    @Override
    @Transactional
    public Language readLanguageById(int id) {
        return getHibernateTemplate().load(Language.class, id);
    }

    @Override
    @Transactional
    public void updateLanguage(Language language) {
        getHibernateTemplate().update(language);
    }

    @Override
    @Transactional
    public void deleteLanguageById(int id) {
        getHibernateTemplate().delete(getHibernateTemplate().load(Language.class, id));
    }

    @Override
    @Transactional()
    public List<Language> getAllLanguages() {
        @SuppressWarnings("unchecked")    //TODO
                List<Language> result = (List<Language>) getHibernateTemplate().find("from Language");
        return result;
    }
}
