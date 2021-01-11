package com.example.brewdayapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.junit.Test;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

import com.example.brewdayapplication.database.DatabaseHelper;
import com.example.brewdayapplication.database.DatabaseManager;


public class DatabaseManagerUnitTest {

     DatabaseManager databaseManagerTest;
    SQLiteDatabase db;

  /* @Before
   public void initMethod(){
       databaseManagerTest = new DatabaseManager(databaseManagerTest.getContext());//non rivela getContext()
    }*/

    /*@Test
    public void saveCapacitaTest(){
        databaseManagerTest.saveCapacita(17);
        db = databaseManagerTest.getDatabaseHelper().getReadableDatabase();
        String[] capacita = {"QUANTITA_DISPENSA"};
        Cursor puntatore;
        puntatore = db.query("MAGAZZINO", capacita,
                null,null,null,null,null);
        assertEquals(puntatore.getDouble(0), 17, 0.01);
    }*/

    /*@Test
    public void saveIngredientTest(){
        Ingrediente ingrediente = new Ingrediente("acqua", 17);
        databaseManagerTest.saveIngredient(ingrediente);
        db = databaseManagerTest.getDatabaseHelper().getReadableDatabase();//mocking e metodo
        String[] nomeQuantita = {"NOME_INGREDIENTE", "QUANTITA_DISPENSA"};
        Cursor puntatore = db.query("INGREDIENTE", nomeQuantita,
                null, null, null, null, null);
        assertEquals(puntatore.getString(0), "acqua");
        assertEquals(puntatore.getDouble(1), 17, 0.01);
        Ingrediente ingrediente1 = new Ingrediente("acqua", 100);
        databaseManagerTest.saveIngredient(ingrediente1);
        db = databaseManagerTest.getDatabaseHelper().getReadableDatabase();
        String[] quantitaAggiornata = {"QUANTITA_DISPENSA"};
        puntatore = db.query("INGREDIENTE", quantitaAggiornata,
                null, null, null, null, null);
        assertEquals(puntatore.getDouble(1), 117, 0.01);
    }*/

    /*@Test
    public void updateIngredientTest(){
        Ingrediente ingrediente1 = new Ingrediente("acqua", 17);
        Ingrediente ingrediente2 = new Ingrediente("acqua", 100);
        databaseManagerTest.updateIngredient(ingrediente1, ingrediente2);
        db = databaseManagerTest.getDatabaseHelper().getReadableDatabase();
        String[] ingrediente = {"QUANTITA_DISPENSA"};
        Cursor puntatore = db.query("INGREDIENTE", ingrediente,
                null,null, null, null, null);
        assertEquals(puntatore.getDouble(1), 117, 0.01);
    }*/

    /*@Test
    public void mostraIngredienteTest(){
        List<Ingrediente> listaIngredientiTest = new ArrayList<>();
        List<Ingrediente> listaIngredientiMethod = new ArrayList<>();
        Ingrediente ingrediente1, ingrediente2, ingrediente3;
        ingrediente1 = new Ingrediente("acqua", 10);
        ingrediente2 = new Ingrediente("orzo", 11);
        ingrediente3 = new Ingrediente("luppolo", 12);
        listaIngredientiTest.add(ingrediente1);
        listaIngredientiTest.add(ingrediente2);
        listaIngredientiTest.add(ingrediente3);
        databaseManagerTest.saveIngredient(ingrediente1);
        databaseManagerTest.saveIngredient(ingrediente2);
        databaseManagerTest.saveIngredient(ingrediente3);
        listaIngredientiMethod = databaseManagerTest.mostraIngredienti();
        assertEquals(listaIngredientiTest.get(0), listaIngredientiMethod.get(0));
        assertEquals(listaIngredientiTest.get(1), listaIngredientiMethod.get(1));
        assertEquals(listaIngredientiTest.get(2), listaIngredientiMethod.get(2));
    }*/


}
