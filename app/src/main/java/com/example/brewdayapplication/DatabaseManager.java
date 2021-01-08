package com.example.brewdayapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.provider.ContactsContract;
import android.widget.Toast;

import java.util.ArrayList;

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
       // cv.put(DataString.COLUMN_ID_MAGAZZINO, 1);
        cv.put(DataString.COLUMN_CAPACITY_EQUIPMENT, capacita);
        try {
            db.insert(DataString.MAGAZZINO_TABLE, null, cv);
        } catch (SQLiteException sqle) {
            // Gestione delle eccezioni
        }
    }

    public void saveIngredient(Ingrediente ingrediente, Magazzino magazzino) {
        db = databaseHelper.getWritableDatabase();
        cv = new ContentValues();
        cv.put(DataString.COLUMN_NOME_INGREDIENTE, ingrediente.getNome());
        cv.put(DataString.COLUMN_QUANTITA_MAGAZZINO, ingrediente.getQuantita());
        cv.put(DataString.COLUMN_ID_MAGAZZINO, magazzino.getId());
        try {
            db.insert(DataString.INGREDIENTE_TABLE, null, cv);
        } catch (SQLiteException sqle) {
            // Gestione delle eccezioni
        }
    }

    public ArrayList<Ingrediente> mostraIngredienti() {
        ArrayList<Ingrediente> resultList= new ArrayList<Ingrediente>();
        Cursor list_ingredients = null;
        try {
            db = databaseHelper.getReadableDatabase();
            list_ingredients = db.query(DataString.INGREDIENTE_TABLE, new String[]{DataString.COLUMN_NOME_INGREDIENTE, DataString.COLUMN_QUANTITA_MAGAZZINO}, null,
                    null, null, null, DataString.COLUMN_NOME_INGREDIENTE);
            if (list_ingredients.moveToNext()){
                do {
                    String nome_ingrediente = list_ingredients.getString(0);
                    int quantita = list_ingredients.getInt(1);
                    Ingrediente ingrediente = new Ingrediente(nome_ingrediente,quantita);
                    resultList.add(ingrediente);
                }while (list_ingredients.moveToNext());
            }
        } catch (SQLiteException e) {
            list_ingredients.close();
            db.close();
            return null;
        }
        list_ingredients.close();
        return resultList;
    }

    public Cursor getCapacita() {
        Cursor capacita = null;
        try {
            db = databaseHelper.getReadableDatabase();
            capacita = db.query(DataString.MAGAZZINO_TABLE, new String[]{DataString.COLUMN_ID_MAGAZZINO, DataString.COLUMN_CAPACITY_EQUIPMENT}, null,
                    null, null, null, null);
        } catch (SQLiteException e) {
            return null;
        }
        return capacita;
    }


}