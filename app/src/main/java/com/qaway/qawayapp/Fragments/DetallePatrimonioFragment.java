package com.qaway.qawayapp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qaway.qawayapp.Entidades.Patrimonio;
import com.qaway.qawayapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetallePatrimonioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetallePatrimonioFragment extends Fragment {

    //variables referencia para recibir objeto
    TextView nomdetpatrimonio, desdetpatromonio;
    ImageView imgdetpatromonio;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DetallePatrimonioFragment() {
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
    public static DetallePatrimonioFragment newInstance(String param1, String param2) {
        DetallePatrimonioFragment fragment = new DetallePatrimonioFragment();
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
        View vista=inflater.inflate(R.layout.fragment_detalle_patrimonio, container, false);
        nomdetpatrimonio=vista.findViewById(R.id.nomDetPatrimonio);
        desdetpatromonio=vista.findViewById(R.id.desDetPatrimonio);
        imgdetpatromonio=vista.findViewById(R.id.imgDetPatrimonio);

        //crear objeto bundle para recibir el objero enviado por argumentos
        Bundle objetoPatrimonio= getArguments();
        Patrimonio patrimonio=null;
        //validacion para verificar si existe argumento enviado para mostrar
        if (objetoPatrimonio!=null){
            patrimonio= (Patrimonio) objetoPatrimonio.getSerializable("patrimonio");
            //establecer datos en la vista
            nomdetpatrimonio.setText(patrimonio.getNomPatrimonio());
            desdetpatromonio.setText(patrimonio.getDesPatrimonio());
            imgdetpatromonio.setImageResource(patrimonio.getImgPatrimonio());

        }

        return vista;
    }
}