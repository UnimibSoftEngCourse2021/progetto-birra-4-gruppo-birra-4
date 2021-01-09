package com.example.brewdayapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnIngredienti;


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


    private class StartIngredienteActivity implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), IngredienteActivity.class);
            startActivity(intent);
        }
    }
}