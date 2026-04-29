package com.example.clase12;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AsistenciasFragment extends Fragment {
    private RecyclerView rv;
    private AppDataBase db;
    private int alumnoId;

    public AsistenciasFragment() { super(R.layout.fragment_asistencias); }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) alumnoId = getArguments().getInt("alumnoId");

        rv = view.findViewById(R.id.rvAsistencia);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        db = AppDataBase.getInstance(getContext());

        cargarAsistencia();
    }


    private void cargarAsistencia() {
        new Thread(() -> {
            List<Asistencia> lista = db.asistenciaDao().getAsistenciaPorAlumno(alumnoId);

            if (lista.isEmpty()) {
                // Solo insertamos fecha
                db.asistenciaDao().insertAsistencia(new Asistencia(alumnoId, "Viernes 01 de Mayo"));
                db.asistenciaDao().insertAsistencia(new Asistencia(alumnoId, "Sábado 02 de Mayo"));
                db.asistenciaDao().insertAsistencia(new Asistencia(alumnoId, "Domingo 03 de Mayo"));
                lista = db.asistenciaDao().getAsistenciaPorAlumno(alumnoId);
            }

            List<Asistencia> listaFinal = lista;
            getActivity().runOnUiThread(() -> {
                rv.setAdapter(new AsistenciaAdapter(listaFinal));
            });
        }).start();
    }
}

