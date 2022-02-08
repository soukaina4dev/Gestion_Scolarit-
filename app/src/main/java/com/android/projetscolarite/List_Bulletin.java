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

import com.android.projetscolarite.Adapters.noteAdapter;
import com.android.projetscolarite.DatabaseAccess.MyDBHelper;
import com.android.projetscolarite.Models.Evaluation;
import com.android.projetscolarite.R;

import java.util.ArrayList;

public class List_Bulletin extends AppCompatActivity implements noteAdapter.ShowDialog {
    ListView listView;
    ArrayList<Evaluation> evaluations = new ArrayList();
    MyDBHelper database;
    noteAdapter noteadapter;
    String cne;
    double note;
    TextView cneBulletins,nivBull,filBull,semBull,moyenne,classement;

    TextView NomBulletins;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_note);

        database = new MyDBHelper(this);

        Intent intent = getIntent();
        String filiere = intent.getStringExtra("Filiere");
        String nv = intent.getStringExtra("Niveau");
        int semestre = Integer.valueOf(intent.getStringExtra("Semestre"));
        cne = intent.getStringExtra("CNE");

        Log.i("nice", String.valueOf(cne));
        listView = findViewById(R.id.listNote);
        classement = findViewById(R.id.classement);
        moyenne = findViewById(R.id.moyenne);
        semBull = findViewById(R.id.semBull);
        filBull = findViewById(R.id.filBull);
        nivBull = findViewById(R.id.nivBull);
        NomBulletins = findViewById(R.id.NomBulletins);
        cneBulletins = findViewById(R.id.cneBulletins);
        cneBulletins.setText(cne);


        NomBulletins.setText(database.getEtudiant(cne));


        nivBull.setText(nv);


        filBull.setText(filiere);


        semBull.setText(Integer.toString(semestre));

        moyenne.setText(Double.toString(database.getMoyenne(cne,filiere,nv,semestre)));


        classement.setText(Double.toString(database.getMoyenneFiliere(filiere,nv,semestre)));
        database = new MyDBHelper(this);
        evaluations = database.getAllNotes(cne,filiere,nv,semestre);
        Log.i("filieeere", String.valueOf(filiere));
        Log.i("nice", String.valueOf(nv));
        noteadapter = new noteAdapter(this,R.layout.custom_list_bulltins, evaluations);
        listView.setAdapter(noteadapter);
        Button refresh = findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moyenne.setText(Double.toString(database.getMoyenne(cne,filiere,nv,semestre)));
                classement.setText(Double.toString(database.getMoyenneFiliere(filiere,nv,semestre)));

            }
        });
    }

    @Override
    public void display(Evaluation eva) {

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
                        database.modifierNote(eva.getId_mod(),cne,note);
                        Toast.makeText(getApplicationContext(),"successfully note",Toast.LENGTH_SHORT).show();
                        int prodId = evaluations.indexOf(eva);
                        evaluations.get(prodId).setNote(note);
                        noteadapter.notifyDataSetChanged();
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
