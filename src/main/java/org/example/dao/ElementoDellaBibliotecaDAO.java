package org.example.dao;

import org.example.entities.ElementoDellaBiblioteca;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class ElementoDellaBibliotecaDAO {
    private final EntityManager em;

    public ElementoDellaBibliotecaDAO(EntityManager em) {
        this.em = em;
    }

    public void save(ElementoDellaBiblioteca elemento) {
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(elemento);
            transaction.commit();
            System.out.println("Elemento " + elemento.getTitolo() + " salvato correttamente!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ElementoDellaBiblioteca findById(long id) {
        return em.find(ElementoDellaBiblioteca.class, id);
    }

    public void findByIdAndDelete(long id) {
        try {
            EntityTransaction t = em.getTransaction();
            ElementoDellaBiblioteca found = em.find(ElementoDellaBiblioteca.class, id);
            if (found != null) {
                t.begin();
                em.remove(found);
                t.commit();
                System.out.println("Elemento eliminato");
            } else System.out.println("Elemento non trovato");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeElementoByISBN(String isbn) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Query deletebyISBNQuery = em.createQuery("DELETE FROM ElementoDellaBiblioteca e WHERE e.isbn = :isbn");
        deletebyISBNQuery.setParameter("isbn", isbn);
        int numeroDiElementiCancellati = deletebyISBNQuery.executeUpdate();
        transaction.commit();

        if (numeroDiElementiCancellati > 0) System.out.println("Elementi eliminati");
        else System.out.println("Non ho trovato nessun elemento con ISBN " + isbn);
    }

    public List<ElementoDellaBiblioteca> getElementByISBN(String isbn) {
        TypedQuery<ElementoDellaBiblioteca> query = em.createQuery(
                "SELECT e FROM ElementoDellaBiblioteca e WHERE e.isbn = :isbn", ElementoDellaBiblioteca.class);
        query.setParameter("isbn", isbn);

        return query.getResultList();
    }

    public List<ElementoDellaBiblioteca> searchByPublicationYear(int anno) {
        TypedQuery<ElementoDellaBiblioteca> query = em.createQuery(
                "SELECT e FROM ElementoDellaBiblioteca e WHERE e.annoPubblicazione = :anno", ElementoDellaBiblioteca.class);
        query.setParameter("anno", anno);
        return query.getResultList();
    }

    public List<ElementoDellaBiblioteca> searchByAuthor(String autore) {
        TypedQuery<ElementoDellaBiblioteca> query = em.createQuery(
                "SELECT e FROM ElementoDellaBiblioteca e WHERE e.autore = :autore", ElementoDellaBiblioteca.class);
        query.setParameter("autore", autore);
        return query.getResultList();
    }

    public List<ElementoDellaBiblioteca> searchByTitle(String titolo) {
        TypedQuery<ElementoDellaBiblioteca> query = em.createQuery(
                "SELECT e FROM ElementoDellaBiblioteca e WHERE LOWER(e.titolo) LIKE LOWER(:titolo)", ElementoDellaBiblioteca.class);
        query.setParameter("titolo", "%" + titolo + "%");
        return query.getResultList();
    }
}
