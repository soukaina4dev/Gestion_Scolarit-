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
import com.android.projetscolarite.Models.Etudiant;
import com.android.projetscolarite.Models.Module;
import com.android.projetscolarite.R;

import java.util.ArrayList;

public class etudiantAdapter extends ArrayAdapter implements View.OnClickListener {
    ArrayList<Etudiant> etudiants;
    ShowDialog showDialog;

    // Constructor
    public etudiantAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Etudiant> objects){
        super(context, resource, objects);
        etudiants = objects;
        showDialog = (ShowDialog) context;
    }

    // Interface to display dialog
    public interface ShowDialog{
        public void display(Etudiant etu);
    }

    // Method to get the custom view for each list item
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null){
            // Inflate the custom layout for the list item
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_etudiant, parent, false);
        }

        TextView prenomlist, cneList, nomlist;
        Button SupprimerEtu;

        prenomlist = convertView.findViewById(R.id.prenomlist);
        nomlist = convertView.findViewById(R.id.nomlist);
        cneList = convertView.findViewById(R.id.cneList);
        SupprimerEtu = convertView.findViewById(R.id.SupprimerEtu);

        // Set the data for each view in the list item
        cneList.setText(getItem(position).getCNE());
        nomlist.setText(getItem(position).getNom());
        prenomlist.setText(getItem(position).getPrenom());
        
        // Set the click listener and tag for the "SupprimerEtu" button
        SupprimerEtu.setOnClickListener(this);
        SupprimerEtu.setTag(String.valueOf(position));
        
        return convertView;
    }

    // Override to get the student object at the specified position
    @Nullable
    @Override
    public Etudiant getItem(int position) {
        return etudiants.get(position);
    }

    // Handle button click event
    @Override
    public void onClick(View v) {
        // Retrieve the associated student object based on the button's tag
        Etudiant etudiant = getItem(Integer.parseInt((String) v.getTag()));
        // Trigger the display method in the parent context
        showDialog.display(etudiant);
    }
}
