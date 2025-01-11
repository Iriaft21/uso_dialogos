package com.example.uso_dialogos;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

public class DialogoFragment extends DialogFragment {

    private String asignatura;
    private String descripcion;
    private DialogoFragmentListener listener;
    private RecyclerView rvTareas;
    private Tarea tarea;
    private ArrayList<Tarea> tareas;

    public interface DialogoFragmentListener {
        void guardarTarea(Bundle bundle);
    }

    public DialogoFragment(){

    }

    public DialogoFragment(Tarea tarea, RecyclerView rvTareas, ArrayList<Tarea> tareas){
        this.tarea = tarea;
        this.tareas = tareas;
        this.rvTareas = rvTareas;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_dialogo, null);

        Spinner spinner = view.findViewById(R.id.spinner_asignatura);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.asignaturas, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                asignatura = spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        TextView txt_descripcion = view.findViewById(R.id.txt_descripcion);
        TextView fechaEntrega_txt = view.findViewById(R.id.txt_entrega);
        TextView horaEntrega_txt = view.findViewById(R.id.txt_horaentrega);

        if(tarea != null){
            txt_descripcion.setText(tarea.getDescripcion());
            fechaEntrega_txt.setText(tarea.getFechaEntrega());
            horaEntrega_txt.setText(tarea.getHoraEntrega());

            int posicionSpinner = adapter.getPosition(tarea.getAsignatura());
            spinner.setSelection(posicionSpinner);
        }
        fechaEntrega_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //fecha y hora
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                String fecha= dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                                fechaEntrega_txt.setText(fecha);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

        horaEntrega_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int hora = c.get(Calendar.HOUR_OF_DAY);
                int minuto = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                String hora = hourOfDay + ":" + minute;
                                horaEntrega_txt.setText(hora);
                            }
                        }, hora, minuto, false);
                timePickerDialog.show();
            }
        });

        builder.setView(view);
        builder.setTitle(tarea != null? "Modificar tarea": "Crear tarea");

        Button btn_cancelar = view.findViewById(R.id.btn_cancelar);
        btn_cancelar.setOnClickListener(view1 -> getDialog().dismiss());

        Button btn_guardar = view.findViewById(R.id.btn_guardar);
        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                descripcion = txt_descripcion.getText().toString();
                if(tarea!= null){
                    tarea.setAsignatura(asignatura);
                    tarea.setFechaEntrega(fechaEntrega_txt.getText().toString());
                    tarea.setDescripcion(descripcion);
                    tarea.setHoraEntrega(horaEntrega_txt.getText().toString());
                    rvTareas.getAdapter().notifyDataSetChanged();
                    Collections.sort(tareas, Comparator.comparing(Tarea::getAsignatura));
                } else{
                    Tarea tarea = new Tarea(asignatura, fechaEntrega_txt.getText().toString(),descripcion ,horaEntrega_txt.getText().toString(), "Pendiente");
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("Tarea", tarea);
                    listener.guardarTarea(bundle);
                }
                dismiss();
            }
        });

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (DialogoFragmentListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context + " debe implementar DialogoFragmentListener");
        }
    }
}