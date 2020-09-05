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

import com.qaway.qawayapp.Adaptadores.AdapterPatrimonio;
import com.qaway.qawayapp.Entidades.Patrimonio;
import com.qaway.qawayapp.Interfaces.IComunicaFragments;
import com.qaway.qawayapp.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PatrimoniosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PatrimoniosFragment extends Fragment {

    AdapterPatrimonio adapterPatrimonio;
    RecyclerView recyclerViewPatrimonio;
    ArrayList<Patrimonio> listaPatrimonio;

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

    public PatrimoniosFragment() {
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
    public static PatrimoniosFragment newInstance(String param1, String param2) {
        PatrimoniosFragment fragment = new PatrimoniosFragment();
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
        View vista = inflater.inflate(R.layout.fragment_patrimonios, container, false);
        recyclerViewPatrimonio = vista.findViewById(R.id.recyPatrimonios);
        listaPatrimonio = new ArrayList<>();
        //cargar la lista
        cargarLista();
        //mostrar datos
        mostrarData();

        return vista;
    }

    public void cargarLista() {
        listaPatrimonio.add(new Patrimonio("Festividad de la Mamacha", "El Ministerio de Cultura declaró Patrimonio Cultural de la Nación a la Festividad patronal de la Mamacha Asunta de Calca, tradicional fiesta que se celebra en dicha provincia cusqueña del 14 al 19 de agosto de cada año.", R.drawable.festivalmamachajpg));
        listaPatrimonio.add(new Patrimonio("Huchuy Qosqo", "es una ciudadela arqueológica ubicada en Lamay, a una altura de 3.600 metros, con edificaciones de hasta dos pisos, erigidas sobre piedra pulida, con adoquines de barro y rodeada de algunas pozas.", R.drawable.huchuyqosqo));
        listaPatrimonio.add(new Patrimonio("Unu Urco", "rodeado de un formidable sistema de andenes y que tiene como función el culto al agua, cuya fiesta ancestral continúa realizándose anualmente, cada primero de octubre (Unu Urco), en la explanada del complejo.", R.drawable.urco));
        listaPatrimonio.add(new Patrimonio("Ancasmarka", "que constituiría un centro de provisión de alimentos de diferentes pisos, para su posterior distribución entre la población. Mant’o es otro sitio, ubicado en la comunidad campesina de Matinga (Lares), que conserva petroglifos con diversasrepresentaciones de camélidos, jaguares, monos, serpientes y humanas.", R.drawable.ancasmarka));
        listaPatrimonio.add(new Patrimonio("Inkariy", "Este escenifica, en sus ocho pabellones, igual número de sociedades precolombinas, desde Caral hasta la Inca, con las manifestaciones artísticas y culturales  más importantes de cada una de ellas.", R.drawable.inkariy));

    }

    public void mostrarData() {
        recyclerViewPatrimonio.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterPatrimonio = new AdapterPatrimonio(getContext(), listaPatrimonio);
        recyclerViewPatrimonio.setAdapter(adapterPatrimonio);

        adapterPatrimonio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nompatrimonio = listaPatrimonio.get(recyclerViewPatrimonio.getChildAdapterPosition(v)).getNomPatrimonio();
                Toast.makeText(getContext(), "Selecciono: " + nompatrimonio, Toast.LENGTH_LONG).show();
                //enviar el objeto
                interfaceComunicaFragmentes.enviarPatrimonio(listaPatrimonio.get(recyclerViewPatrimonio.getChildAdapterPosition(v)));
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