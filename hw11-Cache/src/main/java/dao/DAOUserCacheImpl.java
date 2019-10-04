package dao;

import cachehw.MyCache;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DAOUserCacheImpl<T> implements DAOUser<T> {
    private final SessionFactory sessionFactory;
    private final StandardServiceRegistry serviceRegistry;
    private final MyCache myCache = new MyCache<>();
    private static final Logger logger = LoggerFactory.getLogger(DAOUserCacheImpl.class);

    public DAOUserCacheImpl() {
        logger.info("Создание сессии");
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


    public void save(User objectData) {
        logger.info("Сохранение данных в базу данных и кэш");
        myCache.put(objectData.getId(), objectData);
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(objectData);
            session.getTransaction().commit();
            session.close();
        }
    }

    @Override
    public <T> T read(long id, Class<T> clazz) {
        if (myCache.isKey(id)) {
            T temp= (T) myCache.get(id);
            logger.info("Четение из кэша ключа:{}, и значения:{}", id, temp);
            return temp;
        } else {
            try (Session session = sessionFactory.openSession()) {
                T temp = session.get(clazz, id);
                session.close();
                logger.info("Четение из БД ключа:{}, и значения:{}", id, temp);
                return temp;
            }
        }
    }
}



