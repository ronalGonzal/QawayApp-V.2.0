package com.qaway.qawayapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.qaway.qawayapp.Entidades.Artesania;
import com.qaway.qawayapp.Entidades.Patrimonio;
import com.qaway.qawayapp.Entidades.Literatura;
import com.qaway.qawayapp.Entidades.Provincia;
import com.qaway.qawayapp.Entidades.Danza;
import com.qaway.qawayapp.Entidades.Turisticos;
import com.qaway.qawayapp.Fragments.AcercadeFragment;
import com.qaway.qawayapp.Fragments.ArtesaniaFragment;
import com.qaway.qawayapp.Fragments.DanzaFragment;
import com.qaway.qawayapp.Fragments.DdccuscoFragment;
import com.qaway.qawayapp.Fragments.DetallePatrimonioFragment;
import com.qaway.qawayapp.Fragments.DetalleLiteraturaFragment;
import com.qaway.qawayapp.Fragments.DetalleArtesaniaFragment;
import com.qaway.qawayapp.Fragments.DetalleTuristicosFragment;
import com.qaway.qawayapp.Fragments.DetalleDanzaFragment;
import com.qaway.qawayapp.Fragments.HomeFragment;
import com.qaway.qawayapp.Fragments.LiteraturaFragment;
import com.qaway.qawayapp.Fragments.MenuQawayFragment;
import com.qaway.qawayapp.Fragments.MuseoFragment;
import com.qaway.qawayapp.Fragments.PatrimoniosFragment;
import com.qaway.qawayapp.Fragments.TuristicosFragment;
import com.qaway.qawayapp.Fragments.ZonasCuscoFragment;
import com.qaway.qawayapp.Interfaces.IComunicaFragments;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, IComunicaFragments {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;

    //variables para cargar fragment
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    //variables para envio
    DetallePatrimonioFragment detallePatrimonioFragment;
    DetalleLiteraturaFragment detalleLiteraturaFragment;
    DetalleArtesaniaFragment detalleArtesaniaFragment;
    DetalleDanzaFragment detalleDanzaFragment;
    DetalleTuristicosFragment detalleTuristicosFragment;
    MenuQawayFragment menuQawayFragment;

    DanzaFragment danzaFragment;
    PatrimoniosFragment patrimoniosFragment;
    LiteraturaFragment literaturaFragment;
    ArtesaniaFragment artesaniaFragment;
    TuristicosFragment turisticosFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout=findViewById(R.id.drawer);
        navigationView=findViewById(R.id.navigationView);
        // establecer evento onclick al navigationView
        navigationView.setNavigationItemSelectedListener(this);

        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        //cargar fragmente home
        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.contenedor, new HomeFragment());
        fragmentTransaction.commit();


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //activar que se oculte cuando se selecciona una opcion del menu
        drawerLayout.closeDrawer(GravityCompat.START);

        if (item.getItemId()==R.id.home){
            //cargar fragmente home
            fragmentManager=getSupportFragmentManager();
            fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.contenedor, new HomeFragment());
            fragmentTransaction.commit();
        }
        if (item.getItemId()==R.id.acercade){
            //cargar fragmente acerca de app
            fragmentManager=getSupportFragmentManager();
            fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.contenedor, new AcercadeFragment());
            fragmentTransaction.commit();
        }
        if (item.getItemId()==R.id.ddccusco){
            //cargar fragmente acerca de app
            fragmentManager=getSupportFragmentManager();
            fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.contenedor, new DdccuscoFragment());
            fragmentTransaction.commit();
        }
        if (item.getItemId()==R.id.museo){
            //cargar fragmente acerca de app
            fragmentManager=getSupportFragmentManager();
            fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.contenedor, new MuseoFragment());
            fragmentTransaction.commit();
        }
        if (item.getItemId()==R.id.zonas){
            //cargar fragmente acerca de app
            fragmentManager=getSupportFragmentManager();
            fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.contenedor, new ZonasCuscoFragment());
            fragmentTransaction.commit();
        }


        if (item.getItemId()==R.id.salir){
            System.exit(0);
        }

        return false;
    }

    @Override
    public void enviarProvincia(Provincia provincia) {
        //aqui se hace la logica para realizar el envio
        menuQawayFragment=new MenuQawayFragment();
        //objeto bundle para transportar la informacion
        Bundle bundleEnvio= new Bundle();
        //enviar objeto que esta llegando con serializable
        bundleEnvio.putSerializable("provincia",provincia);
        menuQawayFragment.setArguments(bundleEnvio);

        // abrir fragment detalle patrimonio
        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contenedor, menuQawayFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void enviarPatrimonio(Patrimonio patrimonio) {
        //aqui se hace la logica para realizar el envio
        detallePatrimonioFragment=new DetallePatrimonioFragment();
        //objeto bundle para transportar la informacion
        Bundle bundleEnvio= new Bundle();
        //enviar objeto que esta llegando con serializable
        bundleEnvio.putSerializable("patrimonio",patrimonio);
        detallePatrimonioFragment.setArguments(bundleEnvio);

        // abrir fragment detalle patrimonio
        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contenedor, detallePatrimonioFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();


    }


    public void enviarLiteratura(Literatura literatura) {
        //aqui se hace la logica para realizar el envio
        detalleLiteraturaFragment=new DetalleLiteraturaFragment();
        //objeto bundle para transportar la informacion
        Bundle bundleEnvio= new Bundle();
        //enviar objeto que esta llegando con serializable
        bundleEnvio.putSerializable("literatura",literatura);
        detalleLiteraturaFragment.setArguments(bundleEnvio);

        // abrir fragment detalle literatura
        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contenedor, detalleLiteraturaFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();


    }


    public void enviarArtesania(Artesania artesania) {

        detalleArtesaniaFragment=new DetalleArtesaniaFragment();
        Bundle bundleEnvio= new Bundle();
        bundleEnvio.putSerializable("artesania", artesania);
        detalleArtesaniaFragment.setArguments(bundleEnvio);


        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contenedor, detalleArtesaniaFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();


    }

    public void enviarDanza(Danza danza) {

        detalleDanzaFragment=new DetalleDanzaFragment();
        Bundle bundleEnvio= new Bundle();
        bundleEnvio.putSerializable("danza", danza);
        detalleDanzaFragment.setArguments(bundleEnvio);


        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contenedor, detalleDanzaFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();


    }

    public void enviarTuristicos(Turisticos turisticos) {

        detalleTuristicosFragment=new DetalleTuristicosFragment();
        Bundle bundleEnvio= new Bundle();
        bundleEnvio.putSerializable("turisticos", turisticos);
        detalleTuristicosFragment.setArguments(bundleEnvio);


        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contenedor, detalleTuristicosFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();


    }

    @Override
    public void enviarProvinciaDanzas(Provincia provincia) {
        //aqui se hace la logica para realizar el envio
        danzaFragment=new DanzaFragment();
        //objeto bundle para transportar la informacion
        Bundle bundleEnvio= new Bundle();
        //enviar objeto que esta llegando con serializable
        bundleEnvio.putSerializable("provincia",provincia);
        danzaFragment.setArguments(bundleEnvio);

        // abrir fragment Danza
        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contenedor, danzaFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    @Override
    public void enviarProvinciaPatrimonios(Provincia provincia) {
        //aqui se hace la logica para realizar el envio
        patrimoniosFragment=new PatrimoniosFragment();
        //objeto bundle para transportar la informacion
        Bundle bundleEnvio= new Bundle();
        //enviar objeto que esta llegando con serializable
        bundleEnvio.putSerializable("provincia",provincia);
        patrimoniosFragment.setArguments(bundleEnvio);

        // abrir fragment Danza
        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contenedor, patrimoniosFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void enviarProvinciaLiteraturas(Provincia provincia) {
        //aqui se hace la logica para realizar el envio
        literaturaFragment=new LiteraturaFragment();
        //objeto bundle para transportar la informacion
        Bundle bundleEnvio= new Bundle();
        //enviar objeto que esta llegando con serializable
        bundleEnvio.putSerializable("provincia",provincia);
        literaturaFragment.setArguments(bundleEnvio);

        // abrir fragment Danza
        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contenedor, literaturaFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    @Override
    public void enviarProvinciaArtesanias(Provincia provincia) {
        //aqui se hace la logica para realizar el envio
        artesaniaFragment=new ArtesaniaFragment();
        //objeto bundle para transportar la informacion
        Bundle bundleEnvio= new Bundle();
        //enviar objeto que esta llegando con serializable
        bundleEnvio.putSerializable("provincia",provincia);
        artesaniaFragment.setArguments(bundleEnvio);

        // abrir fragment Danza
        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contenedor, artesaniaFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void enviarProvinciaTuristicos(Provincia provincia) {
        //aqui se hace la logica para realizar el envio
        turisticosFragment=new TuristicosFragment();
        //objeto bundle para transportar la informacion
        Bundle bundleEnvio= new Bundle();
        //enviar objeto que esta llegando con serializable
        bundleEnvio.putSerializable("provincia",provincia);
        turisticosFragment.setArguments(bundleEnvio);

        // abrir fragment Danza
        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contenedor, turisticosFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


}