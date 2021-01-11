package com.example.brewdayapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.brewdayapplication.Ingrediente;
import com.example.brewdayapplication.R;

import java.util.List;


public class ListAdapter extends ArrayAdapter<Ingrediente> {
    public ListAdapter(@NonNull Context context, List<Ingrediente> ingredienteList) {
        super(context, 0, ingredienteList);
    }

    /* permette di creare un adpter personallizato per la listView
     * è un layout personallizato su come stampare la lista
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Ingrediente ingrediente = getItem(position);
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        TextView textView = convertView.findViewById(R.id.layoutListaIngredienti);
        textView.setText(ingrediente.toString());
        return convertView;
    }
}
