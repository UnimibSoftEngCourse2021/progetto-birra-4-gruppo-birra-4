package com.example.brewdayapplication.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
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
import com.example.brewdayapplication.database.DatabaseManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
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


    String[] arrayIngredienti = new String[]{"Malto", "Orzo", "Lievito", "Acqua", "Zucchero", "Luppolo", "Additivi"};
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

        try {
            printList();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        listViewRicette.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Ricetta ricetta = (Ricetta) listViewRicette.getItemAtPosition(position);
                alert = new AlertDialog.Builder(RicetteActivity.this);
                alert.setTitle("Cancellare?")
                        .setNegativeButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                databaseManager.deleteRicetta(ricetta);
                                try {
                                    printList();
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                alert.setCancelable(true);
                            }
                        })
                        .setPositiveButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "torna indietro", Toast.LENGTH_LONG).show();
                            }
                        });
                alertDialog = alert.create();
                alertDialog.show();
                return true;
            }
        });
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

            textView.setText(arrayIngredienti[i]);
            btnTornaIndietroNewRicetta.setOnClickListener(new BackRicetta());
            btnSalvaRicetta.setOnClickListener(new SalvaRicetta());

        }
    }

    // classe innestata per aggiungere gli ingredienti alla ricetta
    private class PlusIngrediente implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (!editText.getText().toString().isEmpty())
                ricettario.add(new Ingrediente(textView.getText().toString(), Double.parseDouble(editText.getText().toString())));
            else
                ricettario.add(new Ingrediente(textView.getText().toString(), 0));

            if (i < arrayIngredienti.length - 1) {
                textView.setText(arrayIngredienti[++i]);
            } else {
                i = 0;
                Toast.makeText(getApplicationContext(), "Lista ingredienti finita, premere il pulsante Conferma per salvare la ricetta", Toast.LENGTH_SHORT).show();
                editText.setFocusable(false);
            }
            editText.setText(null);
            editText.setHint("Inserisci quantità");
        }
    }

    // classe innestata per chiudere la dialog
    private class BackRicetta implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            alertDialog.dismiss();
        }
    }

    // classe innestata per salvare la ricetta sul db
    private class SalvaRicetta implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            if (!editTextTitoloRicetta.getText().toString().isEmpty()) {
                ricetta = new Ricetta(editTextTitoloRicetta.getText().toString(), new Date(), 1, ricettario);
                databaseManager.saveRicetta(ricetta);
                listRicette.add(ricetta);
                alertDialog.dismiss();
                try {
                    printList();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else
                Toast.makeText(getApplicationContext(), "Inserire il titolo della ricetta", Toast.LENGTH_SHORT).show();

        }
    }

    // stampa su una listview le ricette presenti sul db
    private void printList() throws ParseException {
        listRicette = databaseManager.mostraRicette();
        resultQuery = new ListAdapterRicetta(this, listRicette);
        listViewRicette.setAdapter(resultQuery);
    }

}
