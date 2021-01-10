package com.example.brewdayapplication;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
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
        magazzino = new Magazzino(23, 500);
    }

    @Test
    public void MagazzinoTest(){
        Magazzino o = new Magazzino(1,1.0);
        assertEquals(o.getClass(), magazzino.getClass());
    }

    @Test
    public void getIdTest(){
        assertEquals(magazzino.getId(), 23);
    }

    @Test
    public void getCapacitaTest(){
        assertEquals(magazzino.getCapacita(), 500, 0.01);
    }

    @Test
    public void setCapacitaTest(){
        magazzino.setCapacita(200);
        assertEquals(magazzino.getCapacita(), 200, 0.01);
    }

    @Ignore //setId non implementato
    public void setIdTest(){
       // magazzino.setId(34);
        assertEquals(magazzino.getId(), 34);
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
        assertEquals(magazzino.getDispensaIngrediente().get(0).getQuantita(), 134.0, 0.01);
    }
}
