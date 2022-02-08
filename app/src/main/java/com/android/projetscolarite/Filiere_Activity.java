package com.android.projetscolarite;

import android.app.Dialog;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.projetscolarite.Adapters.filiereAdapter;
import com.android.projetscolarite.DatabaseAccess.MyDBHelper;
import com.android.projetscolarite.Models.Filiere;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Filiere_Activity extends AppCompatActivity implements filiereAdapter.ShowDialog {
    ListView listView;
    BottomNavigationView bottomNavigationView;
    ArrayList<Filiere> filieres = new ArrayList();
    MyDBHelper database;
    filiereAdapter filiereadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filiere);
        listView = findViewById(R.id.listFiliere);
        bottomNavigationView = findViewById(R.id.navigationfiliere);
        database = new MyDBHelper(this);
        filieres = database.getAllFilieres();
        Log.i("NumberGenerated", String.valueOf(filieres));
        filiereadapter = new filiereAdapter(this,R.layout.custom_list_filiere, filieres);
        listView.setAdapter(filiereadapter);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.addFiliere) {
                    Intent intentAdd = new Intent(getApplicationContext(), ajouterFiliere.class);
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
                Filiere  filiere = (Filiere) data.getSerializableExtra("newFiliere");
                try{
                    long fil_id = database.ajouterFiliere(filiere);
                    filiere.setId_fil((int) fil_id);
                    filieres.add(filiere);
                    filiereadapter.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(),"Filiere Add Successfully",Toast.LENGTH_SHORT).show();
                }catch(SQLException e){
                    Toast.makeText(getApplicationContext(),"Filiere UnSuccessfully Added",Toast.LENGTH_SHORT).show();
                }
            }else{

                Toast.makeText(getApplicationContext(),"Something Wrong Happening Try Again",Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    public void display(Filiere fil) {

        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_supprimer);
        dialog.setCancelable(true);
        Button btn  = dialog.findViewById(R.id.SupprimerConfirmation);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    try{
                        database.supprimerFiliere(fil);
                        Toast.makeText(getApplicationContext(),"successfully delete",Toast.LENGTH_SHORT).show();
                        filieres.remove(fil);
                        filiereadapter.notifyDataSetChanged();

                    }catch(SQLException e){
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(),"Unsuccessfully delete",Toast.LENGTH_SHORT).show();
                    }




            }
        });
        dialog.show();

    }


}
