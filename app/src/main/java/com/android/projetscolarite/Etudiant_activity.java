package com.android.projetscolarite;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.projetscolarite.DatabaseAccess.MyDBHelper;
import com.android.projetscolarite.Models.Etudiant;
import com.android.projetscolarite.Models.Filiere;
import com.android.projetscolarite.Models.Inscription;
import com.android.projetscolarite.Models.Module;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Etudiant_activity extends AppCompatActivity {
    TextView intituleFil, nv, semestre;
    BottomNavigationView bottomNavigationView;
    MyDBHelper database;
    Button rechercher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.etudiant);
        bottomNavigationView = findViewById(R.id.navigationetudiant);
        intituleFil = findViewById(R.id.intituleFiliere);
        nv = findViewById(R.id.niveau);
        semestre = findViewById(R.id.semRechEtu);
        database = new MyDBHelper(this);
        rechercher = findViewById(R.id.rechercherEtu);
        rechercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAdd = new Intent(getApplicationContext(), list_Etudiants.class);
                intentAdd.putExtra("Filiere", intituleFil.getText().toString().trim());
                intentAdd.putExtra("Niveau", nv.getText().toString().trim());
                intentAdd.putExtra("Semestre", semestre.getText().toString().trim());

                startActivityForResult(intentAdd,1);
            }
        });


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {

                    case R.id.addEtudiant:
                        Intent intentAdd = new Intent(getApplicationContext(), ajouterEtudiant.class);
                        startActivityForResult(intentAdd,1);
                        break;
                    case R.id.addinscri:
                        Intent intentShow = new Intent(getApplicationContext(), ajouterInscription.class);
                        startActivityForResult(intentShow,2);
                        break;
                }
                return false;
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                Etudiant etudiant = (Etudiant) data.getSerializableExtra("newEtudiant");
                try{
                    long etu_id = database.ajouterEtudiant(etudiant);
                    etudiant.setId_etu((int) etu_id);
                    Toast.makeText(getApplicationContext(),"Etudiant Add Successfully",Toast.LENGTH_SHORT).show();
                }catch(SQLException e){
                    Toast.makeText(getApplicationContext(),"Etudiant UnSuccessfully Added",Toast.LENGTH_SHORT).show();
                }
            }else{

                Toast.makeText(getApplicationContext(),"Something Wrong Happening Try Again",Toast.LENGTH_SHORT).show();
            }

        }
        if(requestCode == 2){
            if(resultCode == RESULT_OK){
                Inscription inscription = (Inscription) data.getSerializableExtra("newInscription");
                try{
                    long etu_id = database.ajouterInscription(inscription);
                    Toast.makeText(getApplicationContext(),"Inscription Add Successfully",Toast.LENGTH_SHORT).show();
                }catch(SQLException e){
                    Toast.makeText(getApplicationContext(),"Inscription UnSuccessfully Added",Toast.LENGTH_SHORT).show();
                }
            }else{

                Toast.makeText(getApplicationContext(),"Something Wrong Happening Try Again",Toast.LENGTH_SHORT).show();
            }

        }
    }


}
