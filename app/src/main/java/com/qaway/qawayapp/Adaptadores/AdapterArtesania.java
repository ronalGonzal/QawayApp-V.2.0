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

        holder.nomartesano.setText(model.get(position).getNomArtesano().toString());
        holder.desartesano.setText(model.get(position).getDesArtesano().toString());

        if (model.get(position).getImagen()!=null){
            holder.imgartesano.setImageBitmap(model.get(position).getImagen());
        }else{
            holder.imgartesano.setImageResource(R.drawable.img_base);
        }

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

