package com.android.projetscolarite.Models;
import java.io.Serializable;

public class Module implements Serializable {

    private int id_mod;
    private String Nom;




    public Module(String Nom) {
        this.Nom = Nom;
    }

    public Module(int id_mod,String Nom) {
        this.Nom = Nom;

        this.id_mod = id_mod;
    }

    public int getId_mod() {
        return id_mod;
    }
    public void setId_fil(int id_fil) {
        this.id_mod = id_fil;
    }


    public void setNom(String Nom) {
        this.Nom = Nom;
    }
    public String getNom() { return Nom; }


}
