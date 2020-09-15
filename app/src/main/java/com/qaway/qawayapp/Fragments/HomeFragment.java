package com.qaway.qawayapp.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.qaway.qawayapp.Entidades.Provincia;
import com.qaway.qawayapp.Interfaces.IComunicaFragments;
import com.qaway.qawayapp.R;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    //variable de id provincia
    TextView txtIdProvincia;

    //referencias para comunicar fragment
    Activity actividad;
    IComunicaFragments interfaceComunicaFragmentes;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    Provincia provincia;
    //variables de los botones

    Button btnConvencion, btnCalca, btnUrubamba, btnPaucartambo, btnAnta, btnCusco, btnQuispicanchi, btnParuro, btnAcomayo, btnCanchis, btnChumbivilcas, btnCanas, btnEspinar;

    TextView txtProvinciaS;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View vista = inflater.inflate(R.layout.fragment_home, container, false);

        btnConvencion = vista.findViewById(R.id.btn_convencion);
        btnConvencion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuQawayFragment menuQawayFragment = new MenuQawayFragment();
                FragmentTransaction trans = getFragmentManager().beginTransaction();
                trans.replace(R.id.contenedor, menuQawayFragment);
                trans.commit();
                //enviar provincia seleccionada
                Provincia p = new Provincia(1, "La Convencion",R.drawable.pconvencion);
                interfaceComunicaFragmentes.enviarProvincia(p);
            }
        });

        btnCalca = vista.findViewById(R.id.btn_calca);
        btnCalca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuQawayFragment menuQawayFragment = new MenuQawayFragment();
                FragmentTransaction trans = getFragmentManager().beginTransaction();
                trans.replace(R.id.contenedor, menuQawayFragment);
                trans.commit();
                //enviar provincia seleccionada
                Provincia p = new Provincia(2, "Calca",R.drawable.pcalca);
                interfaceComunicaFragmentes.enviarProvincia(p);
            }
        });

        btnUrubamba = vista.findViewById(R.id.btn_urubamba);
        btnUrubamba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuQawayFragment menuQawayFragment = new MenuQawayFragment();
                FragmentTransaction trans = getFragmentManager().beginTransaction();
                trans.replace(R.id.contenedor, menuQawayFragment);
                trans.commit();
                //enviar provincia seleccionada
                Provincia p = new Provincia(3, "Urubamba",R.drawable.purubamba);
                interfaceComunicaFragmentes.enviarProvincia(p);
            }
        });

        btnPaucartambo = vista.findViewById(R.id.btn_paucartambo);
        btnPaucartambo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuQawayFragment menuQawayFragment = new MenuQawayFragment();
                FragmentTransaction trans = getFragmentManager().beginTransaction();
                trans.replace(R.id.contenedor, menuQawayFragment);
                trans.commit();
                //enviar provincia seleccionada
                Provincia p = new Provincia(4, "Paucartambo",R.drawable.ppaucartambo);
                interfaceComunicaFragmentes.enviarProvincia(p);
            }
        });

        btnAnta = vista.findViewById(R.id.btn_anta);
        btnAnta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuQawayFragment menuQawayFragment = new MenuQawayFragment();
                FragmentTransaction trans = getFragmentManager().beginTransaction();
                trans.replace(R.id.contenedor, menuQawayFragment);
                trans.commit();
                //enviar provincia seleccionada
                Provincia p = new Provincia(5, "Anta",R.drawable.panta);
                interfaceComunicaFragmentes.enviarProvincia(p);
            }
        });

        btnCusco = vista.findViewById(R.id.btn_cusco);
        btnCusco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuQawayFragment menuQawayFragment = new MenuQawayFragment();
                FragmentTransaction trans = getFragmentManager().beginTransaction();
                trans.replace(R.id.contenedor, menuQawayFragment);
                trans.commit();
                //enviar provincia seleccionada
                Provincia p = new Provincia(6, "Cusco",R.drawable.pcusco);
                interfaceComunicaFragmentes.enviarProvincia(p);
            }
        });

        btnQuispicanchi = vista.findViewById(R.id.btn_quispicanchi);
        btnQuispicanchi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuQawayFragment menuQawayFragment = new MenuQawayFragment();
                FragmentTransaction trans = getFragmentManager().beginTransaction();
                trans.replace(R.id.contenedor, menuQawayFragment);
                trans.commit();
                //enviar provincia seleccionada
                Provincia p = new Provincia(7, "Quispicanchi",R.drawable.pquispicanchi);
                interfaceComunicaFragmentes.enviarProvincia(p);
            }
        });
        btnParuro = vista.findViewById(R.id.btn_paruro);
        btnParuro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuQawayFragment menuQawayFragment = new MenuQawayFragment();
                FragmentTransaction trans = getFragmentManager().beginTransaction();
                trans.replace(R.id.contenedor, menuQawayFragment);
                trans.commit();
                //enviar provincia seleccionada
                Provincia p = new Provincia(8, "Paruro",R.drawable.pparuro);
                interfaceComunicaFragmentes.enviarProvincia(p);
            }
        });
        btnAcomayo = vista.findViewById(R.id.btn_acomayo);
        btnAcomayo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuQawayFragment menuQawayFragment = new MenuQawayFragment();
                FragmentTransaction trans = getFragmentManager().beginTransaction();
                trans.replace(R.id.contenedor, menuQawayFragment);
                trans.commit();
                //enviar provincia seleccionada
                Provincia p = new Provincia(9, "Acomayo",R.drawable.pacomayo);
                interfaceComunicaFragmentes.enviarProvincia(p);
            }
        });
        btnCanchis = vista.findViewById(R.id.btn_canchis);
        btnCanchis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuQawayFragment menuQawayFragment = new MenuQawayFragment();
                FragmentTransaction trans = getFragmentManager().beginTransaction();
                trans.replace(R.id.contenedor, menuQawayFragment);
                trans.commit();
                //enviar provincia seleccionada
                Provincia p = new Provincia(10, "Canchis",R.drawable.pcanchis);
                interfaceComunicaFragmentes.enviarProvincia(p);
            }
        });

        btnChumbivilcas = vista.findViewById(R.id.btn_chumbivilcas);
        btnChumbivilcas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuQawayFragment menuQawayFragment = new MenuQawayFragment();
                FragmentTransaction trans = getFragmentManager().beginTransaction();
                trans.replace(R.id.contenedor, menuQawayFragment);
                trans.commit();
                //enviar provincia seleccionada
                Provincia p = new Provincia(11, "Chumbivilcas",R.drawable.pchumbivilcas);
                interfaceComunicaFragmentes.enviarProvincia(p);
            }
        });

        btnCanas = vista.findViewById(R.id.btn_canas);
        btnCanas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuQawayFragment menuQawayFragment = new MenuQawayFragment();
                FragmentTransaction trans = getFragmentManager().beginTransaction();
                trans.replace(R.id.contenedor, menuQawayFragment);
                trans.commit();
                //enviar provincia seleccionada
                Provincia p = new Provincia(12, "Canas",R.drawable.pcanas);
                interfaceComunicaFragmentes.enviarProvincia(p);
            }
        });
        btnEspinar = vista.findViewById(R.id.btn_espinar);
        btnEspinar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuQawayFragment menuQawayFragment = new MenuQawayFragment();
                FragmentTransaction trans = getFragmentManager().beginTransaction();
                trans.replace(R.id.contenedor, menuQawayFragment);
                trans.commit();
                //enviar provincia seleccionada
                Provincia p = new Provincia(13, "Espinar",R.drawable.pespinar);
                interfaceComunicaFragmentes.enviarProvincia(p);
            }
        });

        return vista;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.actividad = (Activity) context;
            interfaceComunicaFragmentes = (IComunicaFragments) this.actividad;


        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}