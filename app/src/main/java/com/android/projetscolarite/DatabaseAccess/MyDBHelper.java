package com.android.projetscolarite.DatabaseAccess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.android.projetscolarite.Models.Etudiant;
import com.android.projetscolarite.Models.Evaluation;
import com.android.projetscolarite.Models.Filiere;
import com.android.projetscolarite.Models.Inscription;
import com.android.projetscolarite.Models.Module;

import java.util.ArrayList;

public class MyDBHelper extends SQLiteOpenHelper {

    public static final String id_fil = "id_fil";
    public static final String id_mod = "id_mod";
    public static final String id_etu = "id_etu";
    public static final String CNE = "CNE";

    public MyDBHelper(@Nullable Context context) {
        super(context, "gestion_scolarite",null,  1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Filiere (id_fil INTEGER NOT NULL PRIMARY KEY autoIncrement,intitule TEXT NOT NULL);");
        db.execSQL("CREATE TABLE  Etudiant(id_etu INTEGER NOT NULL PRIMARY KEY autoIncrement, CNE TEXT NOT NULL ,Nom TEXT NOT NULL ,Prenom TEXT NOT NULL );");
        db.execSQL("CREATE TABLE Module (id_mod INTEGER NOT NULL PRIMARY KEY autoIncrement,Nom TEXT NOT NULL);");
        db.execSQL("CREATE TABLE Evaluation (id_mod INTEGER NOT NULL,id_etu INTEGER NOT NULL ,Note REAL NOT NULL ,FOREIGN KEY (id_etu) REFERENCES Etudiant (id_etu) ON UPDATE CASCADE ON DELETE SET NULL,FOREIGN KEY (id_mod) REFERENCES Module (id_mod) ON UPDATE CASCADE ON DELETE SET NULL);");
        db.execSQL("CREATE TABLE Plannig (id_mod INTEGER NOT NULL,id_fil INTEGER NOT NULL ,Niveau TEXT NOT NULL , semestre INTEGER NOT NULL, FOREIGN KEY (id_fil) REFERENCES Filiere (id_fil) ON UPDATE CASCADE ON DELETE SET NULL,FOREIGN KEY (id_mod) REFERENCES Module (id_mod) ON UPDATE CASCADE ON DELETE SET NULL);");
        db.execSQL("CREATE TABLE Inscription(id_etu INTEGER NOT NULL,id_fil INTEGER NOT NULL ,Niveau TEXT NOT NULL, semestre INTEGER NOT NULL, FOREIGN KEY (id_fil) REFERENCES Filiere (id_fil) ON UPDATE CASCADE ON DELETE SET NULL,FOREIGN KEY (id_etu) REFERENCES Etudiant (id_etu) ON UPDATE CASCADE ON DELETE SET NULL);");


        db.execSQL("INSERT INTO Filiere(intitule) VALUES('MIPC'),('GEGM'),('MIP'),('BCG');");
    }

    public long ajouterFiliere(Filiere filiere) throws SQLException {
        SQLiteDatabase  db =  this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("intitule", filiere.getIntitule());
        return db.insertOrThrow("Filiere",null,contentValues);
    }


    public void supprimerFiliere(Filiere filiere) throws SQLException {
        SQLiteDatabase db =  this.getWritableDatabase();
        int id = getIdFiliere(filiere.getIntitule());
        if (id != -1 ) {
            db.delete("Filiere",id_fil +'='+ id,null);
            Log.i("delete plzzzz", String.valueOf(id));
            db.close();
            Log.i("delete plzzzz", String.valueOf(getIdFiliere(filiere.getIntitule())));
        }
    }

    public int getIdFiliere(String nom) throws  SQLException{
        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor getNoteId = myDB.rawQuery("select id_fil from Filiere where intitule = '"+nom+"'",null);
        if (getNoteId != null && getNoteId.moveToFirst()) {
            Log.i("id", String.valueOf(getNoteId.getInt(0)));
            return getNoteId.getInt(0);
        } else {
            return -1;  // because you have to return something
        }
    }

    public String getEtudiant(String cne) throws  SQLException{
        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor getNoteId = myDB.rawQuery("select Nom,Prenom from Etudiant where CNE = '"+cne+"'",null);
        String nom;
        if (getNoteId != null && getNoteId.moveToFirst()) {
            Log.i("id", String.valueOf(getNoteId.getInt(0)));
            nom = getNoteId.getString(0)+" "+getNoteId.getString(1);
            return nom;
        } else {
            return null;  // because you have to return something
        }
    }

    public ArrayList<Filiere> getAllFilieres() throws SQLException {
        SQLiteDatabase  db =  this.getReadableDatabase();
        ArrayList<Filiere> filieres = new ArrayList<Filiere>();
        Cursor cursor = db.rawQuery("SELECT * FROM Filiere ORDER BY id_fil DESC",null);
        while(cursor.moveToNext()){

            filieres.add(new Filiere(cursor.getInt(0),cursor.getString(1)));
            Log.i("NumberGenerated", String.valueOf(cursor.getString(1)));
        }
        cursor.close();
        return filieres;
    }

    public long ajouterModule(Module module) throws SQLException {
        SQLiteDatabase  db =  this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Nom", module.getNom());
        return db.insertOrThrow("Module",null,contentValues);
    }

    public long ajouterPlanning(int id_mod,int id_fil, String nv,int semsetre) throws SQLException {
        SQLiteDatabase  db =  this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_mod", id_mod);
        contentValues.put("id_fil", id_fil);
        contentValues.put("Niveau", nv);
        contentValues.put("semestre", semsetre);
        return db.insertOrThrow("Plannig",null,contentValues);
    }


    public ArrayList<Module> getAllModules(String filiere,String nv,int semestre) throws SQLException {
        SQLiteDatabase  db =  this.getReadableDatabase();
        ArrayList<Module> modules = new ArrayList<Module>();
        int id_fil = getIdFiliere(filiere);
        Log.i("ID dyl filiere", String.valueOf(id_fil));
        Cursor cursor = db.rawQuery("SELECT Module.id_mod,Nom FROM Module,Plannig WHERE Plannig.id_mod = module.id_mod AND Plannig.Niveau = '"+nv+"' and Plannig.id_fil = '"+id_fil+"' AND Plannig.semestre = '"+semestre+"' ORDER BY Nom ASC ",null);
        while(cursor.moveToNext()){
            modules.add(new Module(cursor.getInt(0),cursor.getString(1)));
            Log.i("liste", String.valueOf(cursor.getString(1)));
        }
        cursor.close();
        return modules;
    }
    //ORDER BY Nom ASC
    //Module,Plannig WHERE Plannig.id_mod = module.id_mod AND Plannig.Niveau = '"+nv+"' and Plannig.id_fil = '"+id_fil+"'
//INNER JOIN Plannig ON Plannig.id_mod = module.id_mod WHERE Plannig.Niveau = '"+nv+"' and Plannig.id_fil = '"+id_fil+"'
    public int getIdModule(String nom) throws  SQLException{
        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor getNoteId = myDB.rawQuery("select id_mod from Module where intitule = '"+nom+"'",null);
        if (getNoteId != null && getNoteId.moveToFirst()) {
            Log.i("id modeule", String.valueOf(getNoteId.getInt(0)));
            return getNoteId.getInt(0);
        } else {
            return -1;  // because you have to return something
        }
    }

    public int getIdEtudiant(String cne) throws  SQLException{
        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor getNoteId = myDB.rawQuery("select id_etu from Etudiant where CNE = '"+cne+"'",null);
        if (getNoteId != null && getNoteId.moveToFirst()) {
            Log.i("id modeule", String.valueOf(getNoteId.getInt(0)));
            return getNoteId.getInt(0);
        } else {
            return -1;  // because you have to return something
        }
    }

    public void supprimerModule(Module module) throws SQLException {
        SQLiteDatabase db =  this.getWritableDatabase();
        int id = getIdModule(module.getNom());
        if (id != -1 ) {
            db.delete("Module",id_mod +'='+ id,null);
            Log.i("delete plzzzz", String.valueOf(id));
            db.close();
            Log.i("delete plzzzz", String.valueOf(getIdFiliere(module.getNom())));
        }
    }

    public long ajouterEtudiant(Etudiant etudiant) throws SQLException {
        //String CNE, String Nom,String Prenom, String Niveau, int id_fil
        SQLiteDatabase  db =  this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("CNE", etudiant.getCNE());
        contentValues.put("Nom", etudiant.getNom());
        contentValues.put("Prenom", etudiant.getPrenom());
        return db.insertOrThrow("Etudiant",null,contentValues);
    }

    public long ajouterInscription(Inscription inscription) throws SQLException {
        //int id_etu, int id_fil,String Niveau,int semestre
        SQLiteDatabase  db =  this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_etu", inscription.getId_etu());
        contentValues.put("id_fil", inscription.getId_fil());
        contentValues.put("Niveau", inscription.getNiveau());
        contentValues.put("semestre", inscription.getSemestre());
        return db.insertOrThrow("Inscription",null,contentValues);
    }

    public void supprimerEtudiant(Etudiant etudiant) throws SQLException {
        Log.i("delete plzzzz", String.valueOf(etudiant.getId_etu()));
        SQLiteDatabase db =  this.getWritableDatabase();
        db.delete("Etudiant", id_etu +'='+ etudiant.getId_etu(),null);
        db.close();
        Log.i("delete plzzzz", String.valueOf(etudiant.getId_etu()));
    }

    public ArrayList<Etudiant> getAllEtudiants(String filiere,String nv,int semestre) throws SQLException {
        SQLiteDatabase  db =  this.getReadableDatabase();
        ArrayList<Etudiant> etudiants = new ArrayList<Etudiant>();
        int id_fil = getIdFiliere(filiere);
        Log.i("ID dyl filiere", String.valueOf(id_fil));
        Cursor cursor = db.rawQuery("SELECT * FROM Etudiant,Inscription WHERE Etudiant.id_etu = Inscription.id_etu AND Inscription.Niveau = '"+nv+"' and Inscription.id_fil = '"+id_fil+"' AND Inscription.semestre = '"+semestre+"' ORDER BY Nom ASC ",null);
        while(cursor.moveToNext()){
            etudiants.add(new Etudiant(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3)));
            Log.i("liste", String.valueOf(cursor.getString(1)));
        }
        cursor.close();
        return etudiants;
    }

