package com.example.brewdayapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;


public class ListAdapter extends ArrayAdapter<Ingrediente> {
    public ListAdapter(@NonNull Context context, List<Ingrediente> ingredienteList) {
        super(context, 0, ingredienteList);
    }

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
