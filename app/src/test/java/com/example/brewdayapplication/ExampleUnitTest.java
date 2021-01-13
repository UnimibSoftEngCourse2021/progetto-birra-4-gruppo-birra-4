package com.example.brewdayapplication;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void creazioneIngrediente_isCorrect() {
        Ingrediente ingrediente = new Ingrediente(1,"malto", 12);
        assertEquals(12, ingrediente.getQuantita(), 0);
        assertEquals("malto", ingrediente.getNome());
    }


}