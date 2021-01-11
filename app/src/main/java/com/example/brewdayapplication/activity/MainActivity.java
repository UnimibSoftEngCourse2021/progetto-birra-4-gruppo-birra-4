package com.example.brewdayapplication.activity;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.brewdayapplication.R;

public class MainActivity extends AppCompatActivity {

    Button btnIngredienti;
    Button btnRicette;
    long backPressedTime;
    Toast toastBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnIngredienti = findViewById(R.id.btn_Ingredienti);
        btnIngredienti.setOnClickListener(new StartIngredienteActivity());

        btnRicette = findViewById(R.id.btn_Ricette);
        btnRicette.setOnClickListener(new StartRicetteActivity());

    }

    //permette di uscire dall'app e non tornare nell'activity che richiede la capacità dell'equipment
    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            toastBack.cancel();
            finishAffinity();
            System.exit(0);
        } else {
            toastBack = Toast.makeText(getApplicationContext(), "Press back again to exit", Toast.LENGTH_SHORT);
            toastBack.show();
        }
        backPressedTime = System.currentTimeMillis();
        btnIngredienti.setOnClickListener(new StartIngredienteActivity());

    }

    //se premuto il bottone, parte IngredienteActivity.class
    private class StartIngredienteActivity implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), IngredienteActivity.class);
            startActivity(intent);
        }
    }

    //se premuto il bottone, parte RicetteActivity.class
    private class StartRicetteActivity implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), RicetteActivity.class);
            startActivity(intent);
        }
    }
}