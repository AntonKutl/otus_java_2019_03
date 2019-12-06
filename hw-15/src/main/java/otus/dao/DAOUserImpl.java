package otus.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DAOUserImpl<T> implements DAOUser<T> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(T objectData) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(objectData);
            session.getTransaction().commit();
            session.close();
        }
    }

    public List<T>  readAll (){
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM User";
            Query query = session.createQuery(hql);
            List<T> results = query.list();
            session.close();
            return results;
        }
    }
}


