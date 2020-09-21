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
import com.qaway.qawayapp.Adaptadores.AdapterLiteratura;
import com.qaway.qawayapp.Entidades.Danza;
import com.qaway.qawayapp.Entidades.Literatura;
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
public class LiteraturaFragment extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {

    AdapterLiteratura adapterLiteratura;
    RecyclerView recyclerViewLiteratura;
    ArrayList<Literatura> listaLiteratura;

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

        listaLiteratura = new ArrayList<>();
        recyclerViewLiteratura = (RecyclerView) vista.findViewById(R.id.recyLiteratura);
        recyclerViewLiteratura.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerViewLiteratura.setHasFixedSize(true);

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

        String url = "http://tallerpro-001-site1.btempurl.com/wsJSONConsultarLiteraturasxProvincia.php?idProvincia=" + idProvincia;

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        request.add(jsonObjectRequest);

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
        Literatura literatura = null;

        JSONArray json = response.optJSONArray("Literaturas");

        try {
            for (int i = 0; i < json.length(); i++) {
                literatura = new Literatura();
                JSONObject jsonObject = null;
                jsonObject = json.getJSONObject(i);

                literatura.setNomLiteratura(jsonObject.optString("nomAutor"));
                literatura.setDesLiteratura(jsonObject.optString("desAutor"));
                literatura.setDatoimagen(jsonObject.optString("imgAutor"));


                listaLiteratura.add(literatura);
            }
            progress.hide();
            adapterLiteratura = new AdapterLiteratura(getContext(),
                    listaLiteratura) {
            };
            recyclerViewLiteratura.setAdapter(adapterLiteratura);
            adapterLiteratura.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String nomlite = listaLiteratura.get(recyclerViewLiteratura.getChildAdapterPosition(v)).getNomLiteratura();
                    Toast.makeText(getContext(), "Selecciono: " + nomlite, Toast.LENGTH_LONG).show();
                    //enviar el objeto
                    interfaceComunicaFragmentes.enviarLiteratura(listaLiteratura.get(recyclerViewLiteratura.getChildAdapterPosition(v)));
                }
            });


        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "No se a podido establecer conexcion con el servidor " + response, Toast.LENGTH_LONG).show();
            progress.hide();
        }
    }
    public void cargarLista() {
       // listaLiteratura.add(new Literatura("Soledad Araoz.", "Nació en el Cusco, en 1976. A Soledad la acompaña una hija, tiene los cabellos enrulados, y ha bebido mucha agua del río Tambopata, por lo que la selva la llama con frecuencia.", R.drawable.soledad));
        //listaLiteratura.add(new Literatura("Germán Bausch", "Profesor Jubilado, poeta, escritor i compositor. Nació en Cusco en 1921", R.drawable.german));
        //listaLiteratura.add(new Literatura("Raúl Brozovich", "Fue miembro de la generación literaria del 50 y fundador del grupo cultural Rumiñahui. Raúl Brozovich Mendoza (Cusco 1928 - 2006)", R.drawable.raul));
        //listaLiteratura.add(new Literatura("Tania Castro", "Tania Castro es una de las artistas más interesantes, claras y luminosas del Cusco. Es actriz, educadora y comunicadora social, con enfoque en el poder sanador de la palabra.", R.drawable.tania));

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