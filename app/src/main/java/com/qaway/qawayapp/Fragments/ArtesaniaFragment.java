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
import com.qaway.qawayapp.Adaptadores.AdapterArtesania;
import com.qaway.qawayapp.Adaptadores.AdapterDanza;
import com.qaway.qawayapp.Entidades.Artesania;
import com.qaway.qawayapp.Entidades.Danza;
import com.qaway.qawayapp.Entidades.Provincia;
import com.qaway.qawayapp.Interfaces.IComunicaFragments;
import com.qaway.qawayapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ArtesaniaFragment extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener{

    AdapterArtesania adapterArtesania;
    RecyclerView recyclerViewArtesania;
    ArrayList<Artesania> listaArtesania;

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

    public ArtesaniaFragment() {
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
    public static ArtesaniaFragment newInstance(String param1, String param2) {
        ArtesaniaFragment fragment = new ArtesaniaFragment();
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
        View vista = inflater.inflate(R.layout.fragment_artesania, container, false);
        listaArtesania = new ArrayList<>();
        recyclerViewArtesania = (RecyclerView) vista.findViewById(R.id.recyArtesania);
        recyclerViewArtesania.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerViewArtesania.setHasFixedSize(true);

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

        String url = "http://tallerpro-001-site1.btempurl.com/wsJSONConsultarArtesaniasxProvincia.php?idProvincia=" + idProvincia;

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        request.add(jsonObjectRequest);

    }

    public void cargarLista() {
        //listaArtesania.add(new Artesania("Textilería", "Es original y de mejor calidad está refugiada en el mundo rural, en provincias como la de Calca y Urubamba, o en otras más apartadas de la capital departamental.", R.drawable.muecas));
        //listaArtesania.add(new Artesania("Orfebrería, los plateros cusqueños", " Son diestros en la fabricación de joyas, objetos para el culto religioso y diversos objetos utilitarios, producción toda que se diferencia ventajosamente de la fabricada en serie mediante el vaciado en moldes y el estampado.", R.drawable.ceramica));
        //listaArtesania.add(new Artesania("Artesanía cusqueña", "Constituye además un atractivo para los turistas. En Cusco, esta actividad no es un simple souvenir, sino un símbolo histórico, la expresión pura del arte popular. El distrito de Chinchero (Urubamba) pone en evidencia ese rasgo.", R.drawable.art1));
        //listaArtesania.add(new Artesania("Antonio Olave", "Ha alcanzado gran maestría en la fabricación de niños \"Manuelitos\" con detalles como su paladar de espejo, dientes de plumas de aves y cabello natural.", R.drawable.art2));
        //listaArtesania.add(new Artesania("Edilberto Mérida", " Se aleja de los imagineros tradicionales, pues prefiere las imágenes grotescas con los rasgos exegerados, aunque sus motivos de inspiración son también, con frecuencia, religiosos, además de costumbristas.", R.drawable.art2));

    }

    public void mostrarData() {
        recyclerViewArtesania.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterArtesania = new AdapterArtesania(getContext(),
                listaArtesania);
        recyclerViewArtesania.setAdapter(adapterArtesania);

        adapterArtesania.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomartesano = listaArtesania.get(recyclerViewArtesania.getChildAdapterPosition(v)).getNomArtesano();
                Toast.makeText(getContext(), "Selecciono: " + nomartesano, Toast.LENGTH_LONG).show();
                //enviar el objeto
                interfaceComunicaFragmentes.enviarArtesania(listaArtesania.get(recyclerViewArtesania.getChildAdapterPosition(v)));
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
        Artesania artesania = null;

        JSONArray json = response.optJSONArray("Artesanias");

        try {
            for (int i = 0; i < json.length(); i++) {
                artesania = new Artesania();
                JSONObject jsonObject = null;
                jsonObject = json.getJSONObject(i);

                artesania.setNomArtesano(jsonObject.optString("nomArtesania"));
                artesania.setDesArtesano(jsonObject.optString("desArtesania"));
                artesania.setDatoimagen(jsonObject.optString("imgArtesania"));
                //danza.setImgDanza(R.drawable.carnaval);

                listaArtesania.add(artesania);
            }
            progress.hide();
            adapterArtesania = new AdapterArtesania(getContext(),
                    listaArtesania) {
            };
            recyclerViewArtesania.setAdapter(adapterArtesania);
            adapterArtesania.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String nomarte = listaArtesania.get(recyclerViewArtesania.getChildAdapterPosition(v)).getNomArtesano();
                    Toast.makeText(getContext(), "Selecciono: " + nomarte, Toast.LENGTH_LONG).show();
                    //enviar el objeto
                    interfaceComunicaFragmentes.enviarArtesania(listaArtesania.get(recyclerViewArtesania.getChildAdapterPosition(v)));
                }
            });


        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "No se a podido establecer conexcion con el servidor " + response, Toast.LENGTH_LONG).show();
            progress.hide();
        }
    }
}