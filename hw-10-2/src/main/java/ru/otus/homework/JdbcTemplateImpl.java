package ru.otus.homework;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class JdbcTemplateImpl<T> implements JdbcTemplate<T> {
    private static final String URL = "jdbc:h2:mem:testDB;DB_CLOSE_DELAY=-1";
    private SessionFactory sessionFactory;
    private StandardServiceRegistry serviceRegistry;



    public JdbcTemplateImpl() {

        Configuration configuration = new Configuration()
                .configure("hibernate.cfg.xml");
        serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

    }

    @Override
    public void create(T objectData) {
        Metadata metadata = new MetadataSources(serviceRegistry)
                .addAnnotatedClass(objectData.getClass())
                .addAnnotatedClass(AddressDataSet.class)
                .addAnnotatedClass(PhoneDataSet.class)
                .getMetadataBuilder()
                .build();
        sessionFactory = metadata.getSessionFactoryBuilder().build();

    }

    @Override
    public void update(T objectData) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(objectData);
            session.getTransaction().commit();
        }
    }

    @Override
    public <T> T load(long id, Class<T> clazz) {
        T result;
        try (Session session = sessionFactory.openSession()) {
            System.out.println(session.load(clazz, id));
        }
        return null;
    }
}


