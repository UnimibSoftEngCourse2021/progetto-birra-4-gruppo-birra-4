package com.example.brewdayapplication;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.example.brewdayapplication.database.DatabaseHelper;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;

public class DataBaseHelperUnitTest extends Application{
    DatabaseHelper databaseHelperTest;
    SQLiteDatabase db;

    public void initMethod() {
        //non riempie databaseHelper, gli assegna il valore null
        //getApplicationContext funziona solo nel costruttore
        databaseHelperTest = new DatabaseHelper(getApplicationContext());
    }

    @Test
    public  void prova(){
        assertEquals(null, databaseHelperTest.getWritableDatabase());
    }

    /*@Test
    public void onCreateTest(){
        db = databaseHelperTest.getReadableDatabase();
        String[] colonna={"ID_MAGAZZINO"};
        Cursor puntatore;
        puntatore =
                db.query("MAGAZZINO", colonna,
                        null, null, null, null, null);
        assertEquals("ID_MAGAZZINO", puntatore.getColumnName(0));
    }*/

}
