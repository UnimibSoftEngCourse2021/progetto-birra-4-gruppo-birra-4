package com.example.brewdayapplication.Activity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.brewdayapplication.Database.DatabaseManager;
import com.example.brewdayapplication.Ingrediente;
import com.example.brewdayapplication.Adapter.ListAdapter;
import com.example.brewdayapplication.R;

import java.util.List;

public class IngredienteActivity extends AppCompatActivity {


    DatabaseManager databaseManager;
    Button modificaIngrediente;
    EditText quantitaView;
    Spinner ingredienteView;
    ListView listviewIngredienti;
    List<Ingrediente> ingredienteList;
    ListAdapter resultQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingrediente);


        listviewIngredienti = findViewById(R.id.listview_ingredients);
        modificaIngrediente = findViewById(R.id.modifica_ingrediente);
        quantitaView = findViewById(R.id.quantita_ingrediente);
        ingredienteView = findViewById(R.id.nome_ingrediente);
        databaseManager = new DatabaseManager(getApplicationContext());
        printList();


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
            int mostra = databaseManager.saveIngredient(ingrediente);
            if (mostra == 2)
                ingredienteList.add(ingrediente);
            printList();
        }
    }

    private void printList() {
        ingredienteList = databaseManager.mostraIngredienti();
        resultQuery = new ListAdapter(this, ingredienteList);
        listviewIngredienti.setAdapter(resultQuery);
    }


}


