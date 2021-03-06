package com.example.brewdayapplication;
import org.junit.Test;
import org.junit.Before;
import  org.junit.Ignore;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class MagazzinoUnitTest {

    Magazzino magazzino;

    @Before
     public void initMethod() {
        magazzino = new Magazzino( 500);
    }

    @Test
    public void getCapacitaTest(){
        assertEquals(500, magazzino.getCapacita(),0.01);
    }

    @Test
    public void setCapacitaTest(){
        magazzino.setCapacita(200);
        assertEquals( 200, magazzino.getCapacita(),0.01);
    }

    @Test
    public void getDispensaIngredienteTest(){
        List<Ingrediente> listaIngredienti = new ArrayList<>();
        assertEquals(listaIngredienti, magazzino.getDispensaIngrediente());
    }

    @Test
    public void aggiungiIngredientiTest(){
        assertTrue(magazzino.aggiungiIngrediente(new Ingrediente("luppolo", 11.0)));
        assertFalse(magazzino.aggiungiIngrediente(null));
    }

    @Test
    public void modificaQuantitaIngredienteTest(){
        Ingrediente orzo1 = new Ingrediente("orzo", 34.0);
        Ingrediente orzo2 = new Ingrediente("orzo", 100.0);
        magazzino.aggiungiIngrediente(orzo1);
        magazzino.modificaQuantitaIngrediente(orzo2);
        assertEquals(134.0, magazzino.getDispensaIngrediente().get(0).getQuantita(), 0.01);
    }
}
