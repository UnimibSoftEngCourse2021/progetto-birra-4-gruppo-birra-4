package com.example.brewdayapplication;

import android.database.sqlite.SQLiteDatabase;

import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import  org.junit.Ignore;

import static com.example.brewdayapplication.DataString.MAGAZZINO_TABLE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
        assertEquals(db.query(MAGAZZINO_TABLE,null,
                null, null, null, null, null, null).
                getColumnName(0),"ID_MAGAZZINO");
    }


}
