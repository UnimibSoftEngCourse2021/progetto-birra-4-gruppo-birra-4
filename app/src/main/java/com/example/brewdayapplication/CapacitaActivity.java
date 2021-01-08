package com.example.brewdayapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CapacitaActivity extends AppCompatActivity {

    EditText editText_capacita;
    Button btn_conferma;
    private Intent intent;
    private double capacita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment);

        editText_capacita = findViewById(R.id.editTextNumberDecimal_capacita);
        btn_conferma = findViewById(R.id.btn_conferma);

        //se è presente una preferences vuol dire che l'utente ha già inserito la capacità perciò parte MainActivity.
        //Altrimenti vuol dire che è la prima volta che l'utente apre l'app dopo l'installazione e quindi gli si viene
        //chiesto di inserire la capacità
        if (getSharedPreferences()) {
            intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }

        btn_conferma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //controllo dell'input. Se adeguato salva la capacita e la preferences altrimenti crea un toast.
                if (!editText_capacita.getText().toString().isEmpty() && Double.parseDouble(editText_capacita.getText().toString()) > 0) {
                    capacita = Double.parseDouble(editText_capacita.getText().toString());
                    /*salvataggio della capacità nel database*/
                    storeSharedPreferences(true);
                    //Parte intent per andare in MainActivity
                    intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
                Toast.makeText(getApplicationContext(), "Inserire un numero positivo", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //salva preferences.
    private void storeSharedPreferences(Boolean b) {
        SharedPreferences mSharedPreferences = getSharedPreferences("ButtonValue", MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putBoolean("button", b);
        mEditor.apply();
    }

    //legge preferences
    private boolean getSharedPreferences() {
        SharedPreferences mSharedPreferences = getSharedPreferences("ButtonValue", MODE_PRIVATE);
        return mSharedPreferences.getBoolean("button", false);
    }

    public double getCapacita() {
        return capacita;
    }

    public void setCapacita(double capacita) {
        this.capacita = capacita;
    }
}