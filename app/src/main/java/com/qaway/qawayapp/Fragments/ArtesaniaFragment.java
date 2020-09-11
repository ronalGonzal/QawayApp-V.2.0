package com.qaway.qawayapp.Fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.qaway.qawayapp.Adaptadores.AdapterArtesania;
import com.qaway.qawayapp.Entidades.Artesania;
import com.qaway.qawayapp.Interfaces.IComunicaFragments;
import com.qaway.qawayapp.R;

import java.util.ArrayList;


public class ArtesaniaFragment extends Fragment {

    AdapterArtesania adapterArtesania;
    RecyclerView recyclerViewArtesania;
    ArrayList<Artesania> listaArtesania;

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

    public ArtesaniaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PatrimoniosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ArtesaniaFragment newInstance(String param1, String param2) {
        ArtesaniaFragment fragment = new ArtesaniaFragment();
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
        View vista = inflater.inflate(R.layout.fragment_artesania, container, false);
        recyclerViewArtesania = vista.findViewById(R.id.recyArtesania);
        listaArtesania = new ArrayList<>();
        //cargar la lista
        cargarLista();
        //mostrar datos
        mostrarData();

        return vista;
    }

    public void cargarLista() {
        listaArtesania.add(new Artesania("", "", R.drawable.festivalmamachajpg));
        listaArtesania.add(new Artesania("", "", R.drawable.huchuyqosqo));
        listaArtesania.add(new Artesania("", "", R.drawable.urco));
        listaArtesania.add(new Artesania("", "", R.drawable.ancasmarka));
        listaArtesania.add(new Artesania("", "", R.drawable.inkariy));

    }

    public void mostrarData() {
        recyclerViewArtesania.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterArtesania = new AdapterArtesania(getContext(),
                listaArtesania);
        recyclerViewArtesania.setAdapter(adapterArtesania);

        adapterArtesania.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomartesano = listaArtesania.get(recyclerViewArtesania.getChildAdapterPosition(v)).getNomArtesano();
                Toast.makeText(getContext(), "Selecciono: " + nomartesano, Toast.LENGTH_LONG).show();
                //enviar el objeto
                interfaceComunicaFragmentes.enviarArtesania(listaArtesania.get(recyclerViewArtesania.getChildAdapterPosition(v)));
            }
        });
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