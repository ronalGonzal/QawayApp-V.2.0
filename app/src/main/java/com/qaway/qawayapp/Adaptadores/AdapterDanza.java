package com.qaway.qawayapp.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.qaway.qawayapp.Entidades.Danza;
import com.qaway.qawayapp.R;

import java.util.ArrayList;

public abstract class AdapterDanza extends RecyclerView.Adapter<AdapterDanza.DanzaViewHolder> implements View.OnClickListener {

    LayoutInflater inflater;
    ArrayList<Danza> model;

    //listener
    private View.OnClickListener listener;

    public AdapterDanza(Context context, ArrayList<Danza> model) {
        this.inflater = LayoutInflater.from(context);
        this.model = model;
    }

    @NonNull
    @Override
    public AdapterDanza.DanzaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = inflater.inflate(R.layout.lista_danza, parent, false);
        vista.setOnClickListener(this);
        return new AdapterDanza.DanzaViewHolder(vista);
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;

    }


    @Override
    public void onBindViewHolder(@NonNull AdapterDanza.DanzaViewHolder holder, int position) {
        String nomdanza = model.get(position).getNomDanza();
        String desdanza = model.get(position).getDesDanza();
        int imgdanza = model.get(position).getImgDanza();

        holder.nomdanza.setText(nomdanza);
        holder.desdanza.setText(desdanza);
        holder.imgdanza.setImageResource(imgdanza);

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

    public class DanzaViewHolder extends RecyclerView.ViewHolder {
        TextView nomdanza, desdanza;
        ImageView imgdanza;

        public DanzaViewHolder(@NonNull View itemView) {
            super(itemView);
            nomdanza = itemView.findViewById(R.id.nomDanza);
            desdanza = itemView.findViewById(R.id.desDanza);
            imgdanza = itemView.findViewById(R.id.imgDanza);

        }
    }


}
