package otus.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import otus.model.User;

import java.util.List;


public class DAOUserImpl<T> implements DAOUser<T> {
    private SessionFactory sessionFactory;
    private StandardServiceRegistry serviceRegistry;
    private static Logger logger = LoggerFactory.getLogger(DAOUserImpl.class);

    public DAOUserImpl() {
        Configuration configuration = new Configuration()
                .configure("hibernate.cfg.xml");
        serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        Metadata metadata = new MetadataSources(serviceRegistry)
                .addAnnotatedClass(User.class)
                .getMetadataBuilder()
                .build();
        sessionFactory = metadata.getSessionFactoryBuilder().build();
    }

    @Override
    public void save(T objectData) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(objectData);
            session.getTransaction().commit();
            session.close();
            logger.info("User save");
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


