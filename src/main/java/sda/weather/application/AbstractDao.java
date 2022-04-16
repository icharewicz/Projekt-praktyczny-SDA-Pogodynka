package sda.weather.application;

//klasa abstrakcyjna, zawiera metody do operacji z bazą danych
//Inaczej Data Access Object, to komponent dostarczający jednolisty interfejs
// do komunikacji między aplikacją a źródłem danych, często łączony z
// innymi wzorcami projektowymi. Tworzy spójną całość z wartstwą modelu i serwisową.
//Dzięki DAO aplikacja nie musi znać sposobu oraz miejsce składowania swoich danych
//ewentualne modyfikacje tych czynników nie pociągają ze sobą konieczności
// modyfikowania jej kodu źródłowego.

import jakarta.persistence.EntityManager;
import java.util.List;

public abstract class AbstractDao<T> {

    private final Class<T> clazz;

    protected AbstractDao(Class<T> clazz) {
        this.clazz = clazz;
    }

    //Operacje CRUD wykonujemy pomiędzy begin() a commit()
    //Metoda flush() pozwala zapisać zmiany w bazie przed commitem. Przykładowe zastosowania
    // metody flush(): 1. Zapis zmian przez wykonaniem zapytania SELECT i zwrócenie dzięki
    // temu również nowo zapisanych obiektów. 2.Upewnienie się, że obiektom zostało
    // wygenerowane id. 3. Zapisanie zmian do bazy, żeby umożliwić obsługę błędów

    public T findById(int id) {
        //entityManager.find(Entity.class, 1) -> wyszukanie encji po id
        EntityManager entityManager = HibernateUtils.getEntityManager();
        entityManager.getTransaction().begin();
        T record = entityManager.find(clazz, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return record;
    }

    public List<T> findAll() {
        EntityManager entityManager = HibernateUtils.getEntityManager();
        entityManager.getTransaction().begin();
        List<T> records =
                entityManager.createQuery("from " + clazz.getCanonicalName(), clazz)
                        .getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return records;
    }

    public void add(T record) {
        //entityManager.persist(entity) -> zapis
        EntityManager entityManager = HibernateUtils.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(record);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void update(T record) {
        //entityManager.merge(entity) -> modyfikacja
        EntityManager entityManager = HibernateUtils.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(record);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void remove(T record) {
        //entityManager.remove(entity) -> usunięcie
        EntityManager entityManager = HibernateUtils.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(record);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
