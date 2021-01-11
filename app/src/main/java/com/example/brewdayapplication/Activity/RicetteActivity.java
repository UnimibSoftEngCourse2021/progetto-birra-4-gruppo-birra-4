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


    DatabaseManager databaseManager;
    ListView listViewRicette;
    FloatingActionButton aggiungiRicetta;
    Dialog dialogRicetta;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_ricette);

        databaseManager = new DatabaseManager(getApplicationContext());
        listViewRicette = findViewById(R.id.layoutListaRicette);
        aggiungiRicetta = findViewById(R.id.addRicetta);

        dialogRicetta = new Dialog(this);
        aggiungiRicetta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogRicetta.setContentView(R.layout.activity_dialog_new_ricetta);
                dialogRicetta.show();
            }
        });

    }


}
