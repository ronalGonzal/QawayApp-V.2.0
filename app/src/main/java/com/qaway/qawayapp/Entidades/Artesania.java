package com.qaway.qawayapp.Entidades;

import java.io.Serializable;

public class Artesania implements Serializable {

    private String nomArtesano;
    private  String desArtesano;
    private int imgArtesano;

    public Artesania() {
    }

    public Artesania(String nomArtesano, String desArtesano, int imgArtesano) {
        this.nomArtesano = nomArtesano;
        this.desArtesano = desArtesano;
        this.imgArtesano = imgArtesano;

    }

    public String getNomArtesano() {
        return nomArtesano;
    }

    public void setNomArtesano(String nomArtesano) {
        this.nomArtesano = nomArtesano;
    }

    public String getDesArtesano() {
        return desArtesano;
    }

    public void setDesArtesano(String desArtesano) {
        this.desArtesano = desArtesano;
    }

    public int getImgArtesano() {
        return imgArtesano;
    }

    public void setImgArtesano(int imgArtesano) {
        this.imgArtesano = imgArtesano;
    }
}
