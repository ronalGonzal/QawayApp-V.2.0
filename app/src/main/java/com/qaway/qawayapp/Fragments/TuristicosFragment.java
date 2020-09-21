package com.qaway.qawayapp.Fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.qaway.qawayapp.Adaptadores.AdapterDanza;
import com.qaway.qawayapp.Adaptadores.AdapterTuristicos;
import com.qaway.qawayapp.Entidades.Danza;
import com.qaway.qawayapp.Entidades.Provincia;
import com.qaway.qawayapp.Entidades.Turisticos;
import com.qaway.qawayapp.Interfaces.IComunicaFragments;
import com.qaway.qawayapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TuristicosFragment extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {

    AdapterTuristicos adapterTuristicos;
    RecyclerView recyclerViewTuristicos;
    ArrayList<Turisticos> listaTuristicos;

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
        listaTuristicos = new ArrayList<>();
        recyclerViewTuristicos = (RecyclerView) vista.findViewById(R.id.recyTuristicos);
        recyclerViewTuristicos.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerViewTuristicos.setHasFixedSize(true);

        //crear objeto bundle para recibir el objero enviado por argumentos
        Bundle objetoProvincia = getArguments();
        Provincia provincia = null;
        //validacion para verificar si existe argumento enviado para mostrar
        if (objetoProvincia != null) {
            provincia = (Provincia) objetoProvincia.getSerializable("provincia");
            request = Volley.newRequestQueue(getContext());


            cargarWebservices( String.valueOf(provincia.getIdProvincia()));
            //establecer datos en la vista
            // Toast.makeText(getContext(), "Estas en la provincia " + provincia.getNomProvincia() + " - " + provincia.getIdProvincia() , Toast.LENGTH_LONG).show();
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

        String url = "http://tallerpro-001-site1.btempurl.com/wsJSONConsultarLugaresTuristicosxProvincia.php?idProvincia=" + idProvincia;

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        request.add(jsonObjectRequest);

    }

    public void cargarLista() {
        //listaTuristicos.add(new Turisticos("Machu Picchu", "La ciudad inca de Machu Picchu es una obra maestra de la arquitectura e ingeniería inca.", R.drawable.machupicchu));
       // listaTuristicos.add(new Turisticos("Montaña de 7 Colores", "La montaña Vinicunca, más conocido como la Montaña de Colores, es una formación montañosa teñida de las tonalidades del arcoíris: rojo, morado, verde, amarillo, rosado y otras variaciones.", R.drawable.montaa7));
       // listaTuristicos.add(new Turisticos("Salineras de Maras", "Las Minas de sal de Salineras, en el pueblo tradicional de Maras, que han sido explotadas desde la época Inca.", R.drawable.salina));
      //  listaTuristicos.add(new Turisticos("Moray", "Andenes agrícolas que sirvieron de laboratorio agrícola inca para experimentar la adaptación de los cultivos en los diferentes pisos ecológicos.", R.drawable.andenes));
       // listaTuristicos.add(new Turisticos("Basílica Catedral de Cusco", "Es la edificación religiosa más importante de la ciudad de Cusco de estilo renacentista y barroco andino", R.drawable.plaza));

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

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(), "No se puede conectar " + error.toString(), Toast.LENGTH_LONG).show();
        System.out.println();
        Log.d("ERROR: ", error.toString());
        progress.hide();
    }

    @Override
    public void onResponse(JSONObject response) {
        Turisticos turisticos = null;

        JSONArray json = response.optJSONArray("Lugares Turisticos");

        try {
            for (int i = 0; i < json.length(); i++) {
                turisticos = new Turisticos();
                JSONObject jsonObject = null;
                jsonObject = json.getJSONObject(i);

                turisticos.setNomTuristicos(jsonObject.optString("nomLugar"));
                turisticos.setDesTuristicos(jsonObject.optString("desLugar"));
                turisticos.setDatoimagen(jsonObject.optString("imgLugar"));
                //danza.setImgDanza(R.drawable.carnaval);

                listaTuristicos.add(turisticos);
            }
            progress.hide();
            adapterTuristicos = new AdapterTuristicos(getContext(),
                    listaTuristicos) {
            };
            recyclerViewTuristicos.setAdapter(adapterTuristicos);
            adapterTuristicos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String nomturi = listaTuristicos.get(recyclerViewTuristicos.getChildAdapterPosition(v)).getNomTuristicos();
                    Toast.makeText(getContext(), "Selecciono: " + nomturi, Toast.LENGTH_LONG).show();
                    //enviar el objeto
                    interfaceComunicaFragmentes.enviarTuristicos(listaTuristicos.get(recyclerViewTuristicos.getChildAdapterPosition(v)));
                }
            });


        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "No se a podido establecer conexcion con el servidor " + response, Toast.LENGTH_LONG).show();
            progress.hide();
        }

    }
}
