package com.example.brewdayapplication.activity;

import androidx.appcompat.app.AppCompatActivity;


import android.app.AlertDialog;
import android.content.DialogInterface;
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
        btnFeatures = findViewById(R.id.btn_Features);

        databaseManager = new DatabaseManager(getApplicationContext());
        if(databaseManager.mostraRicette().size() == 0)
            btnFeatures.setVisibility(View.INVISIBLE);
        else {
           btnFeatures.setOnClickListener(new BrewDayListener());
        }
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

    private class BrewDayListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            scegliBirra();
        }

        private void scegliBirra() {
            String nomeBirra = "";
            List<Ricetta> listaRicette = databaseManager.mostraRicette();
            List<Ingrediente> listaIngRic;
            List<Ingrediente> listaIngMag = databaseManager.mostraIngredienti();
            double[] quantitaIng = new double[listaRicette.size()];
            int indiceRicettaMax = 0;
            for (int j = 0; j < listaRicette.size(); j++) {
                listaIngRic = listaRicette.get(j).getDispensaIngrediente();
                boolean producibile = true;
                for(int i = 0; i < listaIngRic.size(); i++){
                    int indice = listaIngMag.indexOf(listaIngRic.get(i));
                    if(listaIngMag.get(indice).getQuantita() < listaIngRic.get(i).getQuantita())
                        producibile = false;
                }
                if (!producibile) {
                    quantitaIng[j] = -100000;
                } else {
                    for (int i = 0; i < listaIngRic.size(); i++) {
                        if (i == 1 && listaIngRic.get(0).getQuantita() != 0) {
                            quantitaIng[j] -= listaIngRic.get(i).getQuantita() / listaIngRic.get(0).getQuantita();
                        } else if (listaIngMag.get(i).getQuantita() != 0) {
                            quantitaIng[j] += listaIngRic.get(i).getQuantita() / listaIngMag.get(i).getQuantita();
                        }
                    }
                }
            }
            double max = -1000;
            boolean trova = false;
            for (int i = 0; i < quantitaIng.length; i++) {
                if (quantitaIng[i] > max) {
                    max = quantitaIng[i];
                    indiceRicettaMax = i;
                    trova = true;
                }
            }

            int indice = indiceRicettaMax;
            nomeBirra = listaRicette.get(indiceRicettaMax).getNome();
            AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
            AlertDialog alertDialog;
            if (trova) {
                // toastBack = Toast.makeText(getApplicationContext(), nomeBirra, Toast.LENGTH_SHORT).show();
                String ingredienti = nomeBirra.toUpperCase() + " \n \n";
                for (Ingrediente i : listaRicette.get(indiceRicettaMax).getDispensaIngrediente()) {
                    ingredienti = ingredienti.concat(i.getNome() + " " + i.getQuantita() + "\n");
                }
                //ingredienti = ingredienti.concat("\n \n Vuoi produrre la ricetta?");
                alert.setMessage(ingredienti);
                alert.setTitle("Vuoi produrre la ricetta seguente ?")
                        .setNegativeButton("No", (dialog, which) -> alert.setCancelable(true))
                        .setPositiveButton("Si", (dialog, which) -> {
                            /// CONTROLLO BIRRA
                            databaseManager.produciBirra(listaRicette.get(indice));
                            alert.setCancelable(true);
                        });
                alertDialog = alert.create();
                alertDialog.show();
            } else {
                Toast.makeText(getApplicationContext(), "Non e' possibile produrre nessuna ricetta", Toast.LENGTH_SHORT).show();

            }

        }


    }

}