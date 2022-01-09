package com.company;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class RepositoryImpl implements Repository{
    private SessionFactory sessionFactory;

    private void init(){
         this.sessionFactory = new Configuration()
                .configure("com/company/hibernate.cfg.xml").buildSessionFactory();

    }
    @Override
    public void create(final Object object) {
        if(sessionFactory == null)
            init();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(object);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(final Object object) {
        if(sessionFactory == null)
            init();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(object);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(final Object object) {
        if(sessionFactory == null)
            init();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(object);
        transaction.commit();
        session.close();
    }

    @Override
    public Object getById(final Class tclass, final long id) {
        if(sessionFactory == null)
            init();
        Session session = sessionFactory.openSession();
        Object obj = session.get(tclass,id);
        session.close();
        return obj;
    }
}
