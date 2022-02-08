package com.android.projetscolarite.Models;
import java.io.Serializable;

public class Etudiant implements Serializable {

    private int id_etu;
    private String CNE;
    private String Nom;
    private String Prenom;


    public Etudiant(String CNE, String Nom,String Prenom) {
        this.CNE = CNE;
        this.Nom =  Nom;
        this.Prenom = Prenom;
    }

    public Etudiant(int id_etu,String CNE, String Nom,String Prenom) {
        this.id_etu = id_etu;
        this.CNE = CNE;
        this.Nom =  Nom;
        this.Prenom = Prenom;
    }


    public int getId_etu() {
        return id_etu;
    }

    public void setId_etu(int id_etu) {
        this.id_etu = id_etu;
    }

    public void setCNE(String CNE) {
        this.CNE= CNE;
    }
    public String getCNE() { return CNE; }

    public String getNom() {
        return Nom;
    }
    public void setNom(String Nom) {
        this.Nom =Nom;
    }

    public String getPrenom() {
        return Prenom;
    }
    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }


}
