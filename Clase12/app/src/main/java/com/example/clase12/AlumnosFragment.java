package com.example.clase12;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AlumnosFragment extends Fragment {
    private RecyclerView rv;
    private AppDataBase db;
    private int cursoId;

    public AlumnosFragment() { super(R.layout.fragment_alumnos); }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            cursoId = getArguments().getInt("cursoId");
        }

        rv = view.findViewById(R.id.rvAlumnos);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        db = AppDataBase.getInstance(getContext());

        cargarAlumnos();
    }

    private void cargarAlumnos() {
        new Thread(() -> {
            List<Alumnos> lista = db.alumnosDao().getAlumnosPorCurso(cursoId);
            if (lista.isEmpty()) {
                db.alumnosDao().insertAlumnos(new Alumnos("Aly", cursoId, "García", "López"));
                db.alumnosDao().insertAlumnos(new Alumnos("Juan", cursoId, "Pérez", "Rodríguez"));
                db.alumnosDao().insertAlumnos(new Alumnos("María", cursoId, "Sánchez", "Gómez"));

                lista = db.alumnosDao().getAlumnosPorCurso(cursoId);
            }

            List<Alumnos> listaFinal = lista;
            getActivity().runOnUiThread(() -> {
                rv.setAdapter(new AlumnosAdapter(listaFinal, alumno -> {
                    abrirAsistencia(alumno.alumnoId);
                }));
            });
        }).start();
    }

    private void abrirAsistencia(int alumnoId) {
        AsistenciasFragment asisFrag = new AsistenciasFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("alumnoId", alumnoId);
        asisFrag.setArguments(bundle);

        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView, asisFrag)
                .addToBackStack(null)
                .commit();
    }
}