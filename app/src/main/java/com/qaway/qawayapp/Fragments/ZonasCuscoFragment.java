package com.qaway.qawayapp.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.qaway.qawayapp.Entidades.Zonas;
import com.qaway.qawayapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ZonasCuscoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ZonasCuscoFragment extends Fragment implements OnMapReadyCallback, Response.Listener<JSONObject>, Response.ErrorListener  {

    GoogleMap mapita;
    MapView mapView;


    ArrayList<Zonas> listaZonas;

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

    public ZonasCuscoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ZonasCuscoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ZonasCuscoFragment newInstance(String param1, String param2) {
        ZonasCuscoFragment fragment = new ZonasCuscoFragment();
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

        View vista = inflater.inflate(R.layout.fragment_zonas_cusco, container, false);

        listaZonas = new ArrayList<>();
        request = Volley.newRequestQueue(getContext());
        cargarWebservices();
        return vista;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        mapView = view.findViewById(R.id.zonasCusco);

        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        MapsInitializer.initialize(getContext());
        mapita = googleMap;



        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.setTrafficEnabled(false);

        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        //Toast.makeText(getContext(), listaZonas.get(0).getTituloZona(), Toast.LENGTH_LONG).show();
        try {

            for (int i = 0; i < listaZonas.size(); i++) {
                Zonas zona = new Zonas();
                zona=listaZonas.get(i);

                if(zona.getTipoZona().equals("plaza")){
                    googleMap.addMarker(new MarkerOptions()
                            .position(new LatLng( Double.parseDouble(zona.getLatitud().toString()), Double.parseDouble(zona.getLongitud().toString())))
                            .title(zona.getTituloZona().toString())
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                }
                if(zona.getTipoZona().equals("iglesia")){
                    googleMap.addMarker(new MarkerOptions()
                            .position(new LatLng( Double.parseDouble(zona.getLatitud().toString()), Double.parseDouble(zona.getLongitud().toString())))
                            .title(zona.getTituloZona().toString())
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
                }
                if(zona.getTipoZona().equals("museo")){
                    googleMap.addMarker(new MarkerOptions()
                            .position(new LatLng( Double.parseDouble(zona.getLatitud().toString()), Double.parseDouble(zona.getLongitud().toString())))
                            .title(zona.getTituloZona().toString())
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            progress.hide();
        }


        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-13.5192777,-71.9779604), 15));

    }

    private void cargarWebservices() {
        progress = new ProgressDialog(getContext());
        progress.setMessage("Consultando...");
        progress.show();
        //Toast.makeText(getContext(), "comienz acarga webservices" , Toast.LENGTH_LONG).show();
        String url = "http://tallerpro-001-site1.btempurl.com/wsJSONConsultarZonas.php";
        //Toast.makeText(getContext(), "paso lectura de webservies" , Toast.LENGTH_LONG).show();
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
        Zonas zona = null;

        JSONArray json = response.optJSONArray("Zonas");

        try {
            for (int i = 0; i < json.length(); i++) {
                zona = new Zonas();
                JSONObject jsonObject = null;
                jsonObject = json.getJSONObject(i);

                zona.setIdZona(jsonObject.optInt("idZona"));
                zona.setTituloZona(jsonObject.optString("tituloZona"));
                zona.setLatitud(jsonObject.optString("latitud"));
                zona.setLongitud(jsonObject.optString("longitud"));
                zona.setTipoZona(jsonObject.optString("tipoZona"));
                //Toast.makeText(getContext(), zona.getTituloZona(), Toast.LENGTH_LONG).show();

                listaZonas.add(zona);
            }
            progress.hide();



        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "No se a podido establecer conexcion con el servidor " + response, Toast.LENGTH_LONG).show();
            progress.hide();
        }


    }
}