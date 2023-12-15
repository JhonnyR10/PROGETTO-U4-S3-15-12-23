package org.example;

import org.example.dao.ElementoDellaBibliotecaDAO;
import org.example.dao.PrestitoDAO;
import org.example.dao.UtenteDAO;
import org.example.entities.*;

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
        PrestitoDAO pd = new PrestitoDAO(em);


        Libro libro = new Libro("12nb", "Il Signore degli Anelli", 1954, 1200, "J.R.R. Tolkien", "Fantasy");
        Rivista rivista = new Rivista("13bt", "National Geographic", 2023, 100, Periodicita.MENSILE);
        Rivista rivista2 = new Rivista("13bl", "Novella 2000", 2023, 30, Periodicita.MENSILE);
        Libro libro2 = new Libro("12nt", "Il Signore degli Anelli 2", 1960, 1200, "J.R.R. Tolkien", "Fantasy");
        Utente utente1 = new Utente("Giovanni", "Longo", LocalDate.of(1992, 8, 10), 12);
        Utente utente2 = new Utente("Francesco", "Longo", LocalDate.of(1993, 8, 10), 13);
        Prestito prestito1 = new Prestito(utente1, libro, LocalDate.of(2023, 12, 15));
        Prestito prestito2 = new Prestito(utente2, rivista, LocalDate.of(2023, 10, 15));
        Prestito prestito3 = new Prestito(utente1, rivista2, LocalDate.of(2022, 10, 15));

        ed.save(libro);
        ed.save(rivista);
        ed.save(rivista2);
        ud.save(utente1);
        ud.save(utente2);
        pd.save(prestito1);
        pd.save(prestito2);
        pd.save(prestito3);
        ed.save(libro2);


        System.out.println("-------------Ricerca per ISBN----------------");
        ed.getElementByISBN("12nb").forEach(System.out::println);
        System.out.println("-------------Ricerca per anno----------------");
        ed.searchByPublicationYear(1954).forEach(System.out::println);
        System.out.println("-------------Ricerca per autore----------------");
        ed.searchByAuthor("J.R.R. Tolkien").forEach(System.out::println);
        System.out.println("-------------Ricerca per titolo----------------");
        ed.searchByTitle("signore").forEach(System.out::println);
        System.out.println("-------------Ricerca per tessera prestiti attivi----------------");
        pd.getPrestitoByTessera(12).forEach(System.out::println);
        System.out.println("-------------Ricerca prestiti scaduti e non riconsegnati----------------");
        pd.getPrestitiScadutiNonRestituiti().forEach(System.out::println);


        em.close();
        emf.close();
    }
}
