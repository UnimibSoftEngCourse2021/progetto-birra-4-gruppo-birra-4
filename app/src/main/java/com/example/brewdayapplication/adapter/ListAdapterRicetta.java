package com.example.brewdayapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.brewdayapplication.R;
import com.example.brewdayapplication.Ricetta;

import java.util.List;

public class ListAdapterRicetta extends ArrayAdapter<Ricetta> {
    public ListAdapterRicetta(@NonNull Context context, List<Ricetta> RicettaList) {
        super(context, 0, RicettaList);
    }

    /* permette di creare un adpter personallizato per la listView
     * è un layout personallizato su come stampare la lista
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Ricetta ricetta = getItem(position);
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        TextView textView = convertView.findViewById(R.id.layoutListaIngredienti);
        textView.setText(ricetta.toString());
        return convertView;
    }
}
