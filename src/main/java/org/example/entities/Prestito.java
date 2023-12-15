package org.example.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "prestito")
public class Prestito {
    @Id
    @GeneratedValue
    private long prestitoId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "elemento_id")
    private ElementoDellaBiblioteca elementoPrestato;

    private LocalDate startDate;
    private LocalDate restituzioneDate;
    private LocalDate returnDate;

    public Prestito() {
    }

    public Prestito(Utente utente, ElementoDellaBiblioteca elementoPrestato, LocalDate startDate) {
        this.utente = utente;
        this.elementoPrestato = elementoPrestato;
        this.startDate = startDate;
        this.restituzioneDate = startDate.plusDays(30);
        this.returnDate = null;
    }

    public Long getPrestitoId() {
        return prestitoId;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public ElementoDellaBiblioteca getElementoPrestato() {
        return elementoPrestato;
    }

    public void setElementoPrestato(ElementoDellaBiblioteca elementoPrestato) {
        this.elementoPrestato = elementoPrestato;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "Prestito{" +
                "prestitoId=" + prestitoId +
                ", utente=" + utente +
                ", elementoPrestato=" + elementoPrestato +
                ", startDate=" + startDate +
                ", restituzioneDate=" + restituzioneDate +
                ", returnDate=" + returnDate +
                '}';
    }
}
