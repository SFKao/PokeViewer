package com.sfkao.pokeviewer.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sfkao.pokeviewer.R;
import com.sfkao.pokeviewer.modelo.Equipo;
import com.squareup.picasso.Picasso;

import java.text.MessageFormat;
import java.util.ArrayList;

/**
 * Adaptador que se encarga de mostrar los equipos en un recycler view
 */
public class EquipoAdapter extends RecyclerView.Adapter {

    private static final int VIEW_ITEM = 1;
    private static final int VIEW_LOADING = 2;
    private ArrayList<Equipo> equipos;

    public EquipoAdapter(ArrayList<Equipo> equipos) {
        this.equipos = equipos;
    }

    public EquipoAdapter() {
        equipos = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if( viewType == VIEW_ITEM) {
            View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_equipo, parent, false);
            return new ViewHolder(v);
        }else{
            View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading,parent,false);
            return new ViewHolderLoading(v);
        }
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //Coloco los datos del equipo en el holder.
        if(holder instanceof ViewHolder) {
            Equipo e = equipos.get(position);
            ((ViewHolder) holder).nombre.setText(e.getNombre());
            ((ViewHolder) holder).autor.setText(MessageFormat.format("{0}{1}", ((ViewHolder) holder).view.getResources().getString(R.string.autor), e.getAutor()));
            ((ViewHolder) holder).codigo.setText(MessageFormat.format("{0}{1}", ((ViewHolder) holder).view.getResources().getString(R.string.id), e.getIdentificador()));
            ((ViewHolder) holder).likes.setText(String.valueOf(e.getLikes()));
            ((ViewHolder) holder).favoritos.setText(String.valueOf(e.getFavoritos()));
            //Coloco las 6 imagenes de los pokemon
            for (int i = 0; i < ((ViewHolder) holder).pokemons.length; i++) {
                if (e.getPokemon(i) != null) {
                    Picasso.get().load(e.getPokemon(i).getSprites().getFrontDefault()).into(((ViewHolder) holder).pokemons[i]);
                }
            }
        }else if (holder instanceof ViewHolderLoading){

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
        //Si cambio los equipos desde fuera, notifico de que se han cambiado y se ha de redibujar
        notifyDataSetChanged();
    }

    public void addLoading() {
        equipos.add(null);
        notifyItemInserted(equipos.size()-1);
    }

    @Override
    public int getItemViewType(int position) {
        return equipos.get(position) != null ? VIEW_ITEM : VIEW_LOADING;
    }

    /**
     * Clase que muestra los datos del equipo
     */
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
    
    public static class ViewHolderLoading extends RecyclerView.ViewHolder {
        public final View view;
        public final ProgressBar progressBar;
        
        public ViewHolderLoading(View view){
            super(view);
            this.view = view;
            progressBar = view.findViewById(R.id.loading_bar);
        }
    }

}
