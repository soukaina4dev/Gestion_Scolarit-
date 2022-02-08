package com.android.projetscolarite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.android.projetscolarite.Models.Filiere;

public class ajouterFiliere extends AppCompatActivity implements View.OnClickListener {
    EditText IntituleFiliere;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add_filiere);
        getSupportActionBar().hide();
        Button btnAdd = findViewById(R.id.AjouterFiliere);
        IntituleFiliere = findViewById(R.id.addIntituleFiliere);
        btnAdd.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        String name = IntituleFiliere.getText().toString().trim();

        if(name.equals("")){

            IntituleFiliere.setError("Please Fill Product Name");
            return;
        }

        Filiere filiere = new Filiere(name);
        Intent intent = new Intent();
        intent.putExtra("newFiliere", filiere);
        setResult(RESULT_OK,intent);
        finish();
    }

}
