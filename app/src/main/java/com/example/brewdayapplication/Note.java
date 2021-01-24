package com.example.brewdayapplication;

public class Note {
    private String testo;

    public Note(String testo) {
        this.testo = testo;
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
                ", testo='" + testo + '\'' +
                '}';
    }
}
