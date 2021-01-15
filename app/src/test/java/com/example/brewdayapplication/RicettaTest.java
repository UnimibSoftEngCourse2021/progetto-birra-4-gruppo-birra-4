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
    Date data;
    List<Ingrediente> listaIngredienteTest;
    Ingrediente ingredienteTest;

    @Before
    public void initMethod(){
        data = new Date("1/1/2020");
        listaIngredienteTest = new ArrayList<>();
        ingredienteTest = new Ingrediente("acqua", 1);
        listaIngredienteTest.add(ingredienteTest);
        ricettaTest = new Ricetta("birra", data, 1, listaIngredienteTest);
    }

    @Test
    public void getTests(){
        assertEquals("Wed Jan 01 00:00:00 CET 2020", ricettaTest.getDataCreazione().toString());
        //assertEquals(1, ricettaTest.getIdRicetta());
        assertEquals("birra", ricettaTest.getNome() );
        assertEquals(1, ricettaTest.getQuantitaBirraProdotta(),  0.01);
        //assertEquals(1, ricettaTest.getIdRicetta());
    }

    @Test
    public void setTests(){
        data.setDate(1/1/2020);
        ricettaTest.setDataCreazione(data);
        //ricettaTest.setIdRicetta(2);
        ricettaTest.setNome("birraChiara");
        ricettaTest.setQuantitaBirraProdotta(2);
        //ricettaTest.setIdRicetta(2);
        assertEquals("Tue Dec 31 00:00:00 CET 2019", ricettaTest.getDataCreazione().toString());
        //assertEquals(2, ricettaTest.getIdRicetta() );
        assertEquals("birraChiara", ricettaTest.getNome());
        assertEquals(0.01, ricettaTest.getQuantitaBirraProdotta());
        //assertEquals(2, ricettaTest.getIdRicetta());
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
    public void toStringAllInformationTest(){
        Ingrediente ingrediente = new Ingrediente("orzo", 2);
        ricettaTest.aggiungiIngrediente(ingrediente);
        assertEquals("Ricetta{nome='birra', dataCreazione=Wed Jan 01 00:00:00 CET 2020}" +
                "acqua quantita: 1.0" +
                "gorzo quantita: 2.0g",
                ricettaTest.toStringAllInformation());
    }

    @Test
    public void toStringTest(){
        assertEquals("Ricetta{" +
                "nome='" + "birra" + '\'' +
                ", dataCreazione=" + "Wed Jan 01 00:00:00 CET 2020" +
                '}', ricettaTest.toString());
    }

}
