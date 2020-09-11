package com.qaway.qawayapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.qaway.qawayapp.Entidades.Literatura;
import com.qaway.qawayapp.R;

public class DetalleLiteraturaFragment extends Fragment {

    //variables referencia para recibir objeto
    TextView nomdetliteratura, desdetliteratura;
    ImageView imgdetliteratura;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DetalleLiteraturaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetallePatrimonioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetalleLiteraturaFragment newInstance(String param1, String param2) {
        DetalleLiteraturaFragment fragment = new DetalleLiteraturaFragment();
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
        View vista=inflater.inflate(R.layout.fragment_detalle_literatura, container, false);
        nomdetliteratura=vista.findViewById(R.id.nomDetLiteratura);
        desdetliteratura=vista.findViewById(R.id.desDetLiteratura);
        imgdetliteratura=vista.findViewById(R.id.imgDetLiteratura);

        //crear objeto bundle para recibir el objero enviado por argumentos
        Bundle objetoLiteratura= getArguments();
        //validacion para verificar si existe argumento enviado para mostrar
        if (objetoLiteratura!=null){
            Literatura literatura = (Literatura) objetoLiteratura.getSerializable("literatura");
            //establecer datos en la vista
            nomdetliteratura.setText(literatura.getNomLiteratura());
            desdetliteratura.setText(literatura.getDesLiteratura());
            imgdetliteratura.setImageResource(literatura.getImgLiteratura());

        }

        return vista;
    }
}

