package com.example.brewdayapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;


import com.example.brewdayapplication.Ingrediente;
import com.example.brewdayapplication.Note;
import com.example.brewdayapplication.Ricetta;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DatabaseManager {

    private final DatabaseHelper databaseHelper;
    private ContentValues cv;
    private SQLiteDatabase db;
    private List<Ingrediente> listaIngredienti;
    private Cursor listaIngredientiCursor;

    private final ZoneId zona = ZoneId.of("Europe/Rome");


    public DatabaseManager(Context ctx) {
        databaseHelper = new DatabaseHelper(ctx);
    }

    //salva sul db la capacità dell'equipment del birraio
    public void saveCapacita(double capacita) {
        //accende al db in scrittura
        db = databaseHelper.getWritableDatabase();
        // cv è il contenuto da inserire nel db
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
     * se l'ingrediente in input è presente nel db chiamata updateIngredient(ingrediente, listaIngredienti.get(i))
     * passando l'ingrediente presente nell'array e quello dato in input dall''utente
     * se l'ingrediente in input non è presente nel db lo aggiunge
     * return j per capire se si tratta di update o insert
     */
    public int saveIngredient(Ingrediente ingrediente) {
        int j = 0;
        if (mostraIngredienti().contains(ingrediente)) {
            listaIngredienti = mostraIngredienti();
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
     */
    private void updateIngredient(Ingrediente ingrediente1, Ingrediente ingrediente2) {
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
        listaIngredienti = new ArrayList<>();
        //accesso in lettura al db
        db = databaseHelper.getReadableDatabase();
        //salva nell'array il risultato della select (query = select)
        listaIngredientiCursor = db.query(DataString.INGREDIENTE_TABLE, null, null, null, null, null, DataString.COLUMN_NOME_INGREDIENTE);

        /*il cursore permette di aumentare riga per riga
         *ad ogni iterazione il cursore si posiziona su un ingrediente della tabella
         *creo un ingrediente avente come nome e quantità quelli puntati dal cursore
         *aggiungo tale ingrediente all'array
         *aumento il cursore per puntare alla prossima riga, quando le righe finiscono esco dal ciclo e returno la lista
         */
        if (listaIngredientiCursor.moveToNext()) {
            do {
                Ingrediente ingrediente = new Ingrediente(listaIngredientiCursor.getString(1), listaIngredientiCursor.getDouble(2));
                listaIngredienti.add(ingrediente);
            } while (listaIngredientiCursor.moveToNext());
        } else
            listaIngredientiCursor.close();
        return listaIngredienti;
    }

    //salva la ricetta passata in input dall'utente sul db
    public void saveRicetta(Ricetta ricetta) {
        db = databaseHelper.getWritableDatabase();
        cv = new ContentValues();
        cv.put(DataString.COLUMN_NOME_RICETTA, ricetta.getNome());
        cv.put(DataString.COLUMN_DATA_RICETTA, ricetta.getDataCreazione());
        cv.put(DataString.COLUMN_QUANTITA_BIRRA, ricetta.getQuantitaBirraProdotta());
        try {
            db.insert(DataString.RICETTA_TABLE, null, cv);
            saveRicettario(ricetta);
        } catch (SQLiteException e) {
            // Gestione delle eccezioni
        }
    }

    // per ogni ricetta salva i riferimenti agli id degli ingredienti presenti nella ricetta
    private void saveRicettario(Ricetta ricetta) {
        int idRicetta = readIdRicetta(ricetta);
        db = databaseHelper.getWritableDatabase();

        for (int i = 0; i < ricetta.getDispensaIngrediente().size(); i++) {
            cv = new ContentValues();
            cv.put(DataString.COLUMN_ID_RICETTA, idRicetta);
            cv.put(DataString.COLUMN_ID_INGREDIENTE, readIdIngrediente(ricetta.getDispensaIngrediente().get(i)));
            cv.put(DataString.COLUMN_QUANTITA_INGREDIENTE_RICETTA, ricetta.getDispensaIngrediente().get(i).getQuantita());
            try {
                db.insert(DataString.RELAZIONE_TABLE, null, cv);
            } catch (SQLiteException e) {
                // Gestione delle eccezioni
            }
        }
    }

    public int readIdRicetta(Ricetta ricetta) {
        db = databaseHelper.getReadableDatabase();
        int id = 0;
        Cursor cursor = db.query(DataString.RICETTA_TABLE, null,
                DataString.COLUMN_NOME_RICETTA + " = ?", new String[]{ricetta.getNome()}, null, null, null);
        if (cursor.moveToNext()) {
            id = cursor.getInt(0);
        } else
            cursor.close();
        return id;
    }

    private int readIdIngrediente(Ingrediente ingrediente) {
        db = databaseHelper.getReadableDatabase();
        int id = 0;
        Cursor cursor = db.query(DataString.INGREDIENTE_TABLE, null,
                DataString.COLUMN_NOME_INGREDIENTE + " = ?", new String[]{ingrediente.getNome()}, null, null, null);
        if (cursor.moveToNext()) {
            id = cursor.getInt(0);
        } else
            cursor.close();
        return id;
    }

    // restituisce le ricette presenti nel db tramite un ArraList
    public List<Ricetta> mostraRicette() throws ParseException {
        List<Ricetta> listaRicette = new ArrayList<>();
        //accesso in lettura al db
        db = databaseHelper.getReadableDatabase();
        //salva nell'array il risultato della select (query = select)
        Cursor listaRicetteCursor = db.query(DataString.RICETTA_TABLE, null, null, null,
                null, null, null);
        if (listaRicetteCursor.moveToNext()) {
            do {
                String nomeRicetta = listaRicetteCursor.getString(1);
                listaIngredienti = getIngredientiRicetta();
                Ricetta ricetta = new Ricetta(nomeRicetta, listaRicetteCursor.getString(2), listaRicetteCursor.getDouble(3), listaIngredienti);
                listaRicette.add(ricetta);
            } while (listaRicetteCursor.moveToNext());
        } else
            listaRicetteCursor.close();
        return listaRicette;
    }


    // restituisce la lista degli ingredienti presenti in ogni ricetta per ogni ricetta nel db
    private List<Ingrediente> getIngredientiRicetta() {
        listaIngredienti = new ArrayList<>();
        //accesso in lettura al db
        db = databaseHelper.getReadableDatabase();
        String listaIngredientiRicettaQuery = "SELECT i.ID_INGREDIENTE, i.NOME_INGREDIENTE, rl.QUANTITA_INGREDIENTE " +
                "FROM RICETTA r JOIN RELAZIONE rl " +
                "ON r.ID_RICETTA = rl.ID_RICETTA " +
                "JOIN INGREDIENTE i " +
                "ON rl.ID_INGREDIENTE = i.ID_INGREDIENTE " +
                "ORDER BY i.ID_INGREDIENTE";
        listaIngredientiCursor = db.rawQuery(listaIngredientiRicettaQuery, null);
        if (listaIngredientiCursor.moveToNext()) {
            do {
                Ingrediente ingrediente = new Ingrediente(listaIngredientiCursor.getString(1), listaIngredientiCursor.getDouble(2));
                listaIngredienti.add(ingrediente);
            } while (listaIngredientiCursor.moveToNext());
        } else
            listaIngredientiCursor.close();
        return listaIngredienti;
    }

    /// nome e data creazione ricetta, nome ingrediente e quantità di relazione

    // restituisce la lista degli ingredienti presenti in ogni ricetta per ogni ricetta nel db
    public List<Ingrediente> getIngredientiRicetta(int id) {
        listaIngredienti = new ArrayList<>();
        //accesso in lettura al db
        db = databaseHelper.getReadableDatabase();
        String listaIngredientiRicettaQuery = "SELECT i.NOME_INGREDIENTE, rl.QUANTITA_INGREDIENTE " +
                "FROM RICETTA r JOIN RELAZIONE rl " +
                "ON r.ID_RICETTA = rl.ID_RICETTA " +
                "JOIN INGREDIENTE i " +
                "ON rl.ID_INGREDIENTE = i.ID_INGREDIENTE " +
                "WHERE r.ID_RICETTA = " + id;
        listaIngredientiCursor = db.rawQuery(listaIngredientiRicettaQuery, null);
        if (listaIngredientiCursor.moveToNext()) {
            do {
                Ingrediente ingrediente = new Ingrediente(listaIngredientiCursor.getString(0), listaIngredientiCursor.getDouble(1));
                listaIngredienti.add(ingrediente);
            } while (listaIngredientiCursor.moveToNext());
        } else
            listaIngredientiCursor.close();
        return listaIngredienti;
    }

    public void deleteRicetta(Ricetta ricetta) {
        db = databaseHelper.getWritableDatabase();
        db.execSQL("PRAGMA foreign_keys = ON;");
        try {
            db.delete(DataString.RICETTA_TABLE, DataString.COLUMN_NOME_RICETTA + " = ?", new String[]{ricetta.getNome()});
        } catch (SQLiteException e) {
            //Gestione eccezioni
        }
    }

    public void saveNote(Note note) {
        db = databaseHelper.getWritableDatabase();
        cv = new ContentValues();
        cv.put(DataString.COLUMN_TESTO_NOTE, note.getTesto());
        try {
            db.insert(DataString.NOTE_TABLE, null, cv);
        } catch (SQLiteException e) {
            // Gestione delle eccezioni
        }
    }

    public Note getNote(Ricetta ricetta) {
        db = databaseHelper.getReadableDatabase();
        Note nota = null;
        Cursor cursor = db.query(DataString.NOTE_TABLE, null, DataString.COLUMN_ID_RICETTA + " = ?", new String[]{String.valueOf(readIdRicetta(ricetta))}, null, null, null);
        if (cursor.moveToNext())
            nota = new Note(cursor.getString(1));
        else
            cursor.close();
        return nota;
    }
}

