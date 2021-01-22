package com.example.brewdayapplication.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.example.brewdayapplication.Ingrediente;
import com.example.brewdayapplication.R;
import com.example.brewdayapplication.database.DatabaseManager;

public class CapacitaActivity extends AppCompatActivity {

    // Dichiarazioni
    EditText editTextCapacita;
    Button btnConferma;
    private Intent intent;
    DatabaseManager databaseManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment);

        databaseManager = new DatabaseManager(getApplicationContext());

        Ingrediente ingrediente;
        String[] listIngredienti = {"Malto", "Orzo", "Luppolo", "Acqua", "Lievito", "Zucchero", "Additivi"};
        for (String s : listIngredienti) {
            ingrediente = new Ingrediente(s, 0);
            databaseManager.saveIngredient(ingrediente);
        }


        editTextCapacita = findViewById(R.id.editTextNumberDecimal_capacita);
        btnConferma = findViewById(R.id.btn_conferma);



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

    //classe innestata che legge il valore in input lo controlla, lo salva nel db se idoneo e fa partire MainActivity
    private class ImpostaCapacitaListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //controllo dell'input. Se adeguato salva la capacita e la preferences altrimenti crea un toast.
            if (!editTextCapacita.getText().toString().isEmpty() && Double.parseDouble(editTextCapacita.getText().toString()) > 0) {
                databaseManager = new DatabaseManager(getApplicationContext());
                databaseManager.saveCapacita(Double.parseDouble(editTextCapacita.getText().toString()));

                storeSharedPreferences(true);
                //Parte intent per andare in MainActivity
                intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
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
