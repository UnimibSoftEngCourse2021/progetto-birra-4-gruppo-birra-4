package com.example.brewdayapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

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
        aggiungiRicetta.setOnClickListener(new StartNewRicetteActivity());


    }
    private class StartNewRicetteActivity implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), NewRicettaActivity.class);
            startActivity(intent);
        }
    }


}
