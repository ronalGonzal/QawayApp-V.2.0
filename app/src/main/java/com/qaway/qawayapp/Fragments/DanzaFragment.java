package com.qaway.qawayapp.Fragments;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.textclassifier.TextSelection;
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
import com.qaway.qawayapp.Entidades.Danza;
import com.qaway.qawayapp.Entidades.Provincia;
import com.qaway.qawayapp.Interfaces.IComunicaFragments;
import com.qaway.qawayapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DanzaFragment extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {

    AdapterDanza adapterDanza;
    RecyclerView recyclerViewDanza;
    ArrayList<Danza> listaDanza;

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

    public DanzaFragment() {
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
    public static DanzaFragment newInstance(String param1, String param2) {
        DanzaFragment fragment = new DanzaFragment();
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
        View vista = inflater.inflate(R.layout.fragment_danza, container, false);
        listaDanza = new ArrayList<>();
        recyclerViewDanza = (RecyclerView) vista.findViewById(R.id.recyDanza);
        recyclerViewDanza.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerViewDanza.setHasFixedSize(true);

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
           // Toast.makeText(getContext(), "Estas en la provincia " + provincia.getNomProvincia() + " - " + provincia.getIdProvincia() , Toast.LENGTH_LONG).show();
        }else   {
            //Toast.makeText(getContext(), "La provincia esta en nullo" , Toast.LENGTH_LONG).show();
        }






        //cargar la lista
        // cargarLista(); usaremos webservices
        //mostrar datos
        //mostrarData(); usaremos webservices

        return vista;
    }

    private void cargarWebservices(String idProvincia) {
        progress = new ProgressDialog(getContext());
        progress.setMessage("Consultando...");
        progress.show();

        String url = "http://tallerpro-001-site1.btempurl.com/wsJSONConsultarDanzasxProvincia.php?idProvincia=" + idProvincia;

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
        Danza danza = null;

        JSONArray json = response.optJSONArray("Danzas");

        try {
            for (int i = 0; i < json.length(); i++) {
                danza = new Danza();
                JSONObject jsonObject = null;
                jsonObject = json.getJSONObject(i);

                danza.setNomDanza(jsonObject.optString("nomDanza"));
                danza.setDesDanza(jsonObject.optString("desDanza"));
                danza.setDatoimagen(jsonObject.optString("imgDanza"));
                danza.setImgDanza(R.drawable.carnaval);

                listaDanza.add(danza);
            }
            progress.hide();
            adapterDanza = new AdapterDanza(getContext(),
                    listaDanza) {
            };
            recyclerViewDanza.setAdapter(adapterDanza);
            adapterDanza.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String nomdanza = listaDanza.get(recyclerViewDanza.getChildAdapterPosition(v)).getNomDanza();
                    Toast.makeText(getContext(), "Selecciono: " + nomdanza, Toast.LENGTH_LONG).show();
                    //enviar el objeto
                   interfaceComunicaFragmentes.enviarDanza(listaDanza.get(recyclerViewDanza.getChildAdapterPosition(v)));
                }
            });


        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "No se a podido establecer conexcion con el servidor " + response, Toast.LENGTH_LONG).show();
            progress.hide();
        }


    }

    public void cargarLista() {
        listaDanza.add(new Danza("El baile del carnaval del Ampay", "El carnaval del Ampay es un baile que se originó en la provincia de Calca, Cusco. Los versos de dicho huayno cusqueño están en quechua y son enmarcados con flores silvestres con el propósito de darle un toque más festivo y tradicional.", R.drawable.carnaval, "", null));
        listaDanza.add(new Danza("La danza del Cápac Chuncho", "Dicho baile se realiza en representación a los guerreros nativos de Kosñipata, una localidad ubicada en la región selvática. Cuenta la tradición que el ch’unchu es el danzante favorito de la Virgen del Carmen. ", R.drawable.valicha, "", null));
        listaDanza.add(new Danza("La danza del Ukuku", "En la danza del Ukuku no hay grupos, existe un solo bailarín vestido con un traje parecido a la piel de un oso y una máscara similar. que acompaña a las comparsas con alegres pasos de baile.", R.drawable.huayno, "", null));
        listaDanza.add(new Danza("Qanchis", "El amor y la ofrenda nos representadas por dos jóvenes, los cuales le rinden homenaje a la Madre Tierra o Pacha Mama. ", R.drawable.inti, "", null));


    }

    public void mostrarData() {
        recyclerViewDanza.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterDanza = new AdapterDanza(getContext(),
                listaDanza) {
        };
        recyclerViewDanza.setAdapter(adapterDanza);

        adapterDanza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomdanza = listaDanza.get(recyclerViewDanza.getChildAdapterPosition(v)).getNomDanza();
                Toast.makeText(getContext(), "Selecciono: " + nomdanza, Toast.LENGTH_LONG).show();
                //enviar el objeto
                interfaceComunicaFragmentes.enviarDanza(listaDanza.get(recyclerViewDanza.getChildAdapterPosition(v)));
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
