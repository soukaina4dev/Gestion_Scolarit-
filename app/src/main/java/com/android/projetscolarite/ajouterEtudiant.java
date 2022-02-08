package com.android.projetscolarite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.android.projetscolarite.DatabaseAccess.MyDBHelper;
import com.android.projetscolarite.Models.Etudiant;


public class ajouterEtudiant extends AppCompatActivity implements View.OnClickListener {
    EditText cne;
    EditText nom;
    EditText prenom;
    MyDBHelper database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add_etudiant);
        getSupportActionBar().hide();
        Button btnAdd = findViewById(R.id.AjouterEtudiant);
        cne = findViewById(R.id.cne);
        nom = findViewById(R.id.nom);
        prenom = findViewById(R.id.prenom);
        btnAdd.setOnClickListener(this);

        database = new MyDBHelper(this);
    }

    @Override
    public void onClick(View v) {

        String case1 = cne.getText().toString().trim();
        String case2 = nom.getText().toString().trim();
        String case3 = prenom.getText().toString().trim();

        if(case1.equals("")){

            cne.setError("Please Fill Product Name");
            return;
        }
        if(case2.equals("")){

            nom.setError("Please Fill Product Name");
            return;
        }
        if(case3.equals("")){

            prenom.setError("Please Fill Product Name");
            return;
        }

            Etudiant etudiant = new Etudiant(case1,case2,case3);
            Intent intent = new Intent();
            intent.putExtra("newEtudiant", etudiant);
            setResult(RESULT_OK,intent);
            finish();
    }

}
