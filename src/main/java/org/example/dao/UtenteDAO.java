package org.example.dao;

import org.example.entities.Utente;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class UtenteDAO {
    private final EntityManager em;

    public UtenteDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Utente utente) {
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(utente);
            transaction.commit();
            System.out.println("Utente " + utente.getNome() + " " + utente.getCognome() + " salvato correttamente!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Utente findById(long userId) {
        return em.find(Utente.class, userId);
    }

    public void findByIdAndDelete(long id) {
        try {
            EntityTransaction t = em.getTransaction();
            Utente found = em.find(Utente.class, id);
            if (found != null) {
                t.begin();
                em.remove(found);
                t.commit();
                System.out.println("Utente eliminato");
            } else System.out.println("Utente non trovato");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
