package com.android.projetscolarite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.android.projetscolarite.DatabaseAccess.MyDBHelper;

import androidx.appcompat.app.AppCompatActivity;


import com.android.projetscolarite.Models.Module;
import com.android.projetscolarite.Models.Planning;


public class ajouterModule extends AppCompatActivity implements View.OnClickListener {
    EditText module;
    EditText NomFiliere;
    EditText Niveau;
    EditText semestreMod;
    MyDBHelper database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add_module);
        getSupportActionBar().hide();
        Button btnAdd = findViewById(R.id.AjouterModule);
        module = findViewById(R.id.module);
        NomFiliere = findViewById(R.id.NomFiliere);
        Niveau = findViewById(R.id.Niveau);
        semestreMod = findViewById(R.id.semestreMod);
        btnAdd.setOnClickListener(this);

        database = new MyDBHelper(this);
    }

    @Override
    public void onClick(View v) {

        String name = module.getText().toString().trim();
        String filiere = NomFiliere.getText().toString().trim();
        String nv = Niveau.getText().toString().trim();
        String sem = semestreMod.getText().toString().trim();

        if(name.equals("")){

            module.setError("Please Fill Product Name");
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

        int id_fil = database.getIdFiliere(filiere);
        if(id_fil != -1){
            Module module = new Module(name);
            Intent intent = new Intent();
            intent.putExtra("newModule", module);
            intent.putExtra("niveau", nv);
            intent.putExtra("Filiere",id_fil);
            intent.putExtra("Semestre",Integer.valueOf(sem));
            setResult(RESULT_OK,intent);
            finish();
        }
        else{
            NomFiliere.setError("Nom du filière est invalide ");
        }
    }

}
