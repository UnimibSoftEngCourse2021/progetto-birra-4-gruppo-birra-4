package com.example.brewdayapplication;

import java.util.Date;
import java.util.List;

public class Ricetta {
    private int idRicetta;
    private String nome;
    private Date dataCreazione;
    private double quantitaBirraProdotta;
    private List<Ingrediente> listIngrediente;

    public Ricetta(String nome, Date dataCreazione, double quantitaBirraProdotta, List<Ingrediente> listIngrediente) {
        this.nome = nome;
        this.dataCreazione = dataCreazione;
        this.dataCreazione.setHours(0);
        this.quantitaBirraProdotta = quantitaBirraProdotta;
        this.listIngrediente = listIngrediente;
    }

    public Ricetta(String nome, Date dataCreazione, List<Ingrediente> listIngrediente){
        new Ricetta(nome, dataCreazione, 1, listIngrediente);
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

    // aggiunge un ingrediente alla ricetta
    public boolean aggiungiIngrediente(Ingrediente ingrediente){
        if (ingrediente == null)
            return false;
        else
            listIngrediente.add(ingrediente);
        return true;
    }

    // rimuove un ingrediente dalla ricette
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
            s.append(listIngrediente.get(i).toString() + " ");
        }
        return s.toString();
    }

    //metodo per visualizzare le informazioni più importanti di una ricetta
    public String toStringMinimo(){
        return getNome() + " " + getDataCreazione().toLocaleString().substring(0,12);
    }

    @Override
    public String toString() {
        return "Ricetta{" +
                "nome='" + nome + '\'' +
                ", dataCreazione=" + dataCreazione +
                '}';
    }
}