    public long ajouterEvaluation(int id_mod,String cne, double note) throws SQLException {
        SQLiteDatabase  db =  this.getWritableDatabase();
        int id_etu = getIdEtudiant(cne);
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_mod", id_mod);
        contentValues.put("id_etu", id_etu);
        contentValues.put("Note", note);
        return db.insertOrThrow("Evaluation",null,contentValues);
    }

    public ArrayList<Evaluation> getAllNotes(String cne,String filiere,String nv,int semestre) throws SQLException {
        SQLiteDatabase  db =  this.getReadableDatabase();
        ArrayList<Evaluation> evaluations = new ArrayList<Evaluation>();
        int id_fil = getIdFiliere(filiere);
        int id_etu = getIdEtudiant(cne);
        Log.i("ID dyl filiere", String.valueOf(id_fil));
        Cursor cursor = db.rawQuery("SELECT Module.id_mod,Module.Nom,Evaluation.Note FROM Module,Plannig,Evaluation WHERE Plannig.id_mod = module.id_mod AND Evaluation.id_mod = module.id_mod AND Plannig.Niveau = '"+nv+"' and Plannig.id_fil = '"+id_fil+"' AND Plannig.semestre = '"+semestre+"' AND Evaluation.id_etu = '"+id_etu+"' ORDER BY Nom ASC ",null);
        while(cursor.moveToNext()){
            evaluations.add(new Evaluation(cne,cursor.getInt(0),cursor.getString(1),cursor.getDouble(2)));
            Log.i("liste", String.valueOf(cursor.getString(1)));
        }
        cursor.close();
        return evaluations;
    }

