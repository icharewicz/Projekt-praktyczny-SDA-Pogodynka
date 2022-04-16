package sda.weather.application;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class MeasureRepository extends AbstractDao<Measure>{  // warstwa danych


    protected MeasureRepository() {
        super(Measure.class);
    }

//    public Measure createNewMeasure(Measure measure) {
//        Session session = HibernateUtils.getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//
//        session.persist(measure);
//
//        transaction.commit();
//        session.close();
//
//        return measure;
//
//    }
//
//    public List<Measure> getAllMeasures() {
//        Session session = HibernateUtils.getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//
//        List<Measure> result = session.createNativeQuery("FROM Weather").
//        getResultList();
//
//        transaction.commit();
//        session.close();
//
//        return result;
//    }

}
