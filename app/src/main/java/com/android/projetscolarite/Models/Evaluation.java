package com.android.projetscolarite.Models;
import java.io.Serializable;

public class Evaluation implements Serializable {

    private int id_mod;
    private String CNE;
    private String NomMod;
    private double Note;


    public Evaluation(String CNE, int id_mod, String NomMod, double Note) {
        this.CNE = CNE;
        this.Note =  Note;
        this.NomMod = NomMod;
        this.id_mod = id_mod;
    }

    public void setNomMod(String nomMod) {
        NomMod = nomMod;
    }

    public String getNomMod() {
        return NomMod;
    }

    public void setCNE(String CNE) {
        this.CNE= CNE;
    }
    public String getCNE() { return CNE; }

    public double getNote() {
        return Note;
    }
    public void setNote(double Note) {
        this.Note =Note;
    }

    public int getId_mod() {
        return id_mod;
    }
    public void setId_mod(int id_mod) {
        this.id_mod = id_mod;
    }

}
