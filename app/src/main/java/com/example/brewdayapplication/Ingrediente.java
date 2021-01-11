package com.example.brewdayapplication;


public class Ingrediente {
    private String nome;
    private double quantita;

    public Ingrediente(String nome, double quantita) {
        this.nome = nome;
        this.quantita = quantita;
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
        /* return "Ingrediente{" +
                "nome='" + nome + '\'' +
                ", quantita=" + quantita +
                '}'; */
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
