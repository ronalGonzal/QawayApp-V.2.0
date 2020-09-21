package com.qaway.qawayapp.Interfaces;

import com.qaway.qawayapp.Entidades.Artesania;
import com.qaway.qawayapp.Entidades.Danza;
import com.qaway.qawayapp.Entidades.Literatura;
import com.qaway.qawayapp.Entidades.Patrimonio;
import com.qaway.qawayapp.Entidades.Provincia;
import com.qaway.qawayapp.Entidades.Turisticos;

public interface IComunicaFragments {

    public  void  enviarProvincia(Provincia provincia);
    public void enviarPatrimonio(Patrimonio patrimonio);

    public void enviarLiteratura(Literatura literatura);

    public void enviarArtesania(Artesania artesania);

    public  void enviarDanza(Danza danza);

    public void enviarTuristicos(Turisticos turisticos);

    public void enviarProvinciaDanzas(Provincia provincia);

    public void enviarProvinciaPatrimonios(Provincia provincia);
    public void enviarProvinciaLiteraturas(Provincia provincia);
    public void enviarProvinciaArtesanias(Provincia provincia);
    public void enviarProvinciaTuristicos(Provincia provincia);
}
