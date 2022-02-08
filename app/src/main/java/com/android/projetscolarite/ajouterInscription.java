package com.android.projetscolarite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.android.projetscolarite.DatabaseAccess.MyDBHelper;
import com.android.projetscolarite.Models.Etudiant;
import com.android.projetscolarite.Models.Inscription;

public class ajouterInscription extends AppCompatActivity implements View.OnClickListener {
    EditText cne;
    EditText semestre;
    EditText NomFiliere;
    EditText Niveau;
    MyDBHelper database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add_inscription);
        getSupportActionBar().hide();
        Button btnAdd = findViewById(R.id.AjouterInscription);
        cne = findViewById(R.id.cne);
        semestre = findViewById(R.id.semestre);
        NomFiliere = findViewById(R.id.NomFiliere);
        Niveau = findViewById(R.id.Niveau);
        btnAdd.setOnClickListener(this);

        database = new MyDBHelper(this);
    }

    @Override
    public void onClick(View v) {

        String case1 = cne.getText().toString().trim();
        String case2 = semestre.getText().toString().trim();
        String filiere = NomFiliere.getText().toString().trim();
        String nv = Niveau.getText().toString().trim();


        if(case1.equals("")){

            cne.setError("Please Fill Product Name");
            return;
        }
        if(case2.equals("")){

            semestre.setError("Please Fill Product Name");
            return;
        }

        if(filiere.equals("")){

            NomFiliere.setError("Please Fill Product Name");
            return;
        }
        if(nv.equals("")){

            Niveau.setError("Please Fill Product Name");
            return;
        }

        int id_etu = database.getIdEtudiant(case1);
        int id_fil = database.getIdFiliere(filiere);
        if(id_fil != -1 && id_etu !=-1){
            Inscription inscription = new Inscription(id_etu,id_fil,nv,Integer.valueOf(case2));
            Intent intent = new Intent();
            intent.putExtra("newInscription", inscription);
            setResult(RESULT_OK,intent);
            finish();
        }
        else{
            NomFiliere.setError("Vérifier le cne ou le niveau ");
        }
    }

}
