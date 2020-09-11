package com.qaway.qawayapp.Interfaces;

import com.qaway.qawayapp.Entidades.Artesania;
import com.qaway.qawayapp.Entidades.Literatura;
import com.qaway.qawayapp.Entidades.Patrimonio;
import com.qaway.qawayapp.Entidades.Provincia;

public interface IComunicaFragments {

    public  void  enviarProvincia(Provincia provincia);
    public void enviarPatrimonio(Patrimonio patrimonio);

    public void enviarLiteratura(Literatura literatura);

    public void enviarArtesania(Artesania artesania);
}
