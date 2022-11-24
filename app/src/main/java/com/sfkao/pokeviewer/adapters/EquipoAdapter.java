package com.sfkao.pokeviewer.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sfkao.pokeviewer.R;
import com.sfkao.pokeviewer.modelo.Equipo;
import com.squareup.picasso.Picasso;

import java.text.MessageFormat;
import java.util.ArrayList;

public class EquipoAdapter extends RecyclerView.Adapter {

    private ArrayList<Equipo> equipos;

    public EquipoAdapter(ArrayList<Equipo> equipos) {
        this.equipos = equipos;
    }

    public EquipoAdapter() {
        equipos = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_equipo, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Equipo e = equipos.get(position);
        ((ViewHolder)holder).nombre.setText(e.getNombre());
        ((ViewHolder)holder).autor.setText(MessageFormat.format("{0}{1}", ((ViewHolder) holder).view.getResources().getString(R.string.autor), e.getAutor()));
        ((ViewHolder)holder).codigo.setText(MessageFormat.format("{0}{1}", ((ViewHolder) holder).view.getResources().getString(R.string.id), e.getIdentificador()));
        ((ViewHolder)holder).likes.setText(String.valueOf(e.getLikes()));
        ((ViewHolder)holder).favoritos.setText(String.valueOf(e.getFavoritos()));

        for(int i = 0 ;i<((ViewHolder)holder).pokemons.length;i++ ){
            if(e.getPokemon(i)!=null){
                Picasso.get().load(e.getPokemon(i).getSprites().getFrontDefault()).into(((ViewHolder)holder).pokemons[i]);
            }
        }
    }


    @Override
    public int getItemCount() {
        return equipos.size();
    }

    public ArrayList<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(ArrayList<Equipo> equipos) {
        this.equipos = equipos;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final ImageView[] pokemons;
        public final TextView nombre, autor, codigo, likes, favoritos;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            pokemons = new ImageView[6];

            pokemons[0] = view.findViewById(R.id.imagenPokemon1);
            pokemons[1] = view.findViewById(R.id.imagenPokemon2);
            pokemons[2] = view.findViewById(R.id.imagenPokemon3);
            pokemons[3] = view.findViewById(R.id.imagenPokemon4);
            pokemons[4] = view.findViewById(R.id.imagenPokemon5);
            pokemons[5] = view.findViewById(R.id.imagenPokemon6);

            nombre = view.findViewById(R.id.nombre_equipo);
            autor = view.findViewById(R.id.autor_equipo);
            codigo = view.findViewById(R.id.codigo_equipo);
            likes = view.findViewById(R.id.likes_equipo);
            favoritos = view.findViewById(R.id.favoritos_equipo);
        }
    }

}
