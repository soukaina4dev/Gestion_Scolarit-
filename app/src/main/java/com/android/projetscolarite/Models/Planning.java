package com.android.projetscolarite.Models;
import java.io.Serializable;

public class Planning implements Serializable {

    private int id_mod;
    private int id_fil;
    private String Niveau;


    public Planning(int id_fil, int id_mod,String Niveau) {
        this.id_fil = id_fil;
        this.Niveau =  Niveau;
        this.id_mod = id_mod;
    }

    public int getId_fil() {
        return id_fil;
    }
    public void setId_fil(int id_fil) {
        this.id_fil = id_fil;
    }

    public String getNiveau() {
        return Niveau;
    }
    public void setNiveau(String Niveau) {
        this.Niveau= Niveau;
    }


    public int getId_mod() {
        return id_mod;
    }
    public void setId_mod(int id_mod) {
        this.id_mod = id_mod;
    }

}
