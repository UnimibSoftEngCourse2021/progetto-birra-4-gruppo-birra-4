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

    public int saveIngredient(Ingrediente ingrediente) {
        int j = 0;
        if (mostraIngredienti().contains(ingrediente)) {
            List<Ingrediente> listaIngredienti = mostraIngredienti();
            for (int i = 0; i < listaIngredienti.size(); i++) {
                if (listaIngredienti.get(i).equals(ingrediente)) {
                    updateIngredient(ingrediente, listaIngredienti.get(i));
                    j = 1;
                }
            }
            /*int index = mostraIngredienti().indexOf(ingrediente);
            updateIngredient(mostraIngredienti().get(index));*/
        } else {
            db = databaseHelper.getWritableDatabase();
            cv = new ContentValues();
            cv.put(DataString.COLUMN_NOME_INGREDIENTE, ingrediente.getNome());
            cv.put(DataString.COLUMN_QUANTITA_MAGAZZINO, ingrediente.getQuantita());
            cv.put(DataString.COLUMN_ID_MAGAZZINO, 1);
            try {
                db.insert(DataString.INGREDIENTE_TABLE, null, cv);
                j = 2;
            } catch (SQLiteException sqle) {
                // Gestione delle eccezioni
            }
        }
        return j;
    }


    public void updateIngredient(Ingrediente ingrediente1, Ingrediente ingrediente2) {
        db = databaseHelper.getWritableDatabase();
        cv = new ContentValues();
        cv.put(DataString.COLUMN_QUANTITA_MAGAZZINO, ingrediente1.getQuantita() + ingrediente2.getQuantita());
        try {
            db.update(DataString.INGREDIENTE_TABLE, cv,
                    DataString.COLUMN_NOME_INGREDIENTE + " = ? ",
                    new String[]{ingrediente1.getNome()});
        } catch (SQLiteException sqle) {

        }
    }

    public List<Ingrediente> mostraIngredienti() {
        List<Ingrediente> resultList = new ArrayList<>();
        Cursor listIngredients = null;

        db = databaseHelper.getReadableDatabase();
        listIngredients = db.query(DataString.INGREDIENTE_TABLE, null, null, null, null, null, DataString.COLUMN_NOME_INGREDIENTE);
        if (listIngredients.moveToNext()) {
            do {
                Ingrediente ingrediente = new Ingrediente(listIngredients.getString(0), listIngredients.getInt(1));
                resultList.add(ingrediente);
            } while (listIngredients.moveToNext());
        } else {
            listIngredients.close();
        }

        return resultList;
    }


}