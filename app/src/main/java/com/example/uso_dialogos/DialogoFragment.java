package com.example.uso_dialogos;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class DialogoFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // Construir el diálogo
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Inflar el layout del diálogo con el xml que hemos creado antes
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_dialogo, null);

        // Configurar el Spinner aquí
        Spinner spinner = view.findViewById(R.id.spinner_asignatura);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.asignaturas, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Rellenar el formulario
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        // Establece el layout para el diálogo
        builder.setView(view);

        // Establece el título
        builder.setTitle("Crear tarea");

        // Añadir botones de aceptar y cancelar
        builder.setPositiveButton(R.string.btn_guardar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                // Crear una tarea
            }
        }).setNegativeButton(R.string.btn_cancelar, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                DialogoFragment.this.getDialog().cancel();
            }
        });

        // Devuelve el diálogo
        return builder.create();
    }
}