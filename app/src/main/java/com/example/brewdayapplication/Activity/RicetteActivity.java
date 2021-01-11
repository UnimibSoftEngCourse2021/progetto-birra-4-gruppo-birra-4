package com.example.brewdayapplication.Activity;

import android.os.Bundle;
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


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_ricette);

        databaseManager = new DatabaseManager(getApplicationContext());
        listViewRicette = findViewById(R.id.layoutListaRicette);
        aggiungiRicetta = findViewById(R.id.addRicetta);

    }



}
