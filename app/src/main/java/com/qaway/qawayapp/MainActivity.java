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
import com.qaway.qawayapp.Entidades.Patrimonio;
import com.qaway.qawayapp.Entidades.Provincia;
import com.qaway.qawayapp.Fragments.AcercadeFragment;
import com.qaway.qawayapp.Fragments.DdccuscoFragment;
import com.qaway.qawayapp.Fragments.DetallePatrimonioFragment;
import com.qaway.qawayapp.Fragments.HomeFragment;
import com.qaway.qawayapp.Fragments.MenuQawayFragment;
import com.qaway.qawayapp.Fragments.MuseoFragment;
import com.qaway.qawayapp.Interfaces.IComunicaFragments;

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
    MenuQawayFragment menuQawayFragment;



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
}