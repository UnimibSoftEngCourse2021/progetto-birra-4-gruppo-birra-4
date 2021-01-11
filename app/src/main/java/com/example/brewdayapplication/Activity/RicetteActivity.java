package com.example.brewdayapplication.Activity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.brewdayapplication.Database.DatabaseManager;
import com.example.brewdayapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class RicetteActivity extends AppCompatActivity {

    //Dichiarazioni
    DatabaseManager databaseManager;
    ListView listViewRicette;
    FloatingActionButton aggiungiRicetta;
    Dialog dialogRicetta;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set layout
        setContentView(R.layout.activity_list_ricette);

        //dichiarazione
        listViewRicette = findViewById(R.id.layoutListaRicette);
        aggiungiRicetta = findViewById(R.id.addRicetta);
        databaseManager = new DatabaseManager(getApplicationContext());
        dialogRicetta = new Dialog(this);

        //cliccato il bottone rimanda alla classe innestata che crea la dialog e chiede i parametri per creare la ricetta
        aggiungiRicetta.setOnClickListener(new CreaRicetta());
    }


    //classe innestata per creare la ricetta
    private class CreaRicetta implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            dialogRicetta.setContentView(R.layout.activity_dialog_new_ricetta);
            dialogRicetta.show();
        }
    }
}
