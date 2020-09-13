package com.qaway.qawayapp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.qaway.qawayapp.Entidades.Patrimonio;
import com.qaway.qawayapp.Entidades.Provincia;
import com.qaway.qawayapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuQawayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuQawayFragment extends Fragment {

    //variables referencia para recibir objeto provincia
    TextView nomProvincia;
    ImageView imgProvincia;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MenuQawayFragment() {
        // Required empty public constructor
    }

    // variables para los botonos del menu
    Button btnPatrimonios;
    Button btnLiteratura;
    Button btnArtesania;
    Button btnDanza;
    Button btnTuristicos;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MenuQawayFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MenuQawayFragment newInstance(String param1, String param2) {
        MenuQawayFragment fragment = new MenuQawayFragment();
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
        View vista = inflater.inflate(R.layout.fragment_menu_qaway, container, false);

        nomProvincia = vista.findViewById(R.id.nomProvinciaS);
        imgProvincia= vista.findViewById(R.id.imgProvinciaS);
        //crear objeto bundle para recibir el objero enviado por argumentos
        Bundle objetoProvincia = getArguments();
        Provincia provincia = null;
        //validacion para verificar si existe argumento enviado para mostrar
        if (objetoProvincia != null) {
            provincia = (Provincia) objetoProvincia.getSerializable("provincia");
            //establecer datos en la vista
            nomProvincia.setText(provincia.getNomProvincia());
            imgProvincia.setImageResource(provincia.getImgProvincia());


        }

        btnPatrimonios = vista.findViewById(R.id.btnPatrimonios);
        btnPatrimonios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PatrimoniosFragment patrimoniosFragment = new PatrimoniosFragment();
                FragmentTransaction trans = getFragmentManager().beginTransaction();
                trans.replace(R.id.contenedor, patrimoniosFragment);
                trans.commit();


            }
        });

        btnLiteratura = vista.findViewById(R.id.btnLiteratura);
        btnLiteratura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LiteraturaFragment literaturaFragment = new LiteraturaFragment();
                FragmentTransaction trans = getFragmentManager().beginTransaction();
                trans.replace(R.id.contenedor, literaturaFragment);
                trans.commit();


            }
        });


        btnArtesania = vista.findViewById(R.id.btnArtesanias);
        btnArtesania.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArtesaniaFragment artesaniaFragment = new ArtesaniaFragment();
                FragmentTransaction trans = getFragmentManager().beginTransaction();
                trans.replace(R.id.contenedor, artesaniaFragment);
                trans.commit();


            }
        });

        btnDanza = vista.findViewById(R.id.btnDanzas);
        btnDanza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DanzaFragment danzaFragment = new DanzaFragment();
                FragmentTransaction trans = getFragmentManager().beginTransaction();
                trans.replace(R.id.contenedor, danzaFragment);
                trans.commit();


            }
        });

        btnTuristicos = vista.findViewById(R.id.btnLugares);
        btnTuristicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TuristicosFragment turisticosFragment = new TuristicosFragment();
                FragmentTransaction trans = getFragmentManager().beginTransaction();
                trans.replace(R.id.contenedor, turisticosFragment);
                trans.commit();


            }
        });



        return vista;
    }
}