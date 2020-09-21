package com.qaway.qawayapp.Entidades;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.Serializable;

public class Patrimonio implements Serializable {
    private String nomPatrimonio;
    private  String desPatrimonio;
    private int imgPatrimonio;
    private String datoimagen;
    private Bitmap imagen;

    public Patrimonio() {
    }

    public Patrimonio(String nomPatrimonio, String desPatrimonio, int imgPatrimonio, String datoimagen, Bitmap imagen) {
        this.nomPatrimonio = nomPatrimonio;
        this.desPatrimonio = desPatrimonio;
        this.imgPatrimonio = imgPatrimonio;
        this.datoimagen = datoimagen;
        this.imagen = imagen;
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
