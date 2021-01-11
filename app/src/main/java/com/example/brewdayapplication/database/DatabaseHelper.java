package com.example.brewdayapplication.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "brew.db";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //creazione tabella magazzino
        String createTableStatementMagazzino = " CREATE TABLE " + DataString.MAGAZZINO_TABLE + " ( "
                + DataString.COLUMN_ID_MAGAZZINO + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DataString.COLUMN_CAPACITY_EQUIPMENT + " DOUBLE NOT NULL )";

        //creazione tabella ingredienti
        String createTableStatementIngredient = " CREATE TABLE " + DataString.INGREDIENTE_TABLE + " ("
                + DataString.COLUMN_NOME_INGREDIENTE + " TEXT PRIMARY KEY NOT NULL, "
                + DataString.COLUMN_QUANTITA_MAGAZZINO + " DOUBLE NOT NULL, "
                + DataString.COLUMN_ID_MAGAZZINO + " INTEGER NOT NULL, "
                + " FOREIGN KEY ( " + DataString.COLUMN_ID_MAGAZZINO + " ) REFERENCES " + DataString.MAGAZZINO_TABLE + " ( " + DataString.COLUMN_ID_MAGAZZINO + " ))";

        //esecuzione delle due query
        db.execSQL(createTableStatementMagazzino);
        db.execSQL(createTableStatementIngredient);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}