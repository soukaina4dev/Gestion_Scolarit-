package com.android.projetscolarite.Models;
import java.io.Serializable;

public class Filiere implements Serializable {

    private int id_fil;
    private String intitule;


    public Filiere(String intitule) {
        this.intitule = intitule;
        this.id_fil = id_fil;
    }

    public Filiere(int id_fil,String intitule) {
        this.intitule = intitule;
        this.id_fil = id_fil;
    }

    public int getId_fil() {
        return id_fil;
    }
    public void setId_fil(int id_fil) {
        this.id_fil = id_fil;
    }


    public void setIntitule(String intitule) {
        this.intitule= intitule;
    }
    public String getIntitule() { return intitule; }


}
