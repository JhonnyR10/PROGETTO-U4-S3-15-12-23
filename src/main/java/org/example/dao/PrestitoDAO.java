package org.example.dao;

import org.example.entities.Prestito;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class PrestitoDAO {
    private final EntityManager em;

    public PrestitoDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Prestito prestito) {
        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            em.persist(prestito);
            t.commit();
            System.out.println("Prestito salvato");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public Prestito findById(long prestitoId) {
        return em.find(Prestito.class, prestitoId);
    }

    public void findByIdAndDelete(long prestitoId) {
        try {
            EntityTransaction t = em.getTransaction();
            Prestito found = em.find(Prestito.class, prestitoId);
            if (found != null) {
                t.begin();
                em.remove(found);
                t.commit();
                System.out.println("Prestito eliminato");
            } else System.out.println("Prestito non trovato");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Prestito> getPrestitoByTessera(int nTessera) {
        TypedQuery<Prestito> query = em.createQuery(
                "SELECT p FROM Prestito p WHERE p.utente.numeroTessera = :nTessera AND p.returnDate IS NULL", Prestito.class);
        query.setParameter("nTessera", nTessera);
        return query.getResultList();
    }

    public List<Prestito> getPrestitiScadutiNonRestituiti() {
        TypedQuery<Prestito> query = em.createQuery(
                "SELECT p FROM Prestito p WHERE p.restituzioneDate < CURRENT_DATE AND p.returnDate IS NULL", Prestito.class);
        return query.getResultList();
    }
}
