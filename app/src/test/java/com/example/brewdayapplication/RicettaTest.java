package com.example.brewdayapplication;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class RicettaTest {

    Ricetta ricettaTest;
    String data;
    List<Ingrediente> listaIngredienteTest;
    Ingrediente ingredienteTest;

    @Before
    public void initMethod(){
        data = "1-1-2020";
        listaIngredienteTest = new ArrayList<>();
        ingredienteTest = new Ingrediente("acqua", 1);
        listaIngredienteTest.add(ingredienteTest);
        ricettaTest = new Ricetta("birra", data, 1, listaIngredienteTest);
    }

    @Test
    public void getTests(){
        assertEquals("01-01-2020 00:00", ricettaTest.getDataCreazione());
        assertEquals("birra", ricettaTest.getNome() );
        assertEquals(1, ricettaTest.getQuantitaBirraProdotta(),  0.01);
    }

    @Test
    public void setTests(){
        ricettaTest.setDataCreazione();
        ricettaTest.setNome("birraChiara");
        ricettaTest.setQuantitaBirraProdotta(2);
        assertEquals("01-01-2020 00:00", ricettaTest.getDataCreazione());
        assertEquals("birraChiara", ricettaTest.getNome());
        assertEquals(2, ricettaTest.getQuantitaBirraProdotta(), 0.01);
    }

    @Test
    public void setDispensaIngredienteTest(){
        List<Ingrediente> listaIngredienti = new ArrayList<>();
        Ingrediente ingrediente1 = new Ingrediente("acqua", 10);
        Ingrediente ingrediente2 = new Ingrediente("orzo", 11);
        Ingrediente ingrediente3 = new Ingrediente("luppolo", 12);
        listaIngredienti.add(ingredienteTest);
        listaIngredienti.add(ingrediente1);
        listaIngredienti.add(ingrediente2);
        listaIngredienti.add(ingrediente3);
        ricettaTest.setDispensaIngrediente(listaIngredienti);
        assertEquals(listaIngredienti, ricettaTest.getDispensaIngrediente());
    }

    @Test
    public void aggiungiIngredienteTest(){
        Ingrediente ingrediente1 = new Ingrediente("malto", 10);
        ricettaTest.aggiungiIngrediente(ingrediente1);
        assertFalse(ricettaTest.aggiungiIngrediente(null));
        assertTrue(ricettaTest.aggiungiIngrediente(ingrediente1));
        assertEquals(ingrediente1, ricettaTest.getDispensaIngrediente().get(1));
    }

    @Test
    public void getDispensaIngredienteTest(){
        List<Ingrediente> listaIngredienti = new ArrayList<>();
        listaIngredienti.add(ingredienteTest);
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

    @Test
    public void toStringTest(){
        assertEquals("Ricetta{" +
                "nome='" + "birra" + '\'' +
                ", dataCreazione=" + "01-01-2020 00:00" +
                '}', ricettaTest.toString());
    }

}
