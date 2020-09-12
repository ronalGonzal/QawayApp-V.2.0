package com.qaway.qawayapp.Entidades;

import java.io.Serializable;

public class Danza implements Serializable {

    private String nomDanza;
    private  String desDanza;
    private int imgDanza;

    public Danza() {
    }

    public Danza(String nomDanza, String desDanza, int imgDanza) {
        this.nomDanza = nomDanza;
        this.desDanza = desDanza;
        this.imgDanza = imgDanza;

    }

    public String getNomDanza() {
        return nomDanza;
    }

    public void setNomDanza(String nomDanza) {
        this.nomDanza = nomDanza;
    }

    public String getDesDanza() {
        return desDanza;
    }

    public void setDesDanza(String desDanza) {
        this.desDanza = desDanza;
    }

    public int getImgDanza() {
        return imgDanza;
    }

    public void setImgDanza(int imgDanza) {
        this.imgDanza = imgDanza;
    }
}
