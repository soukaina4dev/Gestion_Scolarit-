package com.android.projetscolarite.Models;

import java.io.Serializable;

public class Inscription implements Serializable {

    private int id_etu;
    private int id_fil;
    private int semestre;
    private String Niveau;


    public Inscription(int id_etu, int id_fil,String Niveau,int semestre) {
        this.id_fil = id_fil;
        this.Niveau =  Niveau;
        this.id_etu = id_etu;
        this.semestre = semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public int getSemestre() {
        return semestre;
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


    public int getId_etu() {
        return id_etu;
    }
    public void setId_etu(int id_etu) {
        this.id_etu = id_etu;
    }

}