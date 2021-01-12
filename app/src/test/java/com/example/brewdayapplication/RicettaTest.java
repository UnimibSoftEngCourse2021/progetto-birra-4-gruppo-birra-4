package com.example.brewdayapplication;

import org.junit.Test;
import org.junit.Before;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class RicettaTest {

    Ricetta ricettaTest;
    Date data;

    @Before
    public void initMethod(){
        data = new Date("1/1/2020");
        ricettaTest = new Ricetta(1, "birra", data, 1);
    }

    @Test
    public void getTests(){
        assertEquals("Wed Jan 01 00:00:00 CET 2020", ricettaTest.getDataCreazione().toString());
        assertEquals(1, ricettaTest.getIdRicetta());
        assertEquals("birra", ricettaTest.getNome() );
        assertEquals(1, ricettaTest.getQuantitaBirraProdotta(),  0.01);
    }

    @Test
    public void setTests(){
        data.setDate(1/1/2020);
        ricettaTest.setDataCreazione(data);
        ricettaTest.setIdRicetta(2);
        ricettaTest.setNome("birraChiara");
        ricettaTest.setQuantitaBirraProdotta(2);
        assertEquals("Tue Dec 31 00:00:00 CET 2019", ricettaTest.getDataCreazione().toString());
        assertEquals(2, ricettaTest.getIdRicetta() );
        assertEquals("birraChiara", ricettaTest.getNome());
        assertEquals(2, ricettaTest.getQuantitaBirraProdotta(), 0.01);
    }

    @Test
    public void setDispensaIngredienteTest(){
        List<Ingrediente> listaIngredienti = new ArrayList<>();
        Ingrediente ingrediente1 = new Ingrediente("acqua", 10);
        Ingrediente ingrediente2 = new Ingrediente("orzo", 11);
        Ingrediente ingrediente3 = new Ingrediente("luppolo", 12);
        listaIngredienti.add(ingrediente1);
        listaIngredienti.add(ingrediente2);
        listaIngredienti.add(ingrediente3);
        ricettaTest.setDispensaIngrediente(listaIngredienti);
        assertEquals(listaIngredienti, ricettaTest.getDispensaIngrediente());
    }

    @Test
    public void aggiungiIngredienteTest(){
        Ingrediente ingrediente1 = new Ingrediente("acqua", 10);
        assertFalse(ricettaTest.aggiungiIngrediente(null));
        assertTrue(ricettaTest.aggiungiIngrediente(ingrediente1));
        assertEquals(ingrediente1, ricettaTest.getDispensaIngrediente().get(0));
    }

    @Test
    public void getDispensaIngredienteTest(){
        List<Ingrediente> listaIngredienti = new ArrayList<>();
        assertEquals(listaIngredienti, ricettaTest.getDispensaIngrediente());
        Ingrediente ingrediente1 = new Ingrediente("acqua", 10);
        listaIngredienti.add(ingrediente1);
        ricettaTest.setDispensaIngrediente(listaIngredienti);
        assertEquals(listaIngredienti, ricettaTest.getDispensaIngrediente());
    }

    @Test
    public void eliminaIngredienteTest(){
        Ingrediente ingrediente1 = new Ingrediente("acqua", 10);
        ricettaTest.aggiungiIngrediente(ingrediente1);
        assertFalse(ricettaTest.eliminaIngrediente(null));
        assertTrue(ricettaTest.eliminaIngrediente(ingrediente1));
        ricettaTest.eliminaIngrediente(ingrediente1);
    }

}
