package com.android.projetscolarite;

import android.app.Dialog;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.projetscolarite.Adapters.evaluationAdapter;
import com.android.projetscolarite.DatabaseAccess.MyDBHelper;
import com.android.projetscolarite.Models.Module;

import java.util.ArrayList;

public class list_notes extends AppCompatActivity implements evaluationAdapter.ShowDialog {
    ListView listView;
    ArrayList<Module> modules = new ArrayList();
    MyDBHelper database;
    evaluationAdapter evaluationadapter;
    String cne;
    double note;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_module);

        Intent intent = getIntent();
        String filiere = intent.getStringExtra("Filiere");
        String nv = intent.getStringExtra("Niveau");
        int semestre = Integer.valueOf(intent.getStringExtra("Semestre"));
        cne = intent.getStringExtra("CNE");


        listView = findViewById(R.id.listModule);
        database = new MyDBHelper(this);
        modules = database.getAllModules(filiere,nv,semestre);
        Log.i("filieeere", String.valueOf(filiere));
        Log.i("nice", String.valueOf(nv));
        evaluationadapter = new evaluationAdapter(this,R.layout.custom_list_note, modules);
        listView.setAdapter(evaluationadapter);


    }

    @Override
    public void display(Module mod) {

        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog);
        dialog.setCancelable(true);
        EditText n = dialog.findViewById(R.id.noteAdd);
        Button btn  = dialog.findViewById(R.id.AddNote);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                note = Double.valueOf(n.getText().toString().trim());

                if(note == 0){
                    n.setError("Please Add note");
                    return;
                }
                dialog.dismiss();

                if( note<=20){
                    try{
                        long idTrans = database.ajouterEvaluation(mod.getId_mod(),cne,note);
                        Toast.makeText(getApplicationContext(),"successfully note",Toast.LENGTH_SHORT).show();
                    }catch(SQLException e){
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(),"Unsuccessfully Operation",Toast.LENGTH_SHORT).show();
                    }

                }else{

                    Toast.makeText(getApplicationContext(),"Quantity Insufficient",Toast.LENGTH_SHORT).show();
                }

            }
        });
        dialog.show();

    }
}
