package com.example.brewdayapplication.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.brewdayapplication.Ingrediente;
import com.example.brewdayapplication.Note;
import com.example.brewdayapplication.R;
import com.example.brewdayapplication.Ricetta;
import com.example.brewdayapplication.adapter.ListAdapterRicetta;
import com.example.brewdayapplication.database.DatabaseManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RicetteActivity extends AppCompatActivity {

    TextView textViewTitolo;
    TextView textViewData;
    TextView textViewListaIng;
    TextView textViewListaQuant;


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
    boolean isUpdate = false;
    String listaIngString;
    String listaQuaString;


    Note note;

    String[] arrayIngredienti = new String[]{"Malto", "Orzo", "Lievito", "Acqua", "Zucchero", "Luppolo", "Additivi"};
    int i = 0;
    TextView textView;
    EditText editText;
    Button button;
    String data = "";
    final ZoneId zona = ZoneId.of("Europe/Rome");

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

        listViewRicette.setOnItemLongClickListener(new CancellaRicettaListener());
        //metodo che mostra gli ingredienti di una ricetta con un alert
        listViewRicette.setOnItemClickListener(new VisualizzaInfoRicettaListener());

    }

    // stampa su una listview le ricette presenti sul db
    private void printList() {
        listRicette = databaseManager.mostraRicette();
        resultQuery = new ListAdapterRicetta(this, listRicette);
        listViewRicette.setAdapter(resultQuery);
    }

    //classe innestata per creare la ricetta
    private class CreaRicetta implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            provaaaa();
        }
    }

    private void provaaaa() {
        alert = new AlertDialog.Builder(RicetteActivity.this);
        viewNewRicetta = getLayoutInflater().inflate(R.layout.activity_dialog_new_ricetta, null);
        alert.setView(viewNewRicetta);
        alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();

        editTextTitoloRicetta = viewNewRicetta.findViewById(R.id.titoloRicetta);
        textView = viewNewRicetta.findViewById(R.id.nome_ingrediente);
        editText = viewNewRicetta.findViewById(R.id.quantita_ingrediente);
        button = viewNewRicetta.findViewById(R.id.plus_ingrediente);

        btnTornaIndietroNewRicetta = viewNewRicetta.findViewById(R.id.btn_back_ricetta);
        btnSalvaRicetta = viewNewRicetta.findViewById(R.id.btn_save_ricetta);
        btnSalvaRicetta.setEnabled(false);
        i = 0;

        textView.setText(arrayIngredienti[i]);

        if (isUpdate) {
            editTextTitoloRicetta.setText(ricetta.getNome());
            editTextTitoloRicetta.setEnabled(false);
        }

        button.setOnClickListener(new PlusIngrediente());
        btnTornaIndietroNewRicetta.setOnClickListener(new BackRicetta());
        btnSalvaRicetta.setOnClickListener(new SalvaRicetta());
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
                Toast.makeText(getApplicationContext(), "Lista ingredienti finita, premere il pulsante Conferma per salvare la ricetta", Toast.LENGTH_SHORT).show();
                editText.setFocusable(false);
                btnSalvaRicetta.setEnabled(true);
            }
            editText.setText(null);
            editText.setHint("Quantità");
        }
    }

    // classe innestata per chiudere la dialog
    private class BackRicetta implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            i = 0;
            isUpdate = false;
            alertDialog.cancel();
        }
    }

    // classe innestata per salvare la ricetta sul db
    private class SalvaRicetta implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (isUpdate) {
                databaseManager.updateRicetta(ricetta, ricettario);
                isUpdate = false;
                alertDialog.cancel();
                descrizione();
                ricettario.clear();
            }

            if (!editTextTitoloRicetta.getText().toString().isEmpty()) {
                data = creaData();
                ricetta = new Ricetta(editTextTitoloRicetta.getText().toString(), data, 1, ricettario);
                databaseManager.saveRicetta(ricetta);
                listRicette.add(ricetta);
                alertDialog.dismiss();
                ricettario.clear();
                printList();
            } else
                Toast.makeText(getApplicationContext(), "Inserire il titolo della ricetta", Toast.LENGTH_SHORT).show();

        }

        private String creaData() {
            LocalDate localdata = LocalDate.now();
            LocalTime ora = LocalTime.now();
            LocalDateTime dataCreazione = LocalDateTime.of(localdata, ora);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm").withZone(zona).withLocale(Locale.ITALY);
            return dataCreazione.format(formatter);
        }
    }

    private class VisualizzaInfoRicettaListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            alert = new AlertDialog.Builder(RicetteActivity.this);
            viewNewRicetta = getLayoutInflater().inflate(R.layout.alert_note, null);
            alert.setView(viewNewRicetta);

            textViewTitolo = viewNewRicetta.findViewById(R.id.textViewTitolo);
            textViewData = viewNewRicetta.findViewById(R.id.textViewData);
            textViewListaIng = viewNewRicetta.findViewById(R.id.textView_ListaIngredienti);
            textViewListaQuant = viewNewRicetta.findViewById(R.id.textView_QuantitaIngredienti);
            Button buttonModificaRicetta = viewNewRicetta.findViewById(R.id.btn_ModificaRicetta);
            Button buttonSalvaNota = viewNewRicetta.findViewById(R.id.btn_SalvaNote);
            Button buttonProduciRicetta = viewNewRicetta.findViewById(R.id.btn_ProduciRicetta);
            EditText editTextNotaProblema = viewNewRicetta.findViewById(R.id.editText_NotaBirraio);
            EditText editTextNotaUtente = viewNewRicetta.findViewById(R.id.editText_NotaAmici);

            listRicette = databaseManager.mostraRicette();
            ricetta = listRicette.get(position);
            ricetta = (Ricetta) listViewRicette.getItemAtPosition(position);

            descrizione();

            /* = "";
            listaQuaString = "";
            List<Ingrediente> ingredientiRicetta = databaseManager.getIngredientiRicetta(databaseManager.readIdRicetta(ricetta));
            for (Ingrediente j : ingredientiRicetta) {
                listaIngString = listaIngString.concat(j.getNome() + " \n");
                listaQuaString = listaQuaString.concat(j.getQuantita() + " g \n");
            }
            /*textViewTitolo.setText(ricetta.getNome());
            textViewData.setText(ricetta.getDataCreazione());
            textViewListaIng.setText(listaIngString);
            textViewListaQuant.setText(listaQuaString);

            // note = databaseManager.getNote(ricetta);

            /*if (note != null){
                editTextNotaProblema.setText(note.getTestoProblemi());
                editTextNotaUtente.setText(note.getTestoUtenti());
            }*/

            alertDialog = alert.create();
            alertDialog.show();
            buttonModificaRicetta.setOnClickListener(new AggiornaRicetta());

            //note = new Note(editTextNotaProblema.getText().toString(), editTextNotaProblema.getText().toString());

            buttonSalvaNota.setOnClickListener(new SalvaNota());
            buttonProduciRicetta.setOnClickListener(new ProduciRicetta());

        }
    }

    private void descrizione() {
        listaIngString = "";
        listaQuaString = "";
        List<Ingrediente> ingredientiRicetta = databaseManager.getIngredientiRicetta(databaseManager.readIdRicetta(ricetta));
        for (Ingrediente j : ingredientiRicetta) {
            listaIngString = listaIngString.concat(j.getNome() + " \n");
            listaQuaString = listaQuaString.concat(j.getQuantita() + " g \n");
            textViewTitolo.setText(ricetta.getNome());
            textViewData.setText(ricetta.getDataCreazione());
            textViewListaIng.setText(listaIngString);
            textViewListaQuant.setText(listaQuaString);
        }
    }


    private class CancellaRicettaListener implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            ricetta = (Ricetta) listViewRicette.getItemAtPosition(position);
            alert = new AlertDialog.Builder(RicetteActivity.this);
            alert.setTitle("Cancellare?")
                    .setNegativeButton("Si", new CancellazioneAffermativaListener())
                    .setPositiveButton("No", new CancellazioneNegativaListener());
            alertDialog = alert.create();
            alertDialog.show();
            return true;
        }
    }

    private class CancellazioneAffermativaListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            databaseManager.deleteRicetta(ricetta);
            printList();
            alert.setCancelable(true);
        }
    }

    private class CancellazioneNegativaListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            Toast.makeText(getApplicationContext(), "torna indietro", Toast.LENGTH_LONG).show();
        }
    }

    private class SalvaNota implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            databaseManager.saveNote(note);
            alertDialog.dismiss();
        }
    }
   private class ProduciRicetta implements  View.OnClickListener{
        @Override
        public void onClick(View v){
            databaseManager.produciBirra(ricetta);
        }
    }

    private class AggiornaRicetta implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            isUpdate = true;
            provaaaa();
        }
    }
}
