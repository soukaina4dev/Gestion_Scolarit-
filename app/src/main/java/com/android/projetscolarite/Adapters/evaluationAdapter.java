package com.android.projetscolarite.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.projetscolarite.Models.Module;
import com.android.projetscolarite.R;

import java.util.ArrayList;

public class evaluationAdapter extends ArrayAdapter implements View.OnClickListener {
    ArrayList<Module> modules;
    ShowDialog showDialog;

    // Constructor
    public evaluationAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Module> objects) {
        super(context, resource, objects);
        modules = objects;
        showDialog = (ShowDialog) context;
    }

    // Interface to display dialog
    public interface ShowDialog {
        public void display(Module mod);
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            // Inflate the custom layout for the list item
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_note, parent, false);
        }

        TextView nomMod;
        Button SupprimerMod;

        nomMod = convertView.findViewById(R.id.nomMod);
        SupprimerMod = convertView.findViewById(R.id.ajouterNote);

        // Set the data for each view in the list item
        nomMod.setText(getItem(position).getNom());

        // Set the click listener and tag for the "SupprimerMod" button
        SupprimerMod.setOnClickListener(this);
        SupprimerMod.setTag(String.valueOf(position));

        return convertView;
    }

    // Override to get the module object at the specified position
    @Nullable
    @Override
    public Module getItem(int position) {
        return modules.get(position);
    }

    // Handle button click event
    @Override
    public void onClick(View v) {
        // Retrieve the associated module object based on the button's tag
        Module module = getItem(Integer.parseInt((String) v.getTag()));
        // Trigger the display method in the parent context
        showDialog.display(module);
    }
}
