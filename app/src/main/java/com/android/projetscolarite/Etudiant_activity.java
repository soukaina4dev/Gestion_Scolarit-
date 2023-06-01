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
        
        // Initialize the views
        bottomNavigationView = findViewById(R.id.navigationetudiant);
        intituleFil = findViewById(R.id.intituleFiliere);
        nv = findViewById(R.id.niveau);
        semestre = findViewById(R.id.semRechEtu);
         // Initialize the database helper
        database = new MyDBHelper(this);
        
         // Set click listener for the "rechercher" button
        rechercher = findViewById(R.id.rechercherEtu);
        rechercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to start the list_Etudiants activity
                Intent intentAdd = new Intent(getApplicationContext(), list_Etudiants.class);
                
                // Pass the values from the TextViews to the intent as extras
                intentAdd.putExtra("Filiere", intituleFil.getText().toString().trim());
                intentAdd.putExtra("Niveau", nv.getText().toString().trim());
                intentAdd.putExtra("Semestre", semestre.getText().toString().trim());

                startActivityForResult(intentAdd,1);
            }
        });

        // Set the listener for the bottom navigation view
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {

                    case R.id.addEtudiant:
                         // Create an intent to start the ajouterEtudiant activity
                        Intent intentAdd = new Intent(getApplicationContext(), ajouterEtudiant.class);
                        startActivityForResult(intentAdd,1);
                        break;
                    case R.id.addinscri:
                        // Create an intent to start the ajouterInscription activity
                        Intent intentShow = new Intent(getApplicationContext(), ajouterInscription.class);
                        startActivityForResult(intentShow,2);
                        break;
                }
                return false;
            }
        });

    }
    
     // Handle the result from the child activities
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                // Get the newEtudiant object from the intent
                Etudiant etudiant = (Etudiant) data.getSerializableExtra("newEtudiant");
                try{
                    // Add the etudiant to the database
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
                       // Get the newInscription object from the intent
                Inscription inscription = (Inscription) data.getSerializableExtra("newInscription");
                try{
                     // Add the inscription to the database
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
