package com.example.brewdayapplication.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.brewdayapplication.database.DataString;
import com.example.brewdayapplication.database.DatabaseManager;
import com.example.brewdayapplication.Ingrediente;
import com.example.brewdayapplication.adapter.ListAdapter;
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


    //crea l'ingrediente con nome selezionato dallo spinner e quantità  inserita dall'utente
    //creato l'ingrediente lo salva nel db
    //se mostra = 2, l'ingrediente non era presente nella lista (era la prima volta che lo aggiungeva) allora lo aggiunge alla lista
    //chiama metodo che stampa la lista
    private class ModificaIngredienteListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Ingrediente ingrediente = null;
            int id = databaseManager.getLastId(DataString.INGREDIENTE_TABLE, DataString.COLUMN_ID_INGREDIENTE);
            try {
                ingrediente = new Ingrediente(id, ingredienteView.getSelectedItem().toString(), Double.parseDouble(quantitaView.getText().toString()));
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
            }
            // l'ingrediente viene aggiunto all'arraylist solo se già non presente in esso
            int mostra = databaseManager.saveIngredient(ingrediente);
            if (mostra == 2)
                ingredienteList.add(ingrediente);
            printList();
        }
    }

    //metodo che stampa gli ingredienti presenti nel db
    private void printList() {
        ingredienteList = databaseManager.mostraIngredienti();
        resultQuery = new ListAdapter(this, ingredienteList);
        listviewIngredienti.setAdapter(resultQuery);
    }


}


