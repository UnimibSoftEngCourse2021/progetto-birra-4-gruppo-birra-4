package com.example.brewdayapplication;


import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class IngredienteActivity extends AppCompatActivity {


    DatabaseManager databaseManager;
    Button modificaIngrediente;
    EditText quantitaView;
    Spinner ingredienteView;
    ListView listviewIngredienti;
    List<Ingrediente> ingredienteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingrediente);


        listviewIngredienti = findViewById(R.id.listview_ingredients);
        modificaIngrediente = findViewById(R.id.modifica_ingrediente);
        quantitaView = findViewById(R.id.quantita_ingrediente);
        ingredienteView = findViewById(R.id.nome_ingrediente);

        databaseManager = new DatabaseManager(getApplicationContext());
        ingredienteList = databaseManager.mostraIngredienti();
        printList(ingredienteList);


        /*definisce la funzione del bottone modifica ingrediente*/
        modificaIngrediente.setOnClickListener(new ModificaIngredienteListener());

    }

    private class ModificaIngredienteListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Ingrediente ingrediente = null;
            try {
                ingrediente = new Ingrediente(ingredienteView.getSelectedItem().toString(), Double.parseDouble(quantitaView.getText().toString()));
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
            }
            databaseManager.saveIngredient(ingrediente);
            ingredienteList.add(ingrediente);
            printList(ingredienteList);
        }
    }


    private void printList(List<Ingrediente> ingredienteList) {
        ListAdapter resultQuery = new ListAdapter(this, ingredienteList);
        listviewIngredienti.setAdapter(resultQuery);
    }


}


