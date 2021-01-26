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

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnIngredienti;
    Button btnRicette;
    long backPressedTime;
    Toast toastBack;
    //in più
    Button btnFeatures;
    DatabaseManager databaseManager;


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
        databaseManager = new DatabaseManager(getApplicationContext());
        btnFeatures.setOnClickListener(new BrewDayListener());
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

    private class BrewDayListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            scegliBirra();
        }

        //in più
        private void scegliBirra() {
            String nomeBirra = "";
            List<Ricetta> listaRicette;
            listaRicette = databaseManager.mostraRicette();
            List<Ingrediente> listaIngRic;
            List<Ingrediente> listaIngMag = databaseManager.mostraIngredienti();
            double[] quantitaIng = new double[listaRicette.size()];
            int indiceRicettaMax = 0;
            boolean ok = true;
            for(int j=0; j<databaseManager.mostraRicette().size();j++) {
                ok = true;
                listaIngRic = databaseManager.getIngredientiRicetta(databaseManager.readIdRicetta(listaRicette.get(j)));
                for (int i = 0; i < listaIngRic.size() && ok; i++) {
                    if (listaIngMag.get(i).getQuantita() < listaIngRic.get(i).getQuantita()) {
                        quantitaIng[j] = 0;
                        ok = false;
                    }
                    else{
                        if(i==6){
                            if(listaIngRic.get(3).getQuantita()==0){
                                //non fare nulla
                            }
                            else{
                                 quantitaIng[j] -= listaIngRic.get(i).getQuantita() / listaIngRic.get(3).getQuantita();
                            }
                        }
                        else{
                            if(listaIngMag.get(i).getQuantita()==0){
                                //non fare nulla
                            }
                            quantitaIng[j] += listaIngRic.get(i).getQuantita() / listaIngMag.get(i).getQuantita();
                        }
                    }
                }
            }

            double max = 0;
            for (int i = 0; i < quantitaIng.length; i++) {
                if (quantitaIng[i] > max) {
                    max = quantitaIng[i];
                    indiceRicettaMax = i;
                }
            }

            nomeBirra = listaRicette.get(indiceRicettaMax).getNome();


            toastBack = Toast.makeText(getApplicationContext(), nomeBirra, Toast.LENGTH_SHORT);
            toastBack.show();
        }


    }

}