package org.example.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "utente")
public class Utente {
    @Id
    @GeneratedValue
    private long userId;
    private String nome;
    private String cognome;
    private LocalDate dataDiNascira;
    private int numeroTessera;

    @OneToMany(mappedBy = "utente", cascade = CascadeType.ALL)
    private List<Prestito> prestiti;

    public Utente() {
    }

    public Utente(String nome, String cognome, LocalDate dataDiNascira, int numeroTessera) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascira = dataDiNascira;
        this.numeroTessera = numeroTessera;
    }

    public long getUserId() {
        return userId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDate getDataDiNascira() {
        return dataDiNascira;
    }

    public void setDataDiNascira(LocalDate dataDiNascira) {
        this.dataDiNascira = dataDiNascira;
    }

    public int getNumeroTessera() {
        return numeroTessera;
    }

    public void setNumeroTessera(int numeroTessera) {
        this.numeroTessera = numeroTessera;
    }

    public List<Prestito> getPrestiti() {
        return prestiti;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "userId=" + userId +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", dataDiNascira=" + dataDiNascira +
                ", numeroTessera=" + numeroTessera +
                '}';
    }
}
