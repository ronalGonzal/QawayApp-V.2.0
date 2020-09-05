package com.qaway.qawayapp.Entidades;

import java.io.Serializable;

public class Provincia implements Serializable {
    private int idProvincia;
    private String nomProvincia;
    private int imgProvincia;

    public Provincia() {
    }

    public Provincia(int idProvincia, String nomProvincia, int imgProvincia) {
        this.idProvincia = idProvincia;
        this.nomProvincia = nomProvincia;
        this.imgProvincia = imgProvincia;
    }

    public int getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(int idProvincia) {
        this.idProvincia = idProvincia;
    }

    public String getNomProvincia() {
        return nomProvincia;
    }

    public void setNomProvincia(String nomProvincia) {
        this.nomProvincia = nomProvincia;
    }

    public int getImgProvincia() {
        return imgProvincia;
    }

    public void setImgProvincia(int imgProvincia) {
        this.imgProvincia = imgProvincia;
    }
}
