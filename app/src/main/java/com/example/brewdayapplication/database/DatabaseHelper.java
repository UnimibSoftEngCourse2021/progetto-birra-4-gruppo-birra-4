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
                + DataString.COLUMN_ID_INGREDIENTE + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DataString.COLUMN_NOME_INGREDIENTE + " TEXT NOT NULL, "
                + DataString.COLUMN_QUANTITA_MAGAZZINO + " DOUBLE NOT NULL, "
                + DataString.COLUMN_ID_MAGAZZINO + " INTEGER NOT NULL, "
                + " FOREIGN KEY ( " + DataString.COLUMN_ID_MAGAZZINO + " ) REFERENCES " + DataString.MAGAZZINO_TABLE + " ( " + DataString.COLUMN_ID_MAGAZZINO + " ))";

        //creazione tabella ricetta
        String createTableStatementRicetta = " CREATE TABLE " + DataString.RICETTA_TABLE + " ("
                + DataString.COLUMN_ID_RICETTA + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DataString.COLUMN_NOME_RICETTA + " TEXT NOT NULL UNIQUE, "
                + DataString.COLUMN_DATA_RICETTA + " DATE NOT NULL, "
                + DataString.COLUMN_QUANTITA_BIRRA + " DOUBLE NOT NULL )";

        //creazione tabella relazione Ricetta-Ingrediente
        String createTableStatementRelazioneRI = "CREATE TABLE " + DataString.RELAZIONE_TABLE + " ("
                + DataString.COLUMN_ID_RICETTA + "INTEGER, "
                + DataString.COLUMN_ID_INGREDIENTE + "INTEGER, "
                + DataString.COLUMN_QUANTITA_INGREDIENTE_RICETTA + "DOUBLE NOT NULL, "
                + " PRIMARY KEY ( " + DataString.COLUMN_ID_RICETTA + ", " +  DataString.COLUMN_ID_INGREDIENTE + "), "
                + " FOREIGN KEY ( " + DataString.COLUMN_ID_RICETTA + " ) REFERENCES " + DataString.RICETTA_TABLE + " ( " + DataString.COLUMN_ID_RICETTA + " ),"
                + " FOREIGN KEY ( " + DataString.COLUMN_ID_INGREDIENTE + ") REFERENCES " + DataString.INGREDIENTE_TABLE + " ( " + DataString.COLUMN_ID_INGREDIENTE + " ))";


        //esecuzione delle due query
        db.execSQL(createTableStatementMagazzino);
        db.execSQL(createTableStatementIngredient);
        db.execSQL(createTableStatementRicetta);
        db.execSQL(createTableStatementRelazioneRI);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}
