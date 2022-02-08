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

    public etudiantAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Etudiant> objects){
        super(context,resource,objects);
        etudiants = objects;
        showDialog = (ShowDialog) context;
    }

    public interface ShowDialog{
        public void display(Etudiant etu);
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null){

            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_etudiant,parent,false);
        }

        TextView prenomlist,cneList,nomlist;
        Button SupprimerEtu;

        prenomlist = convertView.findViewById(R.id.prenomlist);
        nomlist = convertView.findViewById(R.id.nomlist);
        cneList = convertView.findViewById(R.id.cneList);
        SupprimerEtu = convertView.findViewById(R.id.SupprimerEtu);

        cneList.setText(getItem(position).getCNE());
        nomlist.setText(getItem(position).getNom());
        prenomlist.setText(getItem(position).getPrenom());
        SupprimerEtu.setOnClickListener(this);
        SupprimerEtu.setTag(String.valueOf(position));
        return convertView;
    }

    @Nullable
    @Override
    public Etudiant getItem(int position) {
        return etudiants.get(position);
    }

    @Override
    public void onClick(View v) {

        Etudiant etudiant = getItem(Integer.parseInt((String) v.getTag()));
        showDialog.display(etudiant);
    }
}
