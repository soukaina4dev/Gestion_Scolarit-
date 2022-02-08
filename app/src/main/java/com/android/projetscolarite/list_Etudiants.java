package com.android.projetscolarite;

import android.app.Dialog;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.projetscolarite.Adapters.etudiantAdapter;

import com.android.projetscolarite.DatabaseAccess.MyDBHelper;
import com.android.projetscolarite.Models.Etudiant;


import java.util.ArrayList;

public class list_Etudiants extends AppCompatActivity implements etudiantAdapter.ShowDialog {
    ListView listView;
    ArrayList<Etudiant> etudiants = new ArrayList();
    MyDBHelper database;
    etudiantAdapter etudiantadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_etudiant);

        Intent intent = getIntent();
        String filiere = intent.getStringExtra("Filiere");
        String nv = intent.getStringExtra("Niveau");
        int semestre = Integer.valueOf(intent.getStringExtra("Semestre"));

        listView = findViewById(R.id.listEtudiant);
        database = new MyDBHelper(this);
        etudiants = database.getAllEtudiants(filiere,nv,semestre);
        Log.i("filiere", String.valueOf(filiere));
        Log.i("nice", String.valueOf(nv));
        etudiantadapter = new etudiantAdapter(this,R.layout.custom_list_etudiant, etudiants);
        listView.setAdapter(etudiantadapter);
    }

    @Override
    public void display(Etudiant etu) {
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.dialog_supprimer);
            dialog.setCancelable(true);
            Button btn  = dialog.findViewById(R.id.SupprimerConfirmation);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try{
                        database.supprimerEtudiant(etu);
                        Toast.makeText(getApplicationContext(),"successfully delete",Toast.LENGTH_SHORT).show();
                        etudiants.remove(etu);
                        etudiantadapter.notifyDataSetChanged();

                    }catch(SQLException e){
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(),"Unsuccessfully delete",Toast.LENGTH_SHORT).show();
                    }




                }
            });
            dialog.show();


    }

}
