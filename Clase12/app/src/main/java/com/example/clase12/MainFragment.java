package com.example.clase12;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MainFragment extends Fragment {
    private RecyclerView rv;
    private AppDataBase db;

    public MainFragment() { super(R.layout.fragment_main); }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv = view.findViewById(R.id.rvCursos);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        db = AppDataBase.getInstance(getContext());

        cargarDatos();
    }

    private void cargarDatos() {
        new Thread(() -> {
            List<Cursos> existentes = db.cursosDao().getAllCursos();
            if (existentes.isEmpty()) {
                db.cursosDao().insertCursos(new Cursos("Telecomunicaciones"));
                db.cursosDao().insertCursos(new Cursos("Ciberseguridad"));
                db.cursosDao().insertCursos(new Cursos("Finanzas"));
                existentes = db.cursosDao().getAllCursos();
            }

            List<Cursos> listaFinal = existentes;

            getActivity().runOnUiThread(() -> {
                CursosAdapter adapter = new CursosAdapter(listaFinal, curso -> {
                    abrirAlumnos(curso.id);
                });
                rv.setAdapter(adapter);
            });
        }).start();
    }

    private void abrirAlumnos(int cursoId) {
        Fragment alumnosFrag = new AlumnosFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("cursoId", cursoId);
        alumnosFrag.setArguments(bundle);

        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView, alumnosFrag)
                .addToBackStack(null)
                .commit();
    }
}