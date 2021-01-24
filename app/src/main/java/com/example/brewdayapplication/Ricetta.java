package com.example.brewdayapplication;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.util.List;
import java.util.Locale;

public class Ricetta {
    private String nome;
    private String dataCreazione;
    private double quantitaBirraProdotta;
    private List<Ingrediente> listIngrediente;
    private final ZoneId zona = ZoneId.of("Europe/Rome");

    public Ricetta(String nome, String dataCreazione, double quantitaBirraProdotta, List<Ingrediente> listIngrediente) {
        this.nome = nome;
        this.dataCreazione = creaDataCreazione(dataCreazione);
        this.quantitaBirraProdotta = quantitaBirraProdotta;
        this.listIngrediente = listIngrediente;
    }

    public Ricetta(String nome, String dataCreazione, List<Ingrediente> listIngrediente) {
        new Ricetta(nome, dataCreazione, 1, listIngrediente);
    }

    private String creaDataCreazione(String dataConv) {
        LocalDate data = LocalDate.now();
        LocalTime ora = LocalTime.now();
        LocalDateTime dataCreazione = LocalDateTime.of(data, ora);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm").withZone(zona).withLocale(Locale.ITALY);
        dataConv = dataCreazione.format(formatter);
        return dataConv;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataCreazione() {
        return dataCreazione;
    }

    public void setDataCreazione(String dataCreazione) {
        creaDataCreazione(dataCreazione);
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


    @Override
    public String toString() {
        return getNome() + " " + getDataCreazione();
    }


}
