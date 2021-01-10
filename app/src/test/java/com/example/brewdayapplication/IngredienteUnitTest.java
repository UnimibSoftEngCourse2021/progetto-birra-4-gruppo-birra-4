package com.example.brewdayapplication;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import  org.junit.Ignore;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IngredienteUnitTest {
    Ingrediente ingrediente;

    @Before
    public void initMethod() {
        ingrediente = new Ingrediente("acqua", 1);
    }

    @Test
    public  void IngredienteTest(){
        Ingrediente o = new Ingrediente("acqua", 1);
        assertEquals(o.getClass(), ingrediente.getClass());
    }

    @Test
    public void getNomeTest(){
        assertEquals(ingrediente.getNome(), "acqua");
    }

    @Test
    public void getQuantitaTest(){
        assertEquals(ingrediente.getQuantita(), 1, 0.01);
    }

    @Test
    public void setNomeTest(){
        ingrediente.setNome("orzo");
        assertEquals(ingrediente.getNome(), "orzo");
    }

    @Test
    public void setQuantitaTest(){
        ingrediente.setQuantita(2);
        assertEquals(ingrediente.getQuantita(), 2, 0.01);
    }

    @Test
    public void toStringTest(){
        assertEquals(ingrediente.toString(),"Ingrediente{" + "nome='" + "acqua" + '\'' + ", quantita=" + "1.0" + '}');
    }

    @Test
    public void equalsTest(){
        Ingrediente o = new Ingrediente("acqua", 1);
        assertTrue(ingrediente.equals(ingrediente));
        assertFalse(ingrediente.equals(null));
        Ingrediente p = new Ingrediente("orzo", 3);
        assertFalse(p.equals(ingrediente));
        assertTrue(o.getNome().equals(ingrediente.getNome()));
    }
}
