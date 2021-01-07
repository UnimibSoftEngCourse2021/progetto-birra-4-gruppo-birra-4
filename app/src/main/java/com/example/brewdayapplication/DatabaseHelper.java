package com.example.brewdayapplication;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "brew.db";
    private static final String INGREDIENTE_TABLE = "INGREDIENTE";
    private static final String MAGAZZINO_TABLE = "MAGAZZINO";

    private static final String COLUMN_ID_MAGAZZINO = "ID_MAGAZZINO";
    private static final String COLUMN_CAPACITY_EQUIPMENT = "CAPACITA_SET";

    private static final String COLUMN_NOME_INGREDIENTE = "NOME_INGREDIENTE";
    private static final String COLUMN_QUANTITA_MAGAZZINO = "QUANTITA_DISPENSA";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableStatementMagazzino = " CREATE TABLE " + MAGAZZINO_TABLE + " ( "
                + COLUMN_ID_MAGAZZINO + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_CAPACITY_EQUIPMENT + " DOUBLE NOT NULL )";


        String createTableStatementIngredient = " CREATE TABLE " + INGREDIENTE_TABLE + " ("
                + COLUMN_NOME_INGREDIENTE + " TEXT PRIMARY KEY NOT NULL, "
                + COLUMN_QUANTITA_MAGAZZINO + " DOUBLE NOT NULL, "
                + COLUMN_ID_MAGAZZINO + " INTEGER NOT NULL, "
                + " FOREIGN KEY ( " + COLUMN_ID_MAGAZZINO + " ) REFERENCES " + MAGAZZINO_TABLE + " ( " + COLUMN_ID_MAGAZZINO + " ))";

        db.execSQL(createTableStatementMagazzino);
        db.execSQL(createTableStatementIngredient);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
