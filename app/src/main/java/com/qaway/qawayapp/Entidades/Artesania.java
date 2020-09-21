package com.qaway.qawayapp.Entidades;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.Serializable;

public class Artesania implements Serializable {

    private String nomArtesano;
    private  String desArtesano;
    private int imgArtesano;
    private String datoimagen;
    private Bitmap imagen;

    public Artesania() {
    }

    public Artesania(String nomArtesano, String desArtesano, int imgArtesano, String datoimagen, Bitmap imagen) {
        this.nomArtesano = nomArtesano;
        this.desArtesano = desArtesano;
        this.imgArtesano = imgArtesano;
        this.datoimagen = datoimagen;
        this.imagen = imagen;
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

    public String getDatoimagen() {
        return datoimagen;
    }

    public void setDatoimagen(String datoimagen) {
        this.datoimagen = datoimagen;

        try {
            byte[] byteCode= Base64.decode(datoimagen,Base64.DEFAULT);
            //this.imagen= BitmapFactory.decodeByteArray(byteCode,0,byteCode.length);

            int alto=100;//alto en pixeles
            int ancho=150;//ancho en pixeles

            Bitmap foto= BitmapFactory.decodeByteArray(byteCode,0,byteCode.length);
            this.imagen=Bitmap.createScaledBitmap(foto,alto,ancho,true);


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }
}
