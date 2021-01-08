package com.example.brewdayapplication;


import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;



public class IngredienteActivity extends AppCompatActivity {


    DatabaseManager databaseManager;
    Button modifica_ingrediente;
    EditText quantitaView;
    Spinner ingredienteView;
    ListView listviewIngredienti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingrediente);


        listviewIngredienti = findViewById(R.id.listview_ingredients);
        modifica_ingrediente = findViewById(R.id.modifica_ingrediente);
        quantitaView = findViewById(R.id.quantita_ingrediente);
        ingredienteView = findViewById(R.id.nome_ingrediente);

        databaseManager = new DatabaseManager(getApplicationContext());
        printList(listviewIngredienti);


        /*definisce la funzione del bottone modifica ingrediente*/
        modifica_ingrediente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ingrediente ingrediente = null;
                try {
                    ingrediente = new Ingrediente(ingredienteView.getSelectedItem().toString(), Double.parseDouble(quantitaView.getText().toString()));
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                }
                databaseManager.saveIngredient(ingrediente);
                printList(listviewIngredienti);
            }
        });
    }

    private void printList(ListView listviewIngredienti) {
        ArrayAdapter<Ingrediente> resultQuery = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, databaseManager.mostraIngredienti());
        listviewIngredienti.setAdapter(resultQuery);
    }


}


