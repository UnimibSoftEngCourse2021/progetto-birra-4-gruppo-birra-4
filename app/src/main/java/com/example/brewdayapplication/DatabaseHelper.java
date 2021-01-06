package com.example.brewdayapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME="brew.db";
    private static final String INGREDIENTE_TABLE = "INGREDIENTE";
    private static final String BIRRAIO_TABLE = "BIRRAIO";

    private static final String COLUMN_ID_BIRRAIO = "_ID_BIRRAIO";
    private static final String COLUMN_NAME_BIRRAIO = "NOME";
    private static final String COLUMN_SURNAME = "COGNOME";
    private static final String COLUMN_CAPACITY_EQUIPMENT = "CAPACITA_SET";

    private static final String COLUMN_NOME_INGREDIENTE = "NOME_INGREDIENTE";
    private static final String COLUMN_QUANTITA_DISPENSA = "QUANTITA_DISPENSA";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableStatementBirraio = " CREATE TABLE " + BIRRAIO_TABLE + " ( "
                + COLUMN_ID_BIRRAIO + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME_BIRRAIO + " TEXT NOT NULL, "
                + COLUMN_SURNAME + " TEXT NOT NULL, "
                + COLUMN_CAPACITY_EQUIPMENT + " DOUBLE NOT NULL )";


        String createTableStatementIngredient = " CREATE TABLE " + INGREDIENTE_TABLE + " ("
                + COLUMN_NOME_INGREDIENTE + " TEXT PRIMARY KEY NOT NULL, "
                + COLUMN_QUANTITA_DISPENSA + " DOUBLE NOT NULL,"
                + COLUMN_ID_BIRRAIO + " INTEGER NOT NULL, " +
                " FOREIGN KEY ( " + COLUMN_ID_BIRRAIO + " ) REFERENCES " + BIRRAIO_TABLE + " ( " + COLUMN_ID_BIRRAIO + " )" + " )";

        db.execSQL(createTableStatementBirraio);
        db.execSQL(createTableStatementIngredient);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