    public double getMoyenne(String cne,String filiere,String nv,int semestre) throws SQLException {
        SQLiteDatabase  db =  this.getReadableDatabase();
        double moyenne = 0;
        int id_fil = getIdFiliere(filiere);
        int id_etu = getIdEtudiant(cne);
        Log.i("ID dyl filiere", String.valueOf(id_fil));
        Cursor cursor = db.rawQuery("SELECT AVG(Evaluation.Note) FROM Module,Plannig,Evaluation WHERE Plannig.id_mod = module.id_mod AND Evaluation.id_mod = module.id_mod AND Plannig.Niveau = '"+nv+"' and Plannig.id_fil = '"+id_fil+"' AND Plannig.semestre = '"+semestre+"' AND Evaluation.id_etu = '"+id_etu+"' ORDER BY Nom ASC ",null);
        while(cursor.moveToNext()){
            moyenne= cursor.getDouble(0);
            Log.i("liste", String.valueOf(moyenne));
        }
        cursor.close();
        return moyenne;
    }

    public void modifierNote(int mod_id,String cne, double note) throws SQLException {
        SQLiteDatabase  db =  this.getWritableDatabase();
        Log.i("cne", String.valueOf(cne));
        int etu_id = getIdEtudiant(cne);
        Log.i("cne", String.valueOf(etu_id));
        ContentValues contentValues = new ContentValues();
        contentValues.put("Note", note);
        db.execSQL("UPDATE Evaluation SET Note = '"+note+"'WHERE id_etu = '"+etu_id+"' AND id_mod = '"+mod_id+"'");
    }

    public double getMoyenneFiliere(String filiere,String nv,int semestre) throws SQLException {
        SQLiteDatabase  db =  this.getReadableDatabase();
        ArrayList<Etudiant> etudiants = new ArrayList<Etudiant>();
        double moy=0;
        double moyennes = 0;
        etudiants = getAllEtudiants(filiere,nv,semestre);
        int i=0;
        while(etudiants.size() > i){
           moyennes += getMoyenne(etudiants.get(i).getCNE(),filiere,nv,semestre);
           i++;
        }
        moy=moyennes/i;
        return moy;
    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS article");
        db.execSQL("DROP TABLE IF EXISTS operation");
        onCreate(db);
    }
}
