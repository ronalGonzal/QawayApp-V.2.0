package com.qaway.qawayapp.Adaptadores;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.qaway.qawayapp.Entidades.Artesania;
import com.qaway.qawayapp.R;

import java.util.ArrayList;


public class AdapterArtesania extends RecyclerView.Adapter<AdapterArtesania.ArtesaniaViewHolder> implements View.OnClickListener {

    LayoutInflater inflater;
    ArrayList<Artesania> model;

    //listener
    private View.OnClickListener listener;

    public AdapterArtesania(Context context, ArrayList<Artesania> model) {
        this.inflater = LayoutInflater.from(context);
        this.model = model;
    }

    @NonNull
    @Override
    public AdapterArtesania.ArtesaniaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = inflater.inflate(R.layout.lista_artesania, parent, false);
        vista.setOnClickListener(this);
        return new AdapterArtesania.ArtesaniaViewHolder(vista);
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;

    }


    @Override
    public void onBindViewHolder(@NonNull AdapterArtesania.ArtesaniaViewHolder holder, int position) {
        String nomartesano = model.get(position).getNomArtesano();
        String desartesano = model.get(position).getDesArtesano();
        int imgartesano = model.get(position).getImgArtesano();

        holder.nomartesano.setText(nomartesano);
        holder.desartesano.setText(desartesano);
        holder.imgartesano.setImageResource(imgartesano);

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

    public class ArtesaniaViewHolder extends RecyclerView.ViewHolder {
        TextView nomartesano, desartesano;
        ImageView imgartesano;

        public ArtesaniaViewHolder(@NonNull View itemView) {
            super(itemView);
            nomartesano = itemView.findViewById(R.id.nomArtesano);
            desartesano = itemView.findViewById(R.id.desArtesano);
            imgartesano = itemView.findViewById(R.id.imgArtesano);

        }
    }

}

