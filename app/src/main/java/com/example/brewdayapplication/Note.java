package com.example.brewdayapplication;

public class Note {
    private String titolo;
    private String testo;

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    @Override
    public String toString() {
        return "Note{" +
                "titolo='" + titolo + '\'' +
                ", testo='" + testo + '\'' +
                '}';
    }
}
