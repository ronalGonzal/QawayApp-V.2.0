package com.qaway.qawayapp.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.qaway.qawayapp.Adaptadores.AdapterDanza;
import com.qaway.qawayapp.Entidades.Danza;
import com.qaway.qawayapp.Interfaces.IComunicaFragments;
import com.qaway.qawayapp.R;

import java.util.ArrayList;

public class DanzaFragment extends Fragment {

    AdapterDanza adapterDanza;
    RecyclerView recyclerViewDanza;
    ArrayList<Danza> listaDanza;

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

    public DanzaFragment() {
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
    public static DanzaFragment newInstance(String param1, String param2) {
        DanzaFragment fragment = new DanzaFragment();
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
        View vista = inflater.inflate(R.layout.fragment_danza, container, false);
        recyclerViewDanza = vista.findViewById(R.id.recyDanza);
        listaDanza = new ArrayList<>();
        //cargar la lista
        cargarLista();
        //mostrar datos
        mostrarData();

        return vista;
    }

    public void cargarLista() {
        listaDanza.add(new Danza("", "", R.drawable.festivalmamachajpg));
        listaDanza.add(new Danza("", "", R.drawable.huchuyqosqo));
        listaDanza.add(new Danza("", "", R.drawable.urco));
        listaDanza.add(new Danza("", "", R.drawable.ancasmarka));
        listaDanza.add(new Danza("", "", R.drawable.inkariy));

    }

    public void mostrarData() {
        recyclerViewDanza.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterDanza = new AdapterDanza(getContext(),
                listaDanza) {
        };
        recyclerViewDanza.setAdapter(adapterDanza);

        adapterDanza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomdanza = listaDanza.get(recyclerViewDanza.getChildAdapterPosition(v)).getNomDanza();
                Toast.makeText(getContext(), "Selecciono: " + nomdanza, Toast.LENGTH_LONG).show();
                //enviar el objeto
                interfaceComunicaFragmentes.enviarDanza(listaDanza.get(recyclerViewDanza.getChildAdapterPosition(v)));
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
