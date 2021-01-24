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
        this.quantitaBirraProdotta = quantitaBirraProdotta;
        this.listIngrediente = listIngrediente;
    }

    public Ricetta(String nome, Date dataCreazione, List<Ingrediente> listIngrediente) {
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
    public boolean aggiungiIngrediente(Ingrediente ingrediente) {
        if (ingrediente == null)
            return false;
        else
            listIngrediente.add(ingrediente);
        return true;
    }

    // rimuove un ingrediente dalla ricette
    public boolean eliminaIngrediente(Ingrediente ingrediente) {
        if (ingrediente == null)
            return false;
        else
            listIngrediente.remove(ingrediente);
        return true;
    }


    public String convertiData() {
        String dataOriginale = dataCreazione.toLocaleString().substring(0, 18);
        String mese = dataOriginale.substring(0, 3);
        String giorno = dataOriginale.substring(4, 6);
        String anno = dataOriginale.substring(8, 12);
        String ora = dataOriginale.substring(13,18);
        switch (mese) {
            case "Jan":
                mese = "01";
                break;
            case "Feb":
                mese = "02";
                break;
            case "Mar":
                mese = "03";
                break;
            case "Apr":
                mese = "04";
                break;
            case "May":
                mese = "05";
                break;
            case "Jun":
                mese = "06";
                break;
            case "Jul":
                mese = "07";
                break;
            case "Aug":
                mese = "08";
                break;
            case "Sep":
                mese = "09";
                break;
            case "Oct":
                mese = "10";
                break;
            case "Nov":
                mese = "11";
                break;
            case "Dec":
                mese = "12";
                break;
            default:
                break;
        }
        return giorno + "-" + mese + "-" + anno + " " + ora;
    }


    @Override
    public String toString() {
        return getNome() + " " + convertiData();
    }


}
