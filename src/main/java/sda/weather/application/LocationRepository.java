package sda.weather.application;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class LocationRepository {

    SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    public Location createNewLocation(Location location) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.persist(location);

        transaction.commit();
        session.close();

        HibernateUtils.shutdown(); //?

        return location;
    }

    public List<Location> getAllLocations() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        List<Location> result = session.createNativeQuery("FROM Location").getResultList();

        transaction.commit();
        session.close();

        HibernateUtils.shutdown(); //?

        return result;
    }
}

