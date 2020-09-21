package com.qaway.qawayapp.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.qaway.qawayapp.Entidades.Patrimonio;
import com.qaway.qawayapp.R;

import java.util.ArrayList;

public class AdapterPatrimonio extends RecyclerView.Adapter<AdapterPatrimonio.PatrimonioViewHolder> implements View.OnClickListener {

    LayoutInflater inflater;
    ArrayList<Patrimonio> model;

    //listener
    private View.OnClickListener listener;

    public AdapterPatrimonio(Context context, ArrayList<Patrimonio> model) {
        this.inflater = LayoutInflater.from(context);
        this.model = model;
    }

    @NonNull
    @Override
    public AdapterPatrimonio.PatrimonioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = inflater.inflate(R.layout.lista_patrimonios, parent, false);
        vista.setOnClickListener(this);
        return new PatrimonioViewHolder(vista);
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;

    }


    @Override
    public void onBindViewHolder(@NonNull PatrimonioViewHolder holder, int position) {


        holder.nompatrimonio.setText(model.get(position).getNomPatrimonio().toString());
        holder.despatrimonio.setText(model.get(position).getDesPatrimonio().toString());


        if (model.get(position).getImagen()!=null){
            holder.imgpatrimonio.setImageBitmap(model.get(position).getImagen());
        }else{
            holder.imgpatrimonio.setImageResource(R.drawable.img_base);
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

    public class PatrimonioViewHolder extends RecyclerView.ViewHolder {
        TextView nompatrimonio, despatrimonio;
        ImageView imgpatrimonio;

        public PatrimonioViewHolder(@NonNull View itemView) {
            super(itemView);
            nompatrimonio = itemView.findViewById(R.id.nomPatrimonio);
            despatrimonio = itemView.findViewById(R.id.desPatrimonio);
            imgpatrimonio = itemView.findViewById(R.id.imgPatrimonio);

        }
    }

}
