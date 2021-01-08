package com.example.brewdayapplication;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class IngredienteActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    DatabaseManager databaseManager;
    private Cursor list_ingredients;
    Button modifica_ingrediente;
    EditText quantitaView;
    Spinner ingredienteView;
    Magazzino magazzino;
    ListView listviewIngredienti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingrediente);


        listviewIngredienti = findViewById(R.id.listview_ingredients);
        modifica_ingrediente = findViewById(R.id.modifica_ingrediente);
        quantitaView = findViewById(R.id.quantita_ingrediente);
        ingredienteView = findViewById(R.id.nome_ingrediente);

        databaseManager = new DatabaseManager(getApplicationContext());

        printList(listviewIngredienti);


        /*definisce la funzione del bottone modifica ingrediente*/
        modifica_ingrediente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double quantita = Double.parseDouble(quantitaView.getText().toString());
                String nome_ingrediente = ingredienteView.getSelectedItem().toString();
                Ingrediente ingrediente = new Ingrediente(nome_ingrediente,quantita);
                databaseManager.saveIngredient(ingrediente);
                printList(listviewIngredienti);
            }
        });
    }

    private void printList(ListView listViewIngredienti) {
        ArrayAdapter resultQuery = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, databaseManager.mostraIngredienti());
        listViewIngredienti.setAdapter(resultQuery);
    }

    private Magazzino getMagazzino() {
       Cursor datiMagazzino =  databaseManager.getCapacita();
       int id = 0;
       double capacita = 0;
       while(datiMagazzino.moveToFirst()){
           id = datiMagazzino.getInt(0);
           capacita = datiMagazzino.getDouble(1);
       }
       datiMagazzino.close();
       return new Magazzino(id,capacita);
    }

}


