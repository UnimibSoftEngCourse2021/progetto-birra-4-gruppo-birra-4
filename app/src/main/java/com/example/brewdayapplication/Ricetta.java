package com.example.brewdayapplication;

import java.util.Date;
import java.util.List;

public class Ricetta {
    private int idRicetta;
    private String nome;
    private Date dataCreazione;
    private double quantitaBirraProdotta;
    private List<Ingrediente> listIngrediente;

    public Ricetta(int idRicetta, String nome, Date dataCreazione, double quantitaBirraProdotta, List<Ingrediente> listIngrediente) {
        this.idRicetta = idRicetta;
        this.nome = nome;
        this.dataCreazione = dataCreazione;
        this.quantitaBirraProdotta = quantitaBirraProdotta;
        this.listIngrediente = listIngrediente;
    }

    public Ricetta(int idRicetta, String nome, Date dataCreazione, List<Ingrediente> listIngrediente){
        new Ricetta(idRicetta, nome, dataCreazione, 1, listIngrediente);
    }
    public int getIdRicetta() {
        return idRicetta;
    }

    public void setIdRicetta(int idRicetta) {
        this.idRicetta = idRicetta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataCreazione() {
        return dataCreazione;
    }

    public void setDataCreazione(Date dataCreazione) {
        this.dataCreazione = dataCreazione;
    }

    public double getQuantitaBirraProdotta() {
        return quantitaBirraProdotta;
    }

    public void setQuantitaBirraProdotta(double quantitaBirraProdotta) {
        this.quantitaBirraProdotta = quantitaBirraProdotta;
    }

    public List<Ingrediente> getDispensaIngrediente() {
        return listIngrediente;
    }

    public void setDispensaIngrediente(List<Ingrediente> listIngrediente) {
        this.listIngrediente = listIngrediente;
    }

    public boolean aggiungiIngrediente(Ingrediente ingrediente){
        if (ingrediente == null)
            return false;
        else
            listIngrediente.add(ingrediente);
        return true;
    }

    public  boolean eliminaIngrediente(Ingrediente ingrediente){
        if (ingrediente == null)
            return false;
        else
            listIngrediente.remove(ingrediente);
        return true;
    }

    public String toStringAllInformation() {
        StringBuilder s = new StringBuilder("Ricetta{" +
                "nome='" + nome + '\'' +
                ", dataCreazione=" + dataCreazione +
                '}');
        for (int i = 0; i < listIngrediente.size(); i++) {
            s.append(listIngrediente.get(i).toString());
        }
        return s.toString();
    }

    @Override
    public String toString() {
        return "Ricetta{" +
                "nome='" + nome + '\'' +
                ", dataCreazione=" + dataCreazione +
                '}';
    }
}
