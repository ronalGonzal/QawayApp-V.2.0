package com.qaway.qawayapp.Entidades;

import java.io.Serializable;

public class Turisticos implements Serializable {

    private String nomTuristicos;
    private  String desTuristicos;
    private int imgTuristicos;

    public Turisticos() {
    }

    public Turisticos(String nomTuristicos, String desTuristicos, int imgTuristicos) {
        this.nomTuristicos = nomTuristicos;
        this.desTuristicos = desTuristicos;
        this.imgTuristicos = imgTuristicos;
    }

    public String getNomTuristicos() {
        return nomTuristicos;
    }

    public void setNomTuristicos(String nomTuristicos) {
        this.nomTuristicos = nomTuristicos;
    }

    public String getDesTuristicos() {
        return desTuristicos;
    }

    public void setDesTuristicos(String desTuristicos) {
        this.desTuristicos = desTuristicos;
    }

    public int getImgTuristicos() {
        return imgTuristicos;
    }

    public void setImgTuristicos(int imgTuristicos) {
        this.imgTuristicos = imgTuristicos;
    }
}
