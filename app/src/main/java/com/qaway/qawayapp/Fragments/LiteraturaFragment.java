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

import com.qaway.qawayapp.Adaptadores.AdapterLiteratura;
import com.qaway.qawayapp.Entidades.Literatura;
import com.qaway.qawayapp.Interfaces.IComunicaFragments;
import com.qaway.qawayapp.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PatrimoniosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LiteraturaFragment extends Fragment {

    AdapterLiteratura adapterLiteratura;
    RecyclerView recyclerViewLiteratura;
    ArrayList<Literatura> listaLiteratura;

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

    public LiteraturaFragment() {
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
    public static LiteraturaFragment newInstance(String param1, String param2) {
        LiteraturaFragment fragment = new LiteraturaFragment();
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
        View vista = inflater.inflate(R.layout.fragment_literatura, container, false);
        recyclerViewLiteratura = vista.findViewById(R.id.recyLiteratura);
        listaLiteratura = new ArrayList<>();
        //cargar la lista
        cargarLista();
        //mostrar datos
        mostrarData();

        return vista;
    }

    public void cargarLista() {
        listaLiteratura.add(new Literatura("Soledad Araoz.", "Nació en el Cusco, en 1976. A Soledad la acompaña una hija, tiene los cabellos enrulados, y ha bebido mucha agua del río Tambopata, por lo que la selva la llama con frecuencia.", R.drawable.soledad));
        listaLiteratura.add(new Literatura("Germán Bausch", "Profesor Jubilado, poeta, escritor i compositor. Nació en Cusco en 1921", R.drawable.german));
        listaLiteratura.add(new Literatura("Raúl Brozovich", "Fue miembro de la generación literaria del 50 y fundador del grupo cultural Rumiñahui. Raúl Brozovich Mendoza (Cusco 1928 - 2006)", R.drawable.raul));
        listaLiteratura.add(new Literatura("Tania Castro", "Tania Castro es una de las artistas más interesantes, claras y luminosas del Cusco. Es actriz, educadora y comunicadora social, con enfoque en el poder sanador de la palabra.", R.drawable.tania));

    }

    public void mostrarData() {
        recyclerViewLiteratura.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterLiteratura = new AdapterLiteratura(getContext(), listaLiteratura);
        recyclerViewLiteratura.setAdapter(adapterLiteratura);

        adapterLiteratura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomLiteratura = listaLiteratura.get(recyclerViewLiteratura.getChildAdapterPosition(v)).getNomLiteratura();
                Toast.makeText(getContext(), "Selecciono: " + nomLiteratura, Toast.LENGTH_LONG).show();
                //enviar el objeto
                interfaceComunicaFragmentes.enviarLiteratura(listaLiteratura.get(recyclerViewLiteratura.getChildAdapterPosition(v)));
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