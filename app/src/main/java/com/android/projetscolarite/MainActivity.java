package com.android.projetscolarite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;


import com.android.projetscolarite.DatabaseAccess.MyDBHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    MyDBHelper database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.gestFil);
        Button mod = findViewById(R.id.gesMod);
        Button etu = findViewById(R.id.gesEtu);
        Button n = findViewById(R.id.gesNote);

        database = new MyDBHelper(this);

        n.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intentAdd = new Intent(getApplicationContext(), Evaluation_activity.class);
                startActivityForResult(intentAdd,1);
            }
        });

        etu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intentAdd = new Intent(getApplicationContext(), Etudiant_activity.class);
                startActivityForResult(intentAdd,1);
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intentAdd = new Intent(getApplicationContext(), Filiere_Activity.class);
                startActivityForResult(intentAdd,1);
            }
        });

        mod.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intentAdd = new Intent(getApplicationContext(), Module_activity.class);
                startActivityForResult(intentAdd,1);
            }
        });



    }




}