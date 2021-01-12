package com.example.brewdayapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.brewdayapplication.Ingrediente;
import com.example.brewdayapplication.Ricetta;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    private final DatabaseHelper databaseHelper;
    private ContentValues cv;
    private SQLiteDatabase db;

    public DatabaseManager(Context ctx) {
        databaseHelper = new DatabaseHelper(ctx);
    }

    //salva sul db la capacità dell'equipment del birraio
    public void saveCapacita(double capacita) {

        //accende al db in scrittura
        db = databaseHelper.getWritableDatabase();
        // cv è il contenutp da inserire nel db
        cv = new ContentValues();
        cv.put(DataString.COLUMN_CAPACITY_EQUIPMENT, capacita);
        try {
            //inserisce nella tabella magazzino cv che contiene la capacità dell'equipment
            db.insert(DataString.MAGAZZINO_TABLE, null, cv);
        } catch (SQLiteException sqle) {
            // Gestione delle eccezioni
        }
    }

    /*
    *se l'ingrediente in input è presente nel db chiamata updateIngredient(ingrediente, listaIngredienti.get(i))
    passando l'ingrediente presente nell'array e quello dato in input dall''utente
    * se l'ingrediente in input non è presente nel db lo aggiunge
    * return j per capire se si tratta di update o insert
     */
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
        } else {
            db = databaseHelper.getWritableDatabase();
            cv = new ContentValues();
            cv.put(DataString.COLUMN_NOME_INGREDIENTE, ingrediente.getNome());
            cv.put(DataString.COLUMN_QUANTITA_MAGAZZINO, ingrediente.getQuantita());
            cv.put(DataString.COLUMN_ID_MAGAZZINO, 1);
            try {
                db.insert(DataString.INGREDIENTE_TABLE, null, cv);
                j = 2;
            } catch (SQLiteException e) {
                // Gestione delle eccezioni
            }
        }
        return j;
    }

    /*
    * aggiorna la quantità dell'ingrediente avente nome ingrediente.getNome()
    * nuova quantità = vecchia quantità + quantità passata in input dall'utente
    s */
    public void updateIngredient(Ingrediente ingrediente1, Ingrediente ingrediente2) {
        db = databaseHelper.getWritableDatabase();
        cv = new ContentValues();
        //la quantità da inserire deve essere la sommma delle due quantità
        cv.put(DataString.COLUMN_QUANTITA_MAGAZZINO, ingrediente1.getQuantita() + ingrediente2.getQuantita());
        try {
            db.update(DataString.INGREDIENTE_TABLE, cv,
                    DataString.COLUMN_NOME_INGREDIENTE + " = ? ",
                    new String[]{ingrediente1.getNome()});
        } catch (SQLiteException e) {
            // Gestione eccezioni
        }

    }

    /*
     * legge dal db gli ingredienti presenti e ritorna l'arraylist
     */
    public List<Ingrediente> mostraIngredienti() {
        List<Ingrediente> resultList = new ArrayList<>();
        Cursor listIngredients;
        //accesso in lettura al db
        db = databaseHelper.getReadableDatabase();
        //salva nell'array il risultato della select (query = select)
        listIngredients = db.query(DataString.INGREDIENTE_TABLE, null, null, null, null, null, DataString.COLUMN_NOME_INGREDIENTE);

        /*il cursore permette di aumentare riga per riga
         *ad ogni iterazione il cursore si posiziona su un ingrediente della tabella
         *creo un ingrediente avente come nome e quantità quelli puntati dal cursore
         *aggiungo tale ingrediente all'array
         *aumento il cursore per puntare alla prossima riga, quando le righe finiscono esco dal ciclo e returno la lista
         */
        if (listIngredients.moveToNext()) {
            do {
                Ingrediente ingrediente = new Ingrediente(listIngredients.getString(0), listIngredients.getInt(1));
                resultList.add(ingrediente);
            } while (listIngredients.moveToNext());
        } else
            listIngredients.close();
        return resultList;
    }

    //azioni neccessarie solo ai test
    public DatabaseHelper getDatabaseHelper() {
        return databaseHelper;
    }

    //azioni necessarie solo ai test
    public Context getContext() {
        return getContext();

    }

    public void saveRicetta(Ricetta ricetta) {
        db = databaseHelper.getWritableDatabase();
        cv = new ContentValues();
        cv.put(DataString.COLUMN_NOME_RICETTA, ricetta.getNome());
        cv.put(DataString.COLUMN_DATA_RICETTA, ricetta.getDataCreazione().toString());
        cv.put(DataString.COLUMN_QUANTITA_BIRRA, 0);
        try {
            db.insert(DataString.RICETTA_TABLE, null, cv);
            saveRicettario(ricetta);
        } catch (SQLiteException e) {
            // Gestione delle eccezioni
        }
    }

    private void saveRicettario(Ricetta ricetta) {
        db = databaseHelper.getWritableDatabase();
        cv = new ContentValues();
        cv.put(DataString.COLUMN_ID_RICETTA, ricetta.getIdRicetta());
        for (int i = 0; i < ricetta.getDispensaIngrediente().size(); i++) {
            cv.put(DataString.COLUMN_ID_INGREDIENTE, ricetta.getDispensaIngrediente().get(i).getId());
            cv.put(DataString.COLUMN_QUANTITA_INGREDIENTE_RICETTA, ricetta.getDispensaIngrediente().get(i).getQuantita());
        }
        try {
            db.insert(DataString.RELAZIONE_TABLE, null, cv);
        } catch (SQLiteException e) {
            // Gestione delle eccezioni
        }
    }


}

