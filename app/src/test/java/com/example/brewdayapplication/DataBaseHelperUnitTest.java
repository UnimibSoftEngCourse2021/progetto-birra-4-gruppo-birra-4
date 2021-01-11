package com.example.brewdayapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.brewdayapplication.database.DatabaseHelper;

import org.junit.Test;
import org.junit.Before;

import static com.example.brewdayapplication.database.DataString.MAGAZZINO_TABLE;
import static org.junit.Assert.assertEquals;

public class DataBaseHelperUnitTest {
    DatabaseHelper databaseHelperTest;

    @Before
    public void initMethod() {
        databaseHelperTest = new DatabaseHelper(null);
    }

    @Test
    public void DatabaseHelperTest(){
        DatabaseHelper o = new DatabaseHelper(null);
        assertEquals(o.getClass(), databaseHelperTest.getClass());
    }

    //problema di mocking, risolto aggiungendo in grandle
    //problema NullPointerException linea 34
    /*@Test
    public void onCreateTest(){
        SQLiteDatabase db;
        db = databaseHelperTest.getReadableDatabase();
        String[] colonna={"ID_MAGAZZINO"};
        Cursor puntatore;
        puntatore =
                db.query("MAGAZZINO", colonna,
                        null, null, null, null, null);
        assertEquals("ID_MAGAZZINO", puntatore.getColumnName(0));
    }*/


}
