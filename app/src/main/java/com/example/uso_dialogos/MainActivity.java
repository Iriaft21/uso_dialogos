package com.example.uso_dialogos;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity implements DialogoFragment.DialogoFragmentListener, TareaAdapter.OnItemLongClickListener {

    private ArrayList<Tarea> tareas;
    private TareaAdapter tareaAdapter;
    private RecyclerView rvTareas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tareas = new ArrayList<>();
        tareaAdapter = new TareaAdapter(tareas, this);
        rvTareas = findViewById(R.id.rvTareas);
        rvTareas.setLayoutManager(new LinearLayoutManager(this));
        rvTareas.setAdapter(tareaAdapter);

        FloatingActionButton fab = findViewById(R.id.addTarea);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialogFragment = new DialogoFragment();
                dialogFragment.show(getSupportFragmentManager(), "Dialogo");
            }
        });
    }

    @Override
    public void guardarTarea(Bundle bundle){
        Tarea tarea = bundle.getParcelable("Tarea");
        if(tarea != null){
            tareas.add(tarea);
            rvTareas.getAdapter().notifyDataSetChanged(); // Notificar la adición de la tarea
        }
        Collections.sort(tareas, Comparator.comparing(Tarea::getAsignatura));
    }

    @Override
    public void onItemLongClick(View view, int position) {
        Tarea tarea = tareas.get(position);

        BottomSheetFragment bottomSheetFragment = new BottomSheetFragment(tarea, tareas, rvTareas);
        bottomSheetFragment.show(getSupportFragmentManager(), "BottomSheet");
    }
}