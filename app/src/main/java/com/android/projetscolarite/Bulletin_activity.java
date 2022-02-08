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
        cne = findViewById(R.id.cneNote);
        intituleFil = findViewById(R.id.intituleFiliere2);
        nv = findViewById(R.id.niveau);
        semestre = findViewById(R.id.semRechEtu);
        database = new MyDBHelper(this);
        rechercher = findViewById(R.id.rech);
        rechercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAdd = new Intent(getApplicationContext(), List_Bulletin.class);
                intentAdd.putExtra("Filiere", intituleFil.getText().toString().trim());
                intentAdd.putExtra("Niveau", nv.getText().toString().trim());
                intentAdd.putExtra("Semestre", semestre.getText().toString().trim());
                intentAdd.putExtra("CNE", cne.getText().toString().trim());
                startActivityForResult(intentAdd,1);
            }
        });

    }


}
