package org.example.entities;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_elemento")
public abstract class ElementoDellaBiblioteca {
    @Id
    @GeneratedValue
    private long id;
    private String isbn;
    private String titolo;
    private int annoPubblicazione;
    private int numeroPagine;

    public ElementoDellaBiblioteca() {
    }

    public ElementoDellaBiblioteca(String isbn, String titolo, int annoPubblicazione, int numeroPagine) {
        this.isbn = isbn;
        this.titolo = titolo;
        this.annoPubblicazione = annoPubblicazione;
        this.numeroPagine = numeroPagine;
    }

    public long getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitolo() {
        return titolo;
    }

    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public int getNumeroPagine() {
        return numeroPagine;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                        ", isbn='" + isbn + '\'' +
                        ", titolo='" + titolo + '\'' +
                        ", annoPubblicazione=" + annoPubblicazione +
                        ", numeroPagine=" + numeroPagine +
                        '}';
    }
}
