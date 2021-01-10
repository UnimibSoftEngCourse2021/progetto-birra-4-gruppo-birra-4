package com.example.brewdayapplication;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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
        assertEquals("acqua", ingrediente.getNome());
    }

    @Test
    public void getQuantitaTest(){
        assertEquals( 1, ingrediente.getQuantita(),0.01);
    }

    @Test
    public void setNomeTest(){
        ingrediente.setNome("orzo");
        assertEquals("orzo",ingrediente.getNome());
    }

    @Test
    public void setQuantitaTest(){
        ingrediente.setQuantita(2);
        assertEquals( 2,ingrediente.getQuantita(), 0.01);
    }

    @Test
    public void toStringTest(){
        assertEquals("Ingrediente{" + "nome='" + "acqua" + '\'' + ", quantita=" + "1.0" + '}',ingrediente.toString());
    }

    @Test
    public void equalsTest(){
        Ingrediente o = new Ingrediente("acqua", 1);
        assertEquals(ingrediente, ingrediente);
        assertNotEquals(null, ingrediente);
        Ingrediente p = new Ingrediente("orzo", 3);
        assertNotEquals(p, ingrediente);
        assertEquals(o.getNome(), ingrediente.getNome());
    }
}
