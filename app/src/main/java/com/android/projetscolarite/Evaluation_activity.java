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
import com.android.projetscolarite.Models.Module;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Evaluation_activity extends AppCompatActivity {
    TextView cne,intituleFil, nv, semestre;
    BottomNavigationView bottomNavigationView;
    MyDBHelper database;
    Button rechercher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note);
        cne = findViewById(R.id.cneNote);
        intituleFil = findViewById(R.id.intituleFiliere2);
        nv = findViewById(R.id.niveau);
        semestre = findViewById(R.id.semRechEtu);
        bottomNavigationView = findViewById(R.id.navigationevaluation);
        database = new MyDBHelper(this);
        rechercher = findViewById(R.id.rech);
        rechercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAdd = new Intent(getApplicationContext(), list_notes.class);
                intentAdd.putExtra("Filiere", intituleFil.getText().toString().trim());
                intentAdd.putExtra("Niveau", nv.getText().toString().trim());
                intentAdd.putExtra("Semestre", semestre.getText().toString().trim());
                intentAdd.putExtra("CNE", cne.getText().toString().trim());
                startActivityForResult(intentAdd,1);
            }
        });


       bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.ModNote) {
                    Intent intentAdd = new Intent(getApplicationContext(), Bulletin_activity.class);
                    startActivityForResult(intentAdd,1);
                }
                return false;
                /*switch(item.getItemId()) {

                    case R.id.addEtudiant:
                        Intent intentAdd = new Intent(getApplicationContext(), ajouterEtudiant.class);
                        startActivityForResult(intentAdd,1);
                        break;
                    case R.id.addinscri:
                        Intent intentShow = new Intent(getApplicationContext(), ajouterInscription.class);
                        startActivityForResult(intentShow,2);
                        break;
                }
                return false;*/
            }
        });

    }

}
