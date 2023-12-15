package org.example;

import org.example.dao.ElementoDellaBibliotecaDAO;
import org.example.dao.UtenteDAO;
import org.example.entities.Libro;
import org.example.entities.Periodicita;
import org.example.entities.Rivista;
import org.example.entities.Utente;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class Application {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("PROGETTO-U4-S3-15-12-23");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        ElementoDellaBibliotecaDAO ed = new ElementoDellaBibliotecaDAO(em);
        UtenteDAO ud = new UtenteDAO(em);


        Libro libro = new Libro("12nb", "Il Signore degli Anelli", 1954, 1200, "J.R.R. Tolkien", "Fantasy");
        Rivista rivista = new Rivista("13bt", "National Geographic", 2023, 100, Periodicita.MENSILE);
        Rivista rivista2 = new Rivista("13bl", "Novella 2000", 2023, 30, Periodicita.MENSILE);
        Libro libro2 = new Libro("12nt", "Il Signore degli Anelli 2", 1960, 1200, "J.R.R. Tolkien", "Fantasy");
        Utente utente1 = new Utente("Giovanni", "Longo", LocalDate.of(1992, 8, 10), 12);
        Utente utente2 = new Utente("Francesco", "Longo", LocalDate.of(1993, 8, 10), 13);


//        ed.save(libro);
//        ed.save(rivista);
//        ed.save(rivista2);
//        ud.save(utente1);
//        ud.save(utente2);


        ed.getElementByISBN("12nb").forEach(System.out::println);
        ed.searchByPublicationYear(1954).forEach(System.out::println);
        ed.searchByAuthor("J.R.R. Tolkien").forEach(System.out::println);
        ed.searchByTitle("signore").forEach(System.out::println);


        em.close();
        emf.close();
    }
}
