package com.qaway.qawayapp.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.qaway.qawayapp.Entidades.Literatura;

import com.qaway.qawayapp.R;

import java.util.ArrayList;

public class AdapterLiteratura extends RecyclerView.Adapter<AdapterLiteratura.LiteraturaViewHolder> implements View.OnClickListener {

    LayoutInflater inflater;
    ArrayList<Literatura> model;

    //listener
    private View.OnClickListener listener;

    public AdapterLiteratura(Context context, ArrayList<Literatura> model) {
        this.inflater = LayoutInflater.from(context);
        this.model = model;
    }

    @NonNull
    @Override
    public AdapterLiteratura.LiteraturaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = inflater.inflate(R.layout.lista_literatura, parent, false);
        vista.setOnClickListener(this);
        return new AdapterLiteratura.LiteraturaViewHolder(vista);
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;

    }


    @Override
    public void onBindViewHolder(@NonNull AdapterLiteratura.LiteraturaViewHolder holder, int position) {

        holder.nomliteratura.setText(model.get(position).getNomLiteratura().toString());
        holder.desliteratura.setText(model.get(position).getDesLiteratura().toString());


        if (model.get(position).getImagen()!=null){
            holder.imgliteratura.setImageBitmap(model.get(position).getImagen());
        }else{
            holder.imgliteratura.setImageResource(R.drawable.img_base);
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

    public class LiteraturaViewHolder extends RecyclerView.ViewHolder {
        TextView nomliteratura, desliteratura;
        ImageView imgliteratura;

        public LiteraturaViewHolder(@NonNull View itemView) {
            super(itemView);
            nomliteratura = itemView.findViewById(R.id.nomLiteratura);
            desliteratura = itemView.findViewById(R.id.desLiteratura);
            imgliteratura = itemView.findViewById(R.id.imgLiteratura);

        }
    }

}



