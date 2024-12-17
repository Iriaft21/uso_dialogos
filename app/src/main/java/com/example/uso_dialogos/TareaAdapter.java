package com.example.uso_dialogos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TareaAdapter extends RecyclerView.Adapter<TareaAdapter.TareaViewHolder> {

    private ArrayList<Tarea> coleccion;

    public TareaAdapter(ArrayList<Tarea> coleccion) {
        this.coleccion = coleccion;
    }

    @NonNull
    @Override
    public TareaAdapter.TareaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TareaAdapter.TareaViewHolder cartaViewHolder =
                new TareaViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.tarea, parent, false)
                );
        return cartaViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TareaAdapter.TareaViewHolder holder, int position) {
        Tarea tarea = coleccion.get(position);
        holder.asignatura.setText(tarea.getAsignatura());
        holder.descripcion.setText(tarea.getDescripcion());
        String entrega = tarea.getFechaEntrega() + " " + tarea.getHoraEntrega();
        holder.entrega.setText(entrega);
        holder.estado.setText(tarea.getEstado());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class TareaViewHolder extends RecyclerView.ViewHolder{
        TextView asignatura;
        TextView descripcion;
        TextView entrega;
        TextView estado;

        public TareaViewHolder(@NonNull View itemView) {
            super(itemView);
            asignatura = itemView.findViewById(R.id.asigantura);
            descripcion = itemView.findViewById(R.id.descripcion);
            entrega = itemView.findViewById(R.id.entrega);
            estado = itemView.findViewById(R.id.estado);
        }
    }
}
