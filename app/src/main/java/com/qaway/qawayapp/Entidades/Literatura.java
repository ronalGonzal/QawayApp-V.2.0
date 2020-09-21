package com.qaway.qawayapp.Entidades;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.Serializable;

public class Literatura implements Serializable {
    private String nomLiteratura;
    private  String desLiteratura;
    private int imgLiteratura;
    private String datoimagen;
    private Bitmap imagen;

    public Literatura() {
    }

    public Literatura(String nomLiteratura, String desLiteratura, int imgLiteratura, String datoimagen, Bitmap imagen) {
        this.nomLiteratura = nomLiteratura;
        this.desLiteratura = desLiteratura;
        this.imgLiteratura = imgLiteratura;
        this.datoimagen = datoimagen;
        this.imagen = imagen;
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
