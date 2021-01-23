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

    // metodo chiamato quando viene installata l'app per la prima volta
    @Override
    public void onCreate(SQLiteDatabase db) {
        //creazione tabella magazzino
        String createTableStatementMagazzino = DataString.CREA_TABELLA + " " + DataString.MAGAZZINO_TABLE + "("
                + DataString.COLUMN_ID_MAGAZZINO + " " + DataString.INTERO_CHIAVE + ","
                + DataString.COLUMN_CAPACITY_EQUIPMENT + " DOUBLE NOT NULL)";

        //creazione tabella ingredienti
        String createTableStatementIngredient = DataString.CREA_TABELLA + " " + DataString.INGREDIENTE_TABLE + "("
                + DataString.COLUMN_ID_INGREDIENTE + " " + DataString.INTERO_CHIAVE + ","
                + DataString.COLUMN_NOME_INGREDIENTE + " TEXT NOT NULL,"
                + DataString.COLUMN_QUANTITA_MAGAZZINO + " DOUBLE NOT NULL,"
                + DataString.COLUMN_ID_MAGAZZINO + " INTEGER NOT NULL,"
                + DataString.CHIAVE_ESTERNA + "(" + DataString.COLUMN_ID_MAGAZZINO + ") " + DataString.REFERENCES + " " + DataString.MAGAZZINO_TABLE + "(" + DataString.COLUMN_ID_MAGAZZINO + "))";

        //creazione tabella ricetta
        String createTableStatementRicetta = DataString.CREA_TABELLA + " " + DataString.RICETTA_TABLE + "("
                + DataString.COLUMN_ID_RICETTA + " " + DataString.INTERO_CHIAVE + ","
                + DataString.COLUMN_NOME_RICETTA + " TEXT NOT NULL UNIQUE,"
                + DataString.COLUMN_DATA_RICETTA + " DATE NOT NULL,"
                + DataString.COLUMN_QUANTITA_BIRRA + " DOUBLE NOT NULL)";


        //creazione tabella relazione Ricetta-Ingrediente
        String createTableStatementRelazioneRI = DataString.CREA_TABELLA + " " + DataString.RELAZIONE_TABLE + " ("
                + DataString.COLUMN_ID_RICETTA + " INTEGER,"
                + DataString.COLUMN_ID_INGREDIENTE + " INTEGER,"
                + DataString.COLUMN_QUANTITA_INGREDIENTE_RICETTA + " DOUBLE NOT NULL,"
                + "CONSTRAINT " + DataString.PK_RELAZIONE_RI + " PRIMARY KEY (" + DataString.COLUMN_ID_RICETTA + ", " + DataString.COLUMN_ID_INGREDIENTE + "),"
                + "CONSTRAINT FK_RICETTA " + DataString.CHIAVE_ESTERNA + " (" + DataString.COLUMN_ID_RICETTA + ") " + DataString.REFERENCES + " " + DataString.RICETTA_TABLE + "(" + DataString.COLUMN_ID_RICETTA + ") ON DELETE CASCADE,"
                + "CONSTRAINT FK_INGREDIENTE " + DataString.CHIAVE_ESTERNA + " (" + DataString.COLUMN_ID_INGREDIENTE + ") " + DataString.REFERENCES + " " + DataString.INGREDIENTE_TABLE + "(" + DataString.COLUMN_ID_INGREDIENTE + "))";

        //creazione tabella Note
        String createTableStatementNote = DataString.CREA_TABELLA + " " + DataString.NOTE_TABLE + " ("
                + DataString.COLUMN_ID_NOTE + " INTEGER,"
                + DataString.COLUMN_TITOLO_NOTE + " TEXT NOT NULL,"
                + DataString.COLUMN_TESTO_NOTE + " TEXT NOT NULL,"
                + DataString.COLUMN_ID_RICETTA + " INTEGER,"
                + "CONSTRAINT " + DataString.PK_NOTE + " PRIMARY KEY (" + DataString.COLUMN_ID_NOTE + ", " + DataString.COLUMN_ID_RICETTA + "),"
                + "CONSTRAINT FK_RICETTA " + DataString.CHIAVE_ESTERNA + " (" + DataString.COLUMN_ID_RICETTA + ") " + DataString.REFERENCES + " " + DataString.RICETTA_TABLE + "(" + DataString.COLUMN_ID_RICETTA + "))";


        //esecuzione delle degli statement sql per la creazione delle tabelle nel db
        db.execSQL(createTableStatementMagazzino);
        db.execSQL(createTableStatementIngredient);
        db.execSQL(createTableStatementRicetta);
        db.execSQL(createTableStatementRelazioneRI);
        db.execSQL(createTableStatementNote);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}
