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
        this.quantita = quantita;
    }

    @Override
    public String toString() {
        return "Ingrediente{" +
                "nome='" + nome + '\'' +
                ", quantita=" + quantita +
                '}';
    }
}
