package com.example.brewdayapplication;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnIngredienti;
    long backPressedTime;
    Toast toastBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*commento prova Jury*/
        /*commento prova lara*/
        /* commento Ale*/
        /* commento Simone */


        btnIngredienti = findViewById(R.id.btn_Ingredienti);
        btnIngredienti.setOnClickListener(new StartIngredienteActivity());


    }

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


    private class StartIngredienteActivity implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), IngredienteActivity.class);
            startActivity(intent);
        }
    }
}