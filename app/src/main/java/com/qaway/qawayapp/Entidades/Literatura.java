package com.qaway.qawayapp.Entidades;

import java.io.Serializable;

public class Literatura implements Serializable {
    private String nomLiteratura;
    private  String desLiteratura;
    private int imgLiteratura;

    public Literatura() {
    }

    public Literatura(String nomLiteratura, String desLiteratura, int imgLiteratura) {
        this.nomLiteratura = nomLiteratura;
        this.desLiteratura = desLiteratura;
        this.imgLiteratura = imgLiteratura;

    }

    public String getNomLiteratura() {
        return nomLiteratura;
    }

    public void setNomLiteratura(String nomLiteratura) {
        this.nomLiteratura = nomLiteratura;
    }

    public String getDesLiteratura() {
        return desLiteratura;
    }

    public void setDesLiteratura(String desLiteratura) {
        this.desLiteratura = desLiteratura;
    }

    public int getImgLiteratura() {
        return imgLiteratura;
    }

    public void setImgLiteratura(int imgLiteratura) {
        this.imgLiteratura = imgLiteratura;
    }
}
