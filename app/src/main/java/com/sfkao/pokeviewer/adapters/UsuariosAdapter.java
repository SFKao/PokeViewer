package com.sfkao.pokeviewer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.sfkao.pokeviewer.R;
import com.sfkao.pokeviewer.modelo.pojo_pokeapi_usuario.AmigoApi;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class UsuariosAdapter extends RecyclerView.Adapter {

    private static final int VIEW_ITEM = 1;
    private static final int VIEW_LOADING = 2;
    private List<AmigoApi> amigos;

    OnItemLongClickListener onItemLongClickListener;
    Context c;

    public UsuariosAdapter(ArrayList<AmigoApi> amigos, OnItemLongClickListener onItemLongClickListener, Context c) {
        this.amigos = amigos;
        this.onItemLongClickListener = onItemLongClickListener;
        this.c = c;
    }

    public UsuariosAdapter(Context c) {
        amigos = new ArrayList<>();
        this.c = c;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if( viewType == VIEW_ITEM) {
            View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_amigo, parent, false);
            return new UsuariosAdapter.ViewHolder(v);
        }else{
            View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading,parent,false);
            return new UsuariosAdapter.ViewHolderLoading(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof UsuariosAdapter.ViewHolder) {
            AmigoApi a = amigos.get(position);
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.nombreAmigo.setText(a.getUsername());
            viewHolder.likes.setText(String.valueOf(a.getLikes()));
            viewHolder.favs.setText(String.valueOf(a.getFavoritos()));
            if(a.getPk1()!=null)
                Picasso.get().load(a.getPk1().img).into(viewHolder.pk1);
            else
                viewHolder.pk1.setImageDrawable(ResourcesCompat.getDrawable(getContext().getResources(),R.drawable.pokeball,null));
            if(a.getPk2()!=null)
                Picasso.get().load(a.getPk2().img).into(viewHolder.pk2);
            else
                viewHolder.pk2.setImageDrawable(ResourcesCompat.getDrawable(getContext().getResources(),R.drawable.pokeball,null));
            if(a.getPk3()!=null)
                Picasso.get().load(a.getPk3().img).into(viewHolder.pk3);
            else
                viewHolder.pk3.setImageDrawable(ResourcesCompat.getDrawable(getContext().getResources(),R.drawable.pokeball,null));
            switch (a.getEstadoAmistad()){
                case "pendiente":
                    viewHolder.estadoAmistad.setImageDrawable(ResourcesCompat.getDrawable(getContext().getResources(), R.drawable.ic_baseline_email_24,null));
                    break;
                case "aceptada":
                    viewHolder.estadoAmistad.setImageDrawable(ResourcesCompat.getDrawable(getContext().getResources(),R.drawable.equipos,null));
                    break;
                case "recibida":
                    viewHolder.estadoAmistad.setImageDrawable(ResourcesCompat.getDrawable(getContext().getResources(),R.drawable.ic_baseline_arrow_back_24,null));
                    break;
                default:
                    viewHolder.estadoAmistad.setImageDrawable(ResourcesCompat.getDrawable(getContext().getResources(),R.drawable.bug,null));
            }
            viewHolder.view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (onItemLongClickListener != null)
                        onItemLongClickListener.onItemLongClicked(a);
                    return true;
                }
            });
        }
    }

    public void finnishedLoading(List<AmigoApi> toAdd){

        int size = amigos.size();
        amigos.remove(size -1);
        amigos.addAll(toAdd);
        notifyItemRangeChanged(size-1, toAdd.size()+1);
    }

    @Override
    public int getItemViewType(int position) {
        return amigos.get(position) != null ? VIEW_ITEM : VIEW_LOADING;
    }

    @Override
    public int getItemCount() {
        return amigos.size();
    }

    public List<AmigoApi> getAmigos() {
        return amigos;
    }

    public void setAmigos(List<AmigoApi> amigos) {
        this.amigos = amigos;
        notifyDataSetChanged();
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public interface OnItemLongClickListener{
        public boolean onItemLongClicked(AmigoApi e);
    }

    public Context getContext() {
        return c;
    }

    public void setContext(Context c) {
        this.c = c;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final ImageView estadoAmistad;
        public final TextView nombreAmigo;
        public final ImageView pk1, pk2, pk3;
        public final TextView likes, favs;

        public ViewHolder(View view) {
            super(view);
            this.view = view;

            estadoAmistad = view.findViewById(R.id.friend_status);
            nombreAmigo = view.findViewById(R.id.friend_username);
            likes = view.findViewById(R.id.friend_likes);
            favs = view.findViewById(R.id.friend_favs);

            pk1 = view.findViewById(R.id.friend_pok1);
            pk2 = view.findViewById(R.id.friend_pok2);
            pk3 = view.findViewById(R.id.friend_pok3);
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
