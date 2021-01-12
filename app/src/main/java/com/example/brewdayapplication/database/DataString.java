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

    // costanti sql ripetute
    public static final String CREA_TABELLA = "CREATE TABLE";
    public static final String CHIAVE_ESTERNA = "FOREIGN KEY";
    public static final String INTERO_CHIAVE = "INTEGER PRIMARY KEY AUTOINCREMENT";


    //dechiarazione e assegnamento delle colonne del db per ogni tabella

    public static final String COLUMN_ID_MAGAZZINO = "ID_MAGAZZINO";
    public static final String COLUMN_CAPACITY_EQUIPMENT = "CAPACITA_SET";

    public static final String COLUMN_ID_INGREDIENTE = "ID_RICETTA";
    public static final String COLUMN_NOME_INGREDIENTE = "NOME_INGREDIENTE";
    public static final String COLUMN_QUANTITA_MAGAZZINO = "QUANTITA_DISPENSA";

    public static final String COLUMN_ID_RICETTA = "ID_RICETTA";
    public static final String COLUMN_NOME_RICETTA = "NOME_RICETTA";
    public static final String COLUMN_DATA_RICETTA = "DATA_RICETTA";
    public static final String COLUMN_QUANTITA_BIRRA = "QUANTITA_BIRRA";

    public static final String COLUMN_QUANTITA_INGREDIENTE_RICETTA ="QUANTITA_INGREDIENTE";


}
