package com.example.listas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listas.MiViewHolder;
import com.example.listas.R;

import java.util.ArrayList;

public class MiAdaptador extends RecyclerView.Adapter<MiViewHolder> {

    private ArrayList<String> listaNombres;
    public MiAdaptador(ArrayList<String> dataSet) {

        this.listaNombres = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MiViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.text_row_item, viewGroup, false);

        return new MiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MiViewHolder viewHolder, final int position) {
        viewHolder.getTextView().setText(listaNombres.get(position));
    }

    @Override
    public int getItemCount() {
        return listaNombres.size();
    }

    public void agregarNombre(String nombre){
        listaNombres.add(nombre);
        notifyDataSetChanged(); //para que el recyclerView se actualice
    }
}