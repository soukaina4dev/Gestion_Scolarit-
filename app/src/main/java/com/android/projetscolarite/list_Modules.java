package com.android.projetscolarite;

import android.app.Dialog;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.projetscolarite.Adapters.moduleAdapter;
import com.android.projetscolarite.DatabaseAccess.MyDBHelper;
import com.android.projetscolarite.Models.Filiere;
import com.android.projetscolarite.Models.Module;

import java.util.ArrayList;

public class list_Modules extends AppCompatActivity implements moduleAdapter.ShowDialog {
    ListView listView;
    ArrayList<Module> modules = new ArrayList();
    MyDBHelper database;
    moduleAdapter moduleadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_module);

        Intent intent = getIntent();
        String filiere = intent.getStringExtra("Filiere");
        String nv = intent.getStringExtra("Niveau");
        int semestre = Integer.valueOf(intent.getStringExtra("Semestre"));

        listView = findViewById(R.id.listModule);
        database = new MyDBHelper(this);
        modules = database.getAllModules(filiere,nv,semestre);
        Log.i("filieeere", String.valueOf(filiere));
        Log.i("nice", String.valueOf(nv));
        moduleadapter = new moduleAdapter(this,R.layout.custom_list_module, modules);
        listView.setAdapter(moduleadapter);


    }

    @Override
    public void display(Module mod) {

        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_supprimer);
        dialog.setCancelable(true);
        Button btn  = dialog.findViewById(R.id.SupprimerConfirmation);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    database.supprimerModule(mod);
                    Toast.makeText(getApplicationContext(),"successfully delete",Toast.LENGTH_SHORT).show();
                    modules.remove(mod);
                    moduleadapter.notifyDataSetChanged();

                }catch(SQLException e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Unsuccessfully delete",Toast.LENGTH_SHORT).show();
                }




            }
        });
        dialog.show();

    }
}
