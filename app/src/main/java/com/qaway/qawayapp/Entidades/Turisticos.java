package com.qaway.qawayapp.Entidades;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.Serializable;

public class Turisticos implements Serializable {

    private String nomTuristicos;
    private  String desTuristicos;
    private int imgTuristicos;
    private String datoimagen;
    private Bitmap imagen;

    public Turisticos() {
    }

    public Turisticos(String nomTuristicos, String desTuristicos, int imgTuristicos, String datoimagen, Bitmap imagen) {
        this.nomTuristicos = nomTuristicos;
        this.desTuristicos = desTuristicos;
        this.imgTuristicos = imgTuristicos;
        this.datoimagen = datoimagen;
        this.imagen = imagen;
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
