package com.qaway.qawayapp.Entidades;

import java.io.Serializable;

public class Patrimonio implements Serializable {
    private String nomPatrimonio;
    private  String desPatrimonio;
    private int imgPatrimonio;

    public Patrimonio() {
    }

    public Patrimonio(String nomPatrimonio, String desPatrimonio, int imgPatrimonio) {
        this.nomPatrimonio = nomPatrimonio;
        this.desPatrimonio = desPatrimonio;
        this.imgPatrimonio = imgPatrimonio;
    }

    public String getNomPatrimonio() {
        return nomPatrimonio;
    }

    public void setNomPatrimonio(String nomPatrimonio) {
        this.nomPatrimonio = nomPatrimonio;
    }

    public String getDesPatrimonio() {
        return desPatrimonio;
    }

    public void setDesPatrimonio(String desPatrimonio) {
        this.desPatrimonio = desPatrimonio;
    }

    public int getImgPatrimonio() {
        return imgPatrimonio;
    }

    public void setImgPatrimonio(int imgPatrimonio) {
        this.imgPatrimonio = imgPatrimonio;
    }
}
