package com.example.brewdayapplication.activity;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.brewdayapplication.Ingrediente;
import com.example.brewdayapplication.R;
import com.example.brewdayapplication.Ricetta;
import com.example.brewdayapplication.database.DatabaseManager;

import java.text.ParseException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnIngredienti;
    Button btnRicette;
    long backPressedTime;
    Toast toastBack;
    //in più
    Button btnFeatures;
    DatabaseManager db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnIngredienti = findViewById(R.id.btn_Ingredienti);
        btnIngredienti.setOnClickListener(new StartIngredienteActivity());

        btnRicette = findViewById(R.id.btn_Ricette);
        btnRicette.setOnClickListener(new StartRicetteActivity());

        //in più
        btnFeatures = findViewById(R.id.btn_Features);
        db = new DatabaseManager(getApplicationContext());
        btnFeatures.setOnClickListener(new View.OnClickListener(){public void onClick(View v){
            try {
                scegliBirra();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        });
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



    //in più
    public void scegliBirra() throws ParseException {
        String nomeBirra = "";
        List<Ricetta> listaRicette;
        listaRicette = db.mostraRicette();
        Ricetta ricetta;
        List<Ingrediente> listaIngRic;
        List<Ingrediente> listaIngMag = db.mostraIngredienti();
        double[] quantitaIng = new double[listaRicette.size()];
        int indiceRicettaMax = 1;
        for(int i =0; i<listaRicette.size(); i++){
            ricetta = listaRicette.get(i);
            listaIngRic = ricetta.getDispensaIngrediente();
            for(int j=0, ok=0; j<listaIngRic.size() && ok==0; j++){
                if(listaIngMag.get(j).getQuantita() > listaIngRic.get(j).getQuantita()){
                    quantitaIng[i] = listaIngRic.get(j).getQuantita()/listaIngMag.get(j).getQuantita();
                }
                else {
                    quantitaIng[i] = 0;
                    ok=1;
                };
            }
        }

        double max=0;
        for(int i=0; i<quantitaIng.length; i++){
            if (quantitaIng[i]>max) {
                max = quantitaIng[i];
                indiceRicettaMax = i;
            }
        }

        nomeBirra = listaRicette.get(indiceRicettaMax).getNome();

        toastBack = Toast.makeText(getApplicationContext(), nomeBirra, Toast.LENGTH_SHORT);
        toastBack.show();
    }
}