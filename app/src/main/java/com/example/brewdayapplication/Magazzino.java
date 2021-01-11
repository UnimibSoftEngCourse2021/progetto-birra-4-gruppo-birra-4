package com.example.brewdayapplication;

import java.util.ArrayList;
import java.util.List;

public class Magazzino {

    private int id;
    private double capacita;
    private ArrayList<Ingrediente> dispensaIngrediente;


    public Magazzino(int id, double capacita) {
        this.id = id;
        this.capacita = capacita;
        dispensaIngrediente = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public double getCapacita() {
        return capacita;
    }

    public void setCapacita(double capacita) {
        this.capacita = capacita;
    }

    public List<Ingrediente> getDispensaIngrediente() {
        return dispensaIngrediente;
    }

    //aggiunge un ingrediente al magazzino
    public boolean aggiungiIngrediente(Ingrediente ingrediente) {
        if (ingrediente == null)
            return false;
        else
            dispensaIngrediente.add(ingrediente);
        return true;
    }

    /*se l'ingrediente in input viene trovato nell'array, permette di aggiornare la quantità dell'ingrediente presente nell'array
    * in particolare viene aggiornato con la sua quantità + quantità dell'ingrediente in input
    */
    public void modificaQuantitaIngrediente(Ingrediente ingrediente) {
        int i = 0;
        boolean uguale = true;
        while (i < dispensaIngrediente.size() && uguale) {
            if (dispensaIngrediente.get(i).equals(ingrediente)) {
                uguale = false;
                dispensaIngrediente.get(i).setQuantita(dispensaIngrediente.get(i).getQuantita() + ingrediente.getQuantita());
            }
            i++;
        }
    }


}
