package com.example.brewdayapplication;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Ricetta {
    private int idRicetta;
    private String nome;
    private Date dataCreazione;
    private double quantitaBirraProdotta;
    private List<Ingrediente> dispensaIngrediente;

    public Ricetta(int idRicetta, String nome, Date dataCreazione, double quantitaBirraProdotta) {
        this.idRicetta = idRicetta;
        this.nome = nome;
        this.dataCreazione = dataCreazione;
        this.quantitaBirraProdotta = quantitaBirraProdotta;
        dispensaIngrediente = new ArrayList<>();
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
        return dispensaIngrediente;
    }

    public void setDispensaIngrediente(List<Ingrediente> dispensaIngrediente) {
        this.dispensaIngrediente = dispensaIngrediente;
    }

    public boolean aggiungiIngrediente(Ingrediente ingrediente){
        if (ingrediente == null)
            return false;
        else
            dispensaIngrediente.add(ingrediente);
        return true;
    }

    public  boolean eliminaIngrediente(Ingrediente ingrediente){
        if (ingrediente == null)
            return false;
        else
            dispensaIngrediente.remove(ingrediente);
        return true;
    }







}
