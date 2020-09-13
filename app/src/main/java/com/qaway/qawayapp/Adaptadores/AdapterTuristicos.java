package com.qaway.qawayapp.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.qaway.qawayapp.Entidades.Turisticos;
import com.qaway.qawayapp.R;

import java.util.ArrayList;

public class AdapterTuristicos extends RecyclerView.Adapter<AdapterTuristicos.TuristicosViewHolder> implements View.OnClickListener {

    LayoutInflater inflater;
    ArrayList<Turisticos> model;

    //listener
    private View.OnClickListener listener;

    public AdapterTuristicos(Context context, ArrayList<Turisticos> model) {
        this.inflater = LayoutInflater.from(context);
        this.model = model;
    }

    @NonNull
    @Override
    public AdapterTuristicos.TuristicosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = inflater.inflate(R.layout.lista_turisticos, parent, false);
        vista.setOnClickListener(this);
        return new AdapterTuristicos.TuristicosViewHolder(vista);
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;

    }


    @Override
    public void onBindViewHolder(@NonNull AdapterTuristicos.TuristicosViewHolder holder, int position) {
        String nomturisticos = model.get(position).getNomTuristicos();
        String desturisticos = model.get(position).getDesTuristicos();
        int imgturisticos = model.get(position).getImgTuristicos();

        holder.nomturisticos.setText(nomturisticos);
        holder.desturisticos.setText(desturisticos);
        holder.imgturisticos.setImageResource(imgturisticos);

    }


    @Override
    public int getItemCount() {
        return model.size();
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onClick(v);
        }

    }

    public class TuristicosViewHolder extends RecyclerView.ViewHolder {
        TextView nomturisticos, desturisticos;
        ImageView imgturisticos;

        public TuristicosViewHolder(@NonNull View itemView) {
            super(itemView);
            nomturisticos = itemView.findViewById(R.id.nomTuristicos);
            desturisticos = itemView.findViewById(R.id.desTuristicos);
            imgturisticos = itemView.findViewById(R.id.imgTuristicos);

        }
    }


}

