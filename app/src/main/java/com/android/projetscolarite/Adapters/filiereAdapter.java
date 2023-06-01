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

import com.android.projetscolarite.Models.Filiere;
import com.android.projetscolarite.R;

import java.util.ArrayList;

public class filiereAdapter extends ArrayAdapter implements View.OnClickListener {
    ArrayList<Filiere> filieres;
    ShowDialog showDialog;

    // Constructor
    public filiereAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Filiere> objects) {
        super(context, resource, objects);
        filieres = objects;
        showDialog = (ShowDialog) context;
    }

    // Interface to display dialog
    public interface ShowDialog {
        public void display(Filiere fil);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            // Inflate the custom layout for the list item
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_filiere, parent, false);
        }

        TextView IntituleFiliere;
        Button Supprimer;

        IntituleFiliere = convertView.findViewById(R.id.IntituleFiliere);
        Supprimer = convertView.findViewById(R.id.Supprimer);

        // Set the data for each view in the list item
        IntituleFiliere.setText(getItem(position).getIntitule());

        // Set the click listener and tag for the "Supprimer" button
        Supprimer.setOnClickListener(this);
        Supprimer.setTag(String.valueOf(position));

        return convertView;
    }


    @Nullable
    @Override
    public Filiere getItem(int position) {
        return filieres.get(position);
    }

    @Override
    public void onClick(View v) {
        // Retrieve the associated filiere object based on the button's tag
        Filiere filiere = getItem(Integer.parseInt((String) v.getTag()));
        // Trigger the display method in the parent context
        showDialog.display(filiere);
    }
}
