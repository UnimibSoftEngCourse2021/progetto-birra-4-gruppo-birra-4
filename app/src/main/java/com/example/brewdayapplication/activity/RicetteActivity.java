package com.example.brewdayapplication.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.contentcapture.DataRemovalRequest;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.brewdayapplication.Ingrediente;
import com.example.brewdayapplication.R;
import com.example.brewdayapplication.Ricetta;
import com.example.brewdayapplication.adapter.ListAdapterRicetta;
import com.example.brewdayapplication.database.DataString;
import com.example.brewdayapplication.database.DatabaseManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RicetteActivity extends AppCompatActivity {

    //Dichiarazioni
    DatabaseManager databaseManager;
    ListView listViewRicette;
    FloatingActionButton aggiungiRicetta;

    AlertDialog alertDialog;
    AlertDialog.Builder alert;
    View viewNewRicetta;
    Button btnTornaIndietroNewRicetta;
    Button btnSalvaRicetta;

    List<Ingrediente> ricettario = new ArrayList<>();
    List<Ricetta> listRicette = new ArrayList<>();
    ArrayAdapter<Ricetta> resultQuery;
    Ricetta ricetta;


    String[] arrayIngredienti = new String[]{"Orzo", "Lievito", "Acqua", "Zucchero", "Luppolo", "Additivi"};
    int i = 0;
    GridLayout gridLayout;
    TextView textView;
    EditText editText;
    Button button;

    EditText editTextTitoloRicetta;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set layout
        setContentView(R.layout.activity_list_ricette);

        //dichiarazione
        listViewRicette = findViewById(R.id.layoutListaRicette);
        aggiungiRicetta = findViewById(R.id.addRicetta);

        databaseManager = new DatabaseManager(getApplicationContext());


        //cliccato il bottone rimanda alla classe innestata che crea la dialog e chiede i parametri per creare la ricetta
        aggiungiRicetta.setOnClickListener(new CreaRicetta());

        printList();
    }

    //classe innestata per creare la ricetta
    private class CreaRicetta implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            alert = new AlertDialog.Builder(RicetteActivity.this);
            viewNewRicetta = getLayoutInflater().inflate(R.layout.activity_dialog_new_ricetta, null);
            alert.setView(viewNewRicetta);
            alertDialog = alert.create();
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.show();

            editTextTitoloRicetta = viewNewRicetta.findViewById(R.id.titoloRicetta);
            gridLayout = viewNewRicetta.findViewById(R.id.gridLayout);
            textView = viewNewRicetta.findViewById(R.id.nome_ingrediente);
            editText = viewNewRicetta.findViewById(R.id.quantita_ingrediente);
            button = viewNewRicetta.findViewById(R.id.plus_ingrediente);

            btnTornaIndietroNewRicetta = viewNewRicetta.findViewById(R.id.btn_back_ricetta);
            btnSalvaRicetta = viewNewRicetta.findViewById(R.id.btn_save_ricetta);


            button.setOnClickListener(new PlusIngrediente());
            btnTornaIndietroNewRicetta.setOnClickListener(new BackRicetta());
            btnSalvaRicetta.setOnClickListener(new SalvaRicetta());
        }
    }

    private class PlusIngrediente implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String nome = textView.getText().toString();
            int id = databaseManager.getIngredienteId(nome);
            if (!editText.getText().toString().isEmpty())
                ricettario.add(new Ingrediente(id,nome, Double.parseDouble(editText.getText().toString())));
            else
                ricettario.add(new Ingrediente(id,nome, 0));

            if (i < arrayIngredienti.length) {
                textView.setText(arrayIngredienti[i]);
                i++;
            } else {
                Toast.makeText(getApplicationContext(), "Lista ingredienti finita, premere il pulsante Conferma per salvare la ricetta", Toast.LENGTH_SHORT).show();
                editText.setFocusable(false);
            }
            editText.setText(null);
            editText.setHint("Inserisci quantità");
        }
    }

    private class BackRicetta implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            alertDialog.dismiss();
        }
    }

    private class SalvaRicetta implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            if (!editTextTitoloRicetta.getText().toString().isEmpty()) {
                int lastRicettaInDB = databaseManager.getLastId(DataString.RICETTA_TABLE, DataString.COLUMN_ID_RICETTA);
                ricetta = new Ricetta(lastRicettaInDB, editTextTitoloRicetta.getText().toString(), new Date(), 1, ricettario);
                databaseManager.saveRicetta(ricetta);
                listRicette.add(ricetta);
                alertDialog.dismiss();
                printList();
            } else
                Toast.makeText(getApplicationContext(), "Inserire il titolo della ricetta", Toast.LENGTH_SHORT).show();

        }
    }

    private void printList() {
        //listRicette = databaseManager.mostraRicette();
        resultQuery = new ListAdapterRicetta(this, listRicette);
        listViewRicette.setAdapter(resultQuery);
    }

}
