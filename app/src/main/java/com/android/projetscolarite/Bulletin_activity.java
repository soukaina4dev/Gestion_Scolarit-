package com.android.projetscolarite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.projetscolarite.DatabaseAccess.MyDBHelper;

public class Bulletin_activity extends AppCompatActivity {
    TextView cne,intituleFil, nv, semestre;
    MyDBHelper database;
    Button rechercher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_bulletins);
        
        // Initialize the TextViews
        cne = findViewById(R.id.cneNote);
        intituleFil = findViewById(R.id.intituleFiliere2);
        nv = findViewById(R.id.niveau);
        semestre = findViewById(R.id.semRechEtu);
        
        // Initialize the database helper
        database = new MyDBHelper(this);
        
        // Initialize the search button
        rechercher = findViewById(R.id.rech);
        rechercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to start the List_Bulletin activity
                Intent intentAdd = new Intent(getApplicationContext(), List_Bulletin.class);
                
                // Pass the values from the TextViews to the intent as extras
                intentAdd.putExtra("Filiere", intituleFil.getText().toString().trim());
                intentAdd.putExtra("Niveau", nv.getText().toString().trim());
                intentAdd.putExtra("Semestre", semestre.getText().toString().trim());
                intentAdd.putExtra("CNE", cne.getText().toString().trim());
                
                // Start the List_Bulletin activity and wait for a result
                startActivityForResult(intentAdd,1);
            }
        });

    }


}
