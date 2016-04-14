package com.springapp.mvc.dao;

import com.springapp.mvc.model.Forecast;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by aneskladnyi on 06.04.2016.
 */
@Repository("ForecastDAO")
@Transactional
public class ForecatDaoImpl implements ForecastDAO {
    private Log log = LogFactory.getLog(ForecatDaoImpl.class);
    private SessionFactory sessionFactory;
    @Resource(name="sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public void save(Forecast p) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(p);
        tx.commit();
        session.close();
        log.info("id saved ="+p.getId());
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<Forecast> list() {
        Session session = this.sessionFactory.openSession();
        List<Forecast> personList = session.createQuery("from Forecast f").list();
        session.close();
        return personList;
    }
}
