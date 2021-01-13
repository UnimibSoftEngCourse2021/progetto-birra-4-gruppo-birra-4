package com.example.brewdayapplication;


public class Ingrediente {
    private int id;
    private String nome;
    private double quantita;

    public Ingrediente(int id, String nome, double quantita) {
        this.id = id;
        this.nome = nome;
        this.quantita = quantita;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getQuantita() {
        return quantita;
    }

    public void setQuantita(double quantita) {
        if (quantita >= 0)
            this.quantita = quantita;
    }

    @Override
    public String toString() {
       return nome + " quantita: " + quantita + "g";

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingrediente that = (Ingrediente) o;
        return getNome().equals(that.getNome());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
