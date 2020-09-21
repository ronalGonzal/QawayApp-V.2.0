package com.qaway.qawayapp.Fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.qaway.qawayapp.Adaptadores.AdapterDanza;
import com.qaway.qawayapp.Adaptadores.AdapterPatrimonio;
import com.qaway.qawayapp.Entidades.Danza;
import com.qaway.qawayapp.Entidades.Patrimonio;
import com.qaway.qawayapp.Entidades.Provincia;
import com.qaway.qawayapp.Interfaces.IComunicaFragments;
import com.qaway.qawayapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PatrimoniosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PatrimoniosFragment extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {

    AdapterPatrimonio adapterPatrimonio;
    RecyclerView recyclerViewPatrimonio;
    ArrayList<Patrimonio> listaPatrimonio;

    //referencias para comunicar fragment
    Activity actividad;
    IComunicaFragments interfaceComunicaFragmentes;

    //variables para trabajar con webservices
    ProgressDialog progress;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

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
        listaPatrimonio = new ArrayList<>();
        recyclerViewPatrimonio = (RecyclerView)  vista.findViewById(R.id.recyPatrimonios);
        recyclerViewPatrimonio.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerViewPatrimonio.setHasFixedSize(true);

        ///
        //crear objeto bundle para recibir el objero enviado por argumentos
        Bundle objetoProvincia = getArguments();
        Provincia provincia = null;
        //validacion para verificar si existe argumento enviado para mostrar
        if (objetoProvincia != null) {
            provincia = (Provincia) objetoProvincia.getSerializable("provincia");
            request = Volley.newRequestQueue(getContext());


            cargarWebservices( String.valueOf(provincia.getIdProvincia()));
            //establecer datos en la vista
             //Toast.makeText(getContext(), "Estas en la patrimonios y provincia " + provincia.getNomProvincia() + " - " + provincia.getIdProvincia() , Toast.LENGTH_LONG).show();
        }else   {
            //Toast.makeText(getContext(), "La provincia esta en nullo" , Toast.LENGTH_LONG).show();
        }


        //cargar la lista
        //cargarLista();
        //mostrar datos
        //mostrarData();

        return vista;
    }

    private void cargarWebservices(String idProvincia) {
        progress = new ProgressDialog(getContext());
        progress.setMessage("Consultando...");
        progress.show();
        //Toast.makeText(getContext(), "comienz acarga webservices" , Toast.LENGTH_LONG).show();
        String url = "http://tallerpro-001-site1.btempurl.com/wsJSONConsultarPatrimoniosxProvincia.php?idProvincia=" + idProvincia;
        //Toast.makeText(getContext(), "paso lectura de webservies" , Toast.LENGTH_LONG).show();
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        request.add(jsonObjectRequest);

    }

    public void cargarLista() {
        //listaPatrimonio.add(new Patrimonio("Festividad de la Mamacha", "El Ministerio de Cultura declaró Patrimonio Cultural de la Nación a la Festividad patronal de la Mamacha Asunta de Calca, tradicional fiesta que se celebra en dicha provincia cusqueña del 14 al 19 de agosto de cada año.", R.drawable.festivalmamachajpg));
        //listaPatrimonio.add(new Patrimonio("Huchuy Qosqo", "es una ciudadela arqueológica ubicada en Lamay, a una altura de 3.600 metros, con edificaciones de hasta dos pisos, erigidas sobre piedra pulida, con adoquines de barro y rodeada de algunas pozas.", R.drawable.huchuyqosqo));
        //listaPatrimonio.add(new Patrimonio("Unu Urco", "rodeado de un formidable sistema de andenes y que tiene como función el culto al agua, cuya fiesta ancestral continúa realizándose anualmente, cada primero de octubre (Unu Urco), en la explanada del complejo.", R.drawable.urco));
        //listaPatrimonio.add(new Patrimonio("Ancasmarka", "que constituiría un centro de provisión de alimentos de diferentes pisos, para su posterior distribución entre la población. Mant’o es otro sitio, ubicado en la comunidad campesina de Matinga (Lares), que conserva petroglifos con diversasrepresentaciones de camélidos, jaguares, monos, serpientes y humanas.", R.drawable.ancasmarka));
        //listaPatrimonio.add(new Patrimonio("Inkariy", "Este escenifica, en sus ocho pabellones, igual número de sociedades precolombinas, desde Caral hasta la Inca, con las manifestaciones artísticas y culturales  más importantes de cada una de ellas.", R.drawable.inkariy));

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
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(), "No se puede conectar " + error.toString(), Toast.LENGTH_LONG).show();
        System.out.println();
        Log.d("ERROR: ", error.toString());
        progress.hide();

    }

    @Override
    public void onResponse(JSONObject response) {
        Patrimonio patrimonio = null;

        JSONArray json = response.optJSONArray("Patrimonios");

        try {
            for (int i = 0; i < json.length(); i++) {
                patrimonio = new Patrimonio();
                JSONObject jsonObject = null;
                jsonObject = json.getJSONObject(i);

                patrimonio.setNomPatrimonio(jsonObject.optString("nomPatrimonio"));
                patrimonio.setDesPatrimonio(jsonObject.optString("desPatrimonio"));
                patrimonio.setDatoimagen(jsonObject.optString("imgPatrimonio"));
                //patrimonio.setImgPatrimonio(R.drawable.carnaval);

                listaPatrimonio.add(patrimonio);
            }
            progress.hide();
            adapterPatrimonio = new AdapterPatrimonio(getContext(),
                    listaPatrimonio) {
            };
            recyclerViewPatrimonio.setAdapter(adapterPatrimonio);
            adapterPatrimonio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String nompatri = listaPatrimonio.get(recyclerViewPatrimonio.getChildAdapterPosition(v)).getNomPatrimonio();
                    Toast.makeText(getContext(), "Selecciono: " + nompatri, Toast.LENGTH_LONG).show();
                    //enviar el objeto
                    interfaceComunicaFragmentes.enviarPatrimonio(listaPatrimonio.get(recyclerViewPatrimonio.getChildAdapterPosition(v)));
                }
            });


        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "No se a podido establecer conexcion con el servidor " + response, Toast.LENGTH_LONG).show();
            progress.hide();
        }


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