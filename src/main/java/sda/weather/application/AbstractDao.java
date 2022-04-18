package sda.weather.application;

//klasa abstrakcyjna, zawiera metody do operacji z bazą danych
//Inaczej Data Access Object, to komponent dostarczający jednolisty interfejs
// do komunikacji między aplikacją a źródłem danych, często łączony z
// innymi wzorcami projektowymi. Tworzy spójną całość z wartstwą modelu i serwisową.
//Dzięki DAO aplikacja nie musi znać sposobu oraz miejsce składowania swoich danych
//ewentualne modyfikacje tych czynników nie pociągają ze sobą konieczności
// modyfikowania jej kodu źródłowego.
// Dla każdej encji musi być osobna klasa, która rozszerza AbstractDao. To oznacza że może
//potem korzytać z jej metod, czlyli już będzie miała wszyskie operacje CRUD.

import jakarta.persistence.EntityManager;
import java.util.List;

public abstract class AbstractDao<AnyType> {

    private final Class<AnyType> anyTypeClass;

    protected AbstractDao(Class<AnyType> anyTypeClass) {
        this.anyTypeClass = anyTypeClass;
    }

    //Operacje CRUD wykonujemy pomiędzy begin() a commit()
    //Metoda flush() pozwala zapisać zmiany w bazie przed commitem. Przykładowe zastosowania
    // metody flush(): 1. Zapis zmian przez wykonaniem zapytania SELECT i zwrócenie dzięki
    // temu również nowo zapisanych obiektów. 2.Upewnienie się, że obiektom zostało
    // wygenerowane id. 3. Zapisanie zmian do bazy, żeby umożliwić obsługę błędów

    public AnyType findById(int id) {
        //find(AnyType.class, 1) -> wyszukanie encji po id
        EntityManager entityManager = HibernateUtils.getEntityManager();
        entityManager.getTransaction().begin();
        AnyType record = entityManager.find(anyTypeClass, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return record;
    }

    public List<AnyType> returnAllRecords() {
        EntityManager entityManager = HibernateUtils.getEntityManager();
        entityManager.getTransaction().begin();
        List<AnyType> records =
                entityManager.createQuery("from " + anyTypeClass.getCanonicalName(), anyTypeClass)
                        .getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return records;
    }

    public AnyType add(AnyType record) {
        //persist -> zapis
        EntityManager entityManager = HibernateUtils.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(record);
        entityManager.getTransaction().commit();
        entityManager.close();
        return record;
    }

    public void update(AnyType record) {
        //merge -> modyfikacja
        EntityManager entityManager = HibernateUtils.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(record);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void remove(AnyType record) {
        //remove -> usunięcie
        EntityManager entityManager = HibernateUtils.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(record);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
