package com.example.brewdayapplication;


import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class NewRicettaActivity extends AppCompatActivity {
    private ArrayList<String> myItems;
    private ListView lvItems;
    private ArrayAdapter<String> arrayAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_ricetta);

        myItems = new ArrayList<>();
        myItems.add("Malto");
        myItems.add("Orzo");
        myItems.add("Lievito");
        myItems.add("Acqua");
        myItems.add("Additivi");
        myItems.add("Zuccheri");

        lvItems = findViewById(R.id.listaIngredientiRicetta);
        arrayAdapter = new ArrayAdapter<>(this, R.layout.list_item, myItems);
        lvItems.setAdapter(arrayAdapter);


    }


}

