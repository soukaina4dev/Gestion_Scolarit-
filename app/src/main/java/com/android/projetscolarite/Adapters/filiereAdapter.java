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

    public filiereAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Filiere> objects) {
        super(context, resource, objects);
        filieres = objects;
        showDialog = (ShowDialog) context;
    }
    public interface  ShowDialog{
        public void display(Filiere fil);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null){

            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_filiere,parent,false);
        }

        TextView IntituleFiliere;
        Button Supprimer;
        IntituleFiliere = convertView.findViewById(R.id.IntituleFiliere);
        Supprimer = convertView.findViewById(R.id.Supprimer);
        IntituleFiliere.setText(getItem(position).getIntitule());
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

        Filiere filiere = getItem(Integer.parseInt((String) v.getTag()));
        showDialog.display(filiere);
    }
}
