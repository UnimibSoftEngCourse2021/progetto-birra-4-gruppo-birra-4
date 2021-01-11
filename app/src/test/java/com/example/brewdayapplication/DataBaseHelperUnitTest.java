package com.example.brewdayapplication;

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

    //problema di mocking
    @Test
    public void onCreateTest(){
        SQLiteDatabase db;
        db = databaseHelperTest.getReadableDatabase();
        assertEquals("ID_MAGAZZINO", db.query(MAGAZZINO_TABLE,null,
                null, null, null, null, null, null).
                getColumnName(0));
    }


}
