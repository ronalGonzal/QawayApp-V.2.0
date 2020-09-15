package com.qaway.qawayapp.Entidades;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.Serializable;

public class Danza implements Serializable {

    private String nomDanza;
    private  String desDanza;
    private int imgDanza;
    private String datoimagen;
    private Bitmap imagen;

    public Danza() {
    }

    public Danza(String nomDanza, String desDanza, int imgDanza, String datoimagen, Bitmap imagen) {
        this.nomDanza = nomDanza;
        this.desDanza = desDanza;
        this.imgDanza = imgDanza;
        this.datoimagen = datoimagen;
        this.imagen = imagen;
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
