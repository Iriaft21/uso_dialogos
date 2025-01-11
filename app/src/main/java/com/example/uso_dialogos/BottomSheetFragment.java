package com.example.uso_dialogos;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class BottomSheetFragment  extends BottomSheetDialogFragment {

    private Tarea tarea;
    private ArrayList<Tarea> tareas;
    private RecyclerView rvTareas;

    public BottomSheetFragment(Tarea tarea, ArrayList<Tarea> tareas, RecyclerView rvTareas){
        this.tarea = tarea;
        this.tareas = tareas;
        this.rvTareas = rvTareas;
    }

    @NonNull
    @Override
    public BottomSheetDialog onCreateDialog(@Nullable Bundle savedInstanceState){
        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.bottom_sheet_layout, null);
        bottomSheetDialog.setContentView(view);

        LinearLayout modificar = view.findViewById(R.id.modificarTarea);
        LinearLayout completado = view.findViewById(R.id.marcarCompletado);
        LinearLayout borrar = view.findViewById(R.id.eliminarTarea);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Marcar como completada");
        builder.setMessage("Deseas marcar la tarea seleccionada como completada?");

        AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());
        builder2.setTitle("Eliminar tarea");
        builder2.setMessage("Deseas eliminar la tarea seleccionada de la lista?");
        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialogFragment = new DialogoFragment(tarea, rvTareas, tareas);
                dialogFragment.show(requireActivity().getSupportFragmentManager(), "Dialogo");
                dismiss();
            }
        });

        completado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        tarea.setEstado("Completado");
                        rvTareas.getAdapter().notifyDataSetChanged();
                        dismiss();
                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dismiss();
                    }
                });
                builder.show();
            }
        });

        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder2.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        tareas.remove(tarea);
                        rvTareas.getAdapter().notifyDataSetChanged();
                        dismiss();
                    }
                });
                builder2.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dismiss();
                    }
                });
                builder2.show();
            }
        });

        return bottomSheetDialog;
    }
}
