package com.example.brewdayapplication.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.brewdayapplication.database.DatabaseManager;
import com.example.brewdayapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class RicetteActivity extends AppCompatActivity {

    //Dichiarazioni
    DatabaseManager databaseManager;
    ListView listViewRicette;
    FloatingActionButton aggiungiRicetta;
    Button btnTornaIndietroNewRicetta;
    Button btnSalvaRicetta;
    AlertDialog alertDialog;
    AlertDialog.Builder alert;
    View viewNewRicetta;


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

            btnTornaIndietroNewRicetta = viewNewRicetta.findViewById(R.id.btn_back_ricetta);
            btnSalvaRicetta = viewNewRicetta.findViewById(R.id.btn_save_ricetta);

            btnTornaIndietroNewRicetta.setOnClickListener(new BackRicetta());
            btnSalvaRicetta.setOnClickListener(new SalvaRicetta());

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
            /*metodo salva ricetta database*/
        }
    }

}
