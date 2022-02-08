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

import java.util.ArrayList;

public class Module_activity extends AppCompatActivity  {
    TextView intituleFil, nv, semestre;
    BottomNavigationView bottomNavigationView;
    MyDBHelper database;
    Button rechercher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module);
        intituleFil = findViewById(R.id.intituleFiliere);
        nv = findViewById(R.id.niveau);
        semestre = findViewById(R.id.semeRechMod);
        bottomNavigationView = findViewById(R.id.navigationmodule);
        database = new MyDBHelper(this);
        rechercher = findViewById(R.id.rechercherModule);
        rechercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAdd = new Intent(getApplicationContext(), list_Modules.class);
                intentAdd.putExtra("Filiere", intituleFil.getText().toString().trim());
                intentAdd.putExtra("Niveau", nv.getText().toString().trim());
                intentAdd.putExtra("Semestre", semestre.getText().toString().trim());
                startActivityForResult(intentAdd,1);
            }
        });


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.addMod) {
                    Intent intentAdd = new Intent(getApplicationContext(), ajouterModule.class);
                    startActivityForResult(intentAdd,1);
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
                Module module = (Module) data.getSerializableExtra("newModule");
                String nv = (String) data.getSerializableExtra("niveau");
                int fil_id = (int) data.getSerializableExtra("Filiere");
                int sem = (int) data.getSerializableExtra("Semestre");
                try{
                    long mod_id = database.ajouterModule(module);
                    module.setId_fil((int) mod_id);
                    long id_pla = database.ajouterPlanning(module.getId_mod(),fil_id,nv,sem);
                    Toast.makeText(getApplicationContext(),"Module Add Successfully",Toast.LENGTH_SHORT).show();
                }catch(SQLException e){
                    Toast.makeText(getApplicationContext(),"Module UnSuccessfully Added",Toast.LENGTH_SHORT).show();
                }
            }else{

                Toast.makeText(getApplicationContext(),"Something Wrong Happening Try Again",Toast.LENGTH_SHORT).show();
            }

        }
    }


}
