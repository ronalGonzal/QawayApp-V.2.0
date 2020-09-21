package com.qaway.qawayapp.Entidades;

public class Zonas {
    private int idZona;
    private String tituloZona;
    private String latitud;
    private  String longitud;
    private  String tipoZona;

    public Zonas() {
    }

    public Zonas(int idZona, String tituloZona, String latitud, String longitud, String tipoZona) {
        this.idZona = idZona;
        this.tituloZona = tituloZona;
        this.latitud = latitud;
        this.longitud = longitud;
        this.tipoZona = tipoZona;
    }

    public int getIdZona() {
        return idZona;
    }

    public void setIdZona(int idZona) {
        this.idZona = idZona;
    }

    public String getTituloZona() {
        return tituloZona;
    }

    public void setTituloZona(String tituloZona) {
        this.tituloZona = tituloZona;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getTipoZona() {
        return tipoZona;
    }

    public void setTipoZona(String tipoZona) {
        this.tipoZona = tipoZona;
    }
}
