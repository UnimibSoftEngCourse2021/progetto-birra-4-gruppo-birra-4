package com.example.brewdayapplication.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
    Button btnFeatures;
    DatabaseManager databaseManager;

    AlertDialog.Builder alert;
    AlertDialog alertDialog;
    List<Ricetta> listaRicette;
    int indice;

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
        // se non ci sono ricette nel db, non si da' l'oppurtina di usare "what should i brew today?"
        if (databaseManager.mostraRicette().isEmpty())
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
            String nomeBirra;
            listaRicette = databaseManager.mostraRicette();
            List<Ingrediente> listaIngRic;
            double[] quantitaIng = new double[listaRicette.size()];
            int indiceRicettaMax = 0;

            // scorre la lista di ricette presenti nel db
            for (int j = 0; j < listaRicette.size(); j++) {
                listaIngRic = listaRicette.get(j).getDispensaIngrediente();
                /*scorre gli ingredienti di una ricetta e verifica se essi sono producibili ovvero ci sia abbastanza quantità
                  in magazzino per ogni ingrediente in ricetta */

                if (!producibile(listaRicette.get(j)))
                    // assegnato valore infinitesimale simbolico per massimizzazione ingredienti
                    quantitaIng[j] = -100000;
                else {
                    quantitaIng[j] = valoreDiScelta(listaIngRic);
                }
            }
            // assegnato valore infinitesimale simbolico per massimizzazione ingredienti
            double max = -1000;
            boolean trova = false;
            // viene trovata la ricetta con valore di gradimento maggiore
            for (int i = 0; i < quantitaIng.length; i++) {
                if (quantitaIng[i] > max) {
                    max = quantitaIng[i];
                    indiceRicettaMax = i;
                    trova = true;
                }
            }

            // serve per mostrare a video il risultato
            indice = indiceRicettaMax;
            nomeBirra = listaRicette.get(indiceRicettaMax).getNome();
            alert = new AlertDialog.Builder(MainActivity.this);

            if (trova) {
                String ingredienti = nomeBirra.toUpperCase() + " \n \n";
                for (Ingrediente i : listaRicette.get(indiceRicettaMax).getDispensaIngrediente())
                    ingredienti = ingredienti.concat(i.getNome() + " " + i.getQuantita() + " g \n");

                alert.setMessage(ingredienti);
                alert.setTitle("Vuoi produrre la ricetta seguente ?")
                        .setPositiveButton("Si", new ProduzioneAffermativaListener())
                        .setNegativeButton("No", new ProduzioneNegativaListener());

                alertDialog = alert.create();
                alertDialog.show();
            } else
                Toast.makeText(getApplicationContext(), "Non e' possibile produrre nessuna ricetta", Toast.LENGTH_SHORT).show();
        }

        //metodo che dice se una ricetta è producibile, creato per diminuire la complessità di scegliBirra
        public boolean producibile(Ricetta ricetta){
            List<Ingrediente> listaIngRic = ricetta.getDispensaIngrediente();
            List<Ingrediente> listaIngMag = databaseManager.mostraIngredienti();
            int k = 0;
            boolean producibile = true;
            while (k < listaIngRic.size() && producibile) {
                int index = listaIngMag.indexOf(listaIngRic.get(k));
                if (listaIngMag.get(index).getQuantita() < listaIngRic.get(k).getQuantita())
                    producibile = false;
                k++;
            }
            return producibile;
        }

        public double valoreDiScelta(List<Ingrediente> listaIngRic){
            List<Ingrediente> listaIngMag = databaseManager.mostraIngredienti();
            double quantitaIng=0;
            // scorre gli ingredienti di una ricetta
            for (int i = 0; i < listaIngRic.size(); i++) {
                        /* viene scelta la  ricetta che consuma la maggior quantità in percentuale di ingredienti in magazzino
                           preferendo la birra con minor quantità di additivi (rispetto alla quantità di acqua).
                           Il valore massimo è 6, che corrisponde al consumo totale degli ingredienti in magazzino (esclusi gli additivi) */
                if (i == 1 && listaIngRic.get(0).getQuantita() != 0)
                    quantitaIng -= listaIngRic.get(i).getQuantita() / listaIngRic.get(0).getQuantita();
                else if (listaIngMag.get(i).getQuantita() != 0)
                    quantitaIng += listaIngRic.get(i).getQuantita() / listaIngMag.get(i).getQuantita();

            }
            return quantitaIng;
        }
    }

    private class ProduzioneAffermativaListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            databaseManager.produciBirra(listaRicette.get(indice));
            alert.setCancelable(true);
        }
    }

    private class ProduzioneNegativaListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            alert.setCancelable(true);
        }
    }

}