package com.example.brewdayapplication.database;

public class DataString {

    //creato solo per evitare smell
    private DataString() {
    }

    //dechiarazione e assegnamento delle tabelle del db
    public static final String INGREDIENTE_TABLE = "INGREDIENTE";
    public static final String MAGAZZINO_TABLE = "MAGAZZINO";


    //dechiarazione e assegnamento delle colonne del db per ogni tabella

    public static final String COLUMN_ID_MAGAZZINO = "ID_MAGAZZINO";
    public static final String COLUMN_CAPACITY_EQUIPMENT = "CAPACITA_SET";


    public static final String COLUMN_NOME_INGREDIENTE = "NOME_INGREDIENTE";
    public static final String COLUMN_QUANTITA_MAGAZZINO = "QUANTITA_DISPENSA";
}
