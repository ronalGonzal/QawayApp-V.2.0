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

import com.qaway.qawayapp.Adaptadores.AdapterTuristicos;
import com.qaway.qawayapp.Entidades.Turisticos;
import com.qaway.qawayapp.Interfaces.IComunicaFragments;
import com.qaway.qawayapp.R;

import java.util.ArrayList;

public class TuristicosFragment extends Fragment {

    AdapterTuristicos adapterTuristicos;
    RecyclerView recyclerViewTuristicos;
    ArrayList<Turisticos> listaTuristicos;

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

    public TuristicosFragment() {
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
    public static TuristicosFragment newInstance(String param1, String param2) {
        TuristicosFragment fragment = new TuristicosFragment();
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
        View vista = inflater.inflate(R.layout.fragment_turisticos, container, false);
        recyclerViewTuristicos = vista.findViewById(R.id.recyTuristicos);
        listaTuristicos = new ArrayList<>();
        //cargar la lista
        cargarLista();
        //mostrar datos
        mostrarData();

        return vista;
    }

    public void cargarLista() {
        listaTuristicos.add(new Turisticos("Machu Picchu", "La ciudad inca de Machu Picchu es una obra maestra de la arquitectura e ingeniería inca.", R.drawable.machupicchu));
        listaTuristicos.add(new Turisticos("Montaña de 7 Colores", "La montaña Vinicunca, más conocido como la Montaña de Colores, es una formación montañosa teñida de las tonalidades del arcoíris: rojo, morado, verde, amarillo, rosado y otras variaciones.", R.drawable.montaa7));
        listaTuristicos.add(new Turisticos("Salineras de Maras", "Las Minas de sal de Salineras, en el pueblo tradicional de Maras, que han sido explotadas desde la época Inca.", R.drawable.salina));
        listaTuristicos.add(new Turisticos("Moray", "Andenes agrícolas que sirvieron de laboratorio agrícola inca para experimentar la adaptación de los cultivos en los diferentes pisos ecológicos.", R.drawable.andenes));
        listaTuristicos.add(new Turisticos("Basílica Catedral de Cusco", "Es la edificación religiosa más importante de la ciudad de Cusco de estilo renacentista y barroco andino", R.drawable.plaza));

    }

    public void mostrarData() {
        recyclerViewTuristicos.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterTuristicos = new AdapterTuristicos(getContext(),
                listaTuristicos) {
        };
        recyclerViewTuristicos.setAdapter(adapterTuristicos);

        adapterTuristicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomturisticos = listaTuristicos.get(recyclerViewTuristicos.getChildAdapterPosition(v)).getNomTuristicos();
                //enviar el objetos();
                Toast.makeText(getContext(), "Selecciono: " + nomturisticos, Toast.LENGTH_LONG).show();
                interfaceComunicaFragmentes.enviarTuristicos(listaTuristicos.get(recyclerViewTuristicos.getChildAdapterPosition(v)));
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
