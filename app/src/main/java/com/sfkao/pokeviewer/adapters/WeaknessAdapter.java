package com.sfkao.pokeviewer.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sfkao.pokeviewer.R;
import com.sfkao.pokeviewer.utils.Util;

import java.util.ArrayList;

public class WeaknessAdapter extends RecyclerView.Adapter {

    private ArrayList<String> tipos;

    public WeaknessAdapter(ArrayList<String> tipos) {
        this.tipos = tipos;
    }

    public WeaknessAdapter() {
        tipos = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weakness, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ImageView image = ((ViewHolder) holder).image;
        image.setImageDrawable(Util.getType(tipos.get(position)));
    }


    @Override
    public int getItemCount() {
        return tipos.size();
    }

    public ArrayList<String> getTipos() {
        return tipos;
    }

    public void setTipos(ArrayList<String> tipos) {
        this.tipos = tipos;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final ImageView image;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            image = view.findViewById(R.id.imageWeakness);
        }
    }

}
