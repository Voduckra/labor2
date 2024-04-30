package repo;


import entity.Clients;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.List;
import javax.persistence.*;
import javax.transaction.Transactional;

public class ClientsRepozitory implements IRepo<Clients> {
    @PersistenceContext
    public EntityManager em = Persistence.createEntityManagerFactory("TST").createEntityManager();
    private static final Logger log= LogManager.getLogger(ClientsRepozitory.class);

    @Override
    public void insert(Clients client){
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(client);
            transaction.commit();
            log.info("Клиент добавлен: " + client);
        } catch (Exception e) {
            log.warn(e.getMessage());
            log.info("Ошибка при добавлении клиента: " + client);
        }
    }


    @Override
    @Transactional
    public void delete(Integer id){

        EntityTransaction transaction = em.getTransaction();
        Clients delClients = em.find(Clients.class, id);
        if (delClients != null) {
            transaction.begin();
            delClients.setDelete(false);
            em.merge(delClients);
            transaction.commit();
            log.info("Клиент с id " + id + " был удален.");}
        else{
            log.info("Клиент с id " + id + "не найден");
        }
    }

    @Override
    @Transactional
    public void update(Clients client) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Clients updatedClient = em.merge(client);
            transaction.commit();
            log.info("Клиент обновлен: " + updatedClient);
        } catch (Exception e) {
            log.warn(e.getMessage());
            log.info("Ошибка при обновлении клиента: " + client);
        }


    }

    @Override
    public List<Clients> getAll() {
        return em.createQuery("SELECT c FROM Clients c where isDelete=true", Clients.class).getResultList();
    }


}
