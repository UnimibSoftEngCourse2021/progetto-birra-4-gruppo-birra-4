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

    EditText editTextCapacita;
    Button btnConferma;
    private Intent intent;
    private double capacita;
    DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment);

        editTextCapacita = findViewById(R.id.editTextNumberDecimal_capacita);
        btnConferma = findViewById(R.id.btn_conferma);

        databaseManager = new DatabaseManager(getApplicationContext());

        //se è presente una preferences vuol dire che l'utente ha già inserito la capacità perciò parte MainActivity.
        //Altrimenti vuol dire che è la prima volta che l'utente apre l'app dopo l'installazione e quindi gli si viene
        //chiesto di inserire la capacità
        if (getSharedPreferences()) {
            intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }

        btnConferma.setOnClickListener(new ImpostaCapacitaListener());
    }

    //legge preferences
    private boolean getSharedPreferences() {
        SharedPreferences mSharedPreferences = getSharedPreferences("ButtonValue", MODE_PRIVATE);
        return mSharedPreferences.getBoolean("button", false);
    }

    private class ImpostaCapacitaListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            //controllo dell'input. Se adeguato salva la capacita e la preferences altrimenti crea un toast.
            if (!editTextCapacita.getText().toString().isEmpty() && Double.parseDouble(editTextCapacita.getText().toString()) > 0) {
                capacita = Double.parseDouble(editTextCapacita.getText().toString());
                databaseManager = new DatabaseManager(getApplicationContext());
                databaseManager.saveCapacita(capacita);

                storeSharedPreferences(true);
                //Parte intent per andare in MainActivity
                intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
            Toast.makeText(getApplicationContext(), "Inserire un numero positivo", Toast.LENGTH_SHORT).show();
        }

        //salva preferences.
        private void storeSharedPreferences(Boolean b) {
            SharedPreferences mSharedPreferences = getSharedPreferences("ButtonValue", MODE_PRIVATE);
            SharedPreferences.Editor mEditor = mSharedPreferences.edit();
            mEditor.putBoolean("button", b);
            mEditor.apply();
        }
    }
}
