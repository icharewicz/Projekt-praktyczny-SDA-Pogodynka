package sda.weather.application;

import jakarta.persistence.EntityManager;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

//HibernateUtil jest klasą pomocniczą (singletonem), która pozwoli nam utworzyć
//tylko jedną instancję SessionFactory jednocześnie, ze względu na duży koszt tego
//procesu.
public class HibernateUtils {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml").build();
            Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder()
                    .build();

            return metadata.getSessionFactoryBuilder().build();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    //EntityManager to „odpowiednik” Session z Hibernate w standardzie JPA.
    // Posiada podobne metody implementujące podstawowy CRUD.
    public static EntityManager getEntityManager() {
        return HibernateUtils.getSessionFactory().createEntityManager();
    }

    public static void shutdown() {
        getSessionFactory().close();
    }

}
