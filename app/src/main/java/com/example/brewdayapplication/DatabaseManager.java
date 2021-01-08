package com.example.brewdayapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;


import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private final DatabaseHelper databaseHelper;
    private ContentValues cv;
    private SQLiteDatabase db;

    public DatabaseManager(Context ctx) {
        databaseHelper = new DatabaseHelper(ctx);
    }

    public void saveCapacita(double capacita) {
        db = databaseHelper.getWritableDatabase();
        cv = new ContentValues();
        cv.put(DataString.COLUMN_CAPACITY_EQUIPMENT, capacita);
        try {
            db.insert(DataString.MAGAZZINO_TABLE, null, cv);
        } catch (SQLiteException sqle) {
            // Gestione delle eccezioni
        }
    }

    public void saveIngredient(Ingrediente ingrediente) {
        db = databaseHelper.getWritableDatabase();
        cv = new ContentValues();
        cv.put(DataString.COLUMN_NOME_INGREDIENTE, ingrediente.getNome());
        cv.put(DataString.COLUMN_QUANTITA_MAGAZZINO, ingrediente.getQuantita());
        cv.put(DataString.COLUMN_ID_MAGAZZINO, 1);
        try {
            db.insert(DataString.INGREDIENTE_TABLE, null, cv);
        } catch (SQLiteException sqle) {
            // Gestione delle eccezioni
        }
    }


    public ArrayList<Ingrediente> mostraIngredienti() {
        ArrayList<Ingrediente> resultList = new ArrayList<Ingrediente>();
        Cursor list_ingredients = null;
        try {
            db = databaseHelper.getReadableDatabase();
            list_ingredients = db.query(DataString.INGREDIENTE_TABLE, null, null,
                    null, null, null, null);
            if (list_ingredients.moveToNext()) {
                do {
                    String nome_ingrediente = list_ingredients.getString(0);
                    int quantita = list_ingredients.getInt(1);
                    Ingrediente ingrediente = new Ingrediente(nome_ingrediente, quantita);
                    resultList.add(ingrediente);
                } while (list_ingredients.moveToNext());
            }
        } catch (SQLiteException e) {
            list_ingredients.close();
            db.close();
            return null;
        }
        list_ingredients.close();
        return resultList;
    }

    public Magazzino getMagazzino() {
        Cursor cursor = null;
        int id = 0;
        double capacitaDouble = 0.0;

        try {
            db = databaseHelper.getReadableDatabase();
            cursor = db.query(DataString.MAGAZZINO_TABLE, null, null, null, null, null, null);
            id = cursor.getInt(0);
            capacitaDouble = cursor.getDouble(1);
        } catch (SQLiteException e) {
            /*da gestire*/
        }
        cursor.close();
        return new Magazzino(id, capacitaDouble);
    }


}