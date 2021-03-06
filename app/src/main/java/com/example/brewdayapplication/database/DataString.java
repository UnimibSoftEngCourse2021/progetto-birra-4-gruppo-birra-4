package com.example.brewdayapplication.database;

public class DataString {




    //creato solo per evitare smell
    private DataString() {
    }

    //dechiarazione e assegnamento delle tabelle del db
    public static final String INGREDIENTE_TABLE = "INGREDIENTE";
    public static final String MAGAZZINO_TABLE = "MAGAZZINO";
    public static final String RICETTA_TABLE = "RICETTA";
    public static final String RELAZIONE_TABLE = "RELAZIONE";
    public static final String NOTE_TABLE = "NOTE";

    // costanti sql ripetute
    public static final String CREA_TABELLA = "CREATE TABLE";
    public static final String CHIAVE_ESTERNA = "FOREIGN KEY";
    public static final String INTERO_CHIAVE = "INTEGER PRIMARY KEY AUTOINCREMENT";
    public static final String REFERENCES = "REFERENCES";


    //dichiarazione e assegnamento delle colonne del db per ogni tabella
    public static final String COLUMN_ID_MAGAZZINO = "ID_MAGAZZINO";
    public static final String COLUMN_CAPACITY_EQUIPMENT = "CAPACITA_SET";

    public static final String COLUMN_ID_INGREDIENTE = "ID_INGREDIENTE";
    public static final String COLUMN_NOME_INGREDIENTE = "NOME_INGREDIENTE";
    public static final String COLUMN_QUANTITA_MAGAZZINO = "QUANTITA_DISPENSA";

    public static final String COLUMN_ID_RICETTA = "ID_RICETTA";
    public static final String COLUMN_NOME_RICETTA = "NOME_RICETTA";
    public static final String COLUMN_DATA_RICETTA = "DATA_RICETTA";

    public static final String COLUMN_QUANTITA_INGREDIENTE_RICETTA ="QUANTITA_INGREDIENTE";
    public static final String PK_RELAZIONE_RI = "PK_RICETTARIO";

    public static final String COLUMN_ID_NOTE = "ID_NOTE";
    public static final String COLUMN_TESTO_NOTE_PROBLEMI = "TESTO_NOTE_PROBLEMI";
    public static final String COLUMN_TESTO_NOTE_UTENTI = "TESTO_NOTE_UTENTI";

    public static final String TEXT_NOT_NULL = "TEXT NOT NULL";
    public static final String INTEGER = "INTEGER";
}
