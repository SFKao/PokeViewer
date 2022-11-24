package com.sfkao.pokeviewer.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.sfkao.pokeviewer.R;
import com.sfkao.pokeviewer.activities.MainActivity;
import com.sfkao.pokeviewer.apis.ApiConexion;
import com.sfkao.pokeviewer.modelo.Equipo;
import com.sfkao.pokeviewer.modelo.pojo_pokemon.Pokemon;
import com.sfkao.pokeviewer.utils.EquipoSingleton;
import com.squareup.picasso.Picasso;

public class new_equipo_fragment extends DialogFragment {

    EditText nombreEquipo;
    EditText[] pokemon;
    ImageView[] pokemonImages;
    ImageButton[] pokemonSearch;

    Button aceptarButton, cancelarButton;

    Pokemon[] pokemons;
    MainActivity context;

    Equipo equipo;
    int pos;

    public new_equipo_fragment(Equipo equipo,int pos) {
        this.equipo = equipo;
        this.pos = pos;
    }

    public new_equipo_fragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_equipo_fragment, container, false);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        this.context = (MainActivity) context;
        super.onAttach(context);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View rootView = inflater.inflate(R.layout.fragment_new_equipo_fragment,null);
        nombreEquipo = rootView.findViewById(R.id.nombreEquipoInput);
        pokemon = new EditText[6];
        pokemon[0] = rootView.findViewById(R.id.pokemon1Input);
        pokemon[1] = rootView.findViewById(R.id.pokemon2Input);
        pokemon[2] = rootView.findViewById(R.id.pokemon3Input);
        pokemon[3] = rootView.findViewById(R.id.pokemon4Input);
        pokemon[4] = rootView.findViewById(R.id.pokemon5Input);
        pokemon[5] = rootView.findViewById(R.id.pokemon6Input);

        pokemonImages = new ImageView[6];
        pokemonImages[0] = rootView.findViewById(R.id.pokemon1Image);
        pokemonImages[1] = rootView.findViewById(R.id.pokemon2Image);
        pokemonImages[2] = rootView.findViewById(R.id.pokemon3Image);
        pokemonImages[3] = rootView.findViewById(R.id.pokemon4Image);
        pokemonImages[4] = rootView.findViewById(R.id.pokemon5Image);
        pokemonImages[5] = rootView.findViewById(R.id.pokemon6Image);

        pokemonSearch = new ImageButton[6];
        pokemonSearch[0] = rootView.findViewById(R.id.buscarPokemon1);
        pokemonSearch[1] = rootView.findViewById(R.id.buscarPokemon2);
        pokemonSearch[2] = rootView.findViewById(R.id.buscarPokemon3);
        pokemonSearch[3] = rootView.findViewById(R.id.buscarPokemon4);
        pokemonSearch[4] = rootView.findViewById(R.id.buscarPokemon5);
        pokemonSearch[5] = rootView.findViewById(R.id.buscarPokemon6);

        aceptarButton = rootView.findViewById(R.id.aceptarButton);
        cancelarButton = rootView.findViewById(R.id.cancelarButton);



        if(equipo!=null){
            nombreEquipo.setText(equipo.getNombre());
            pokemons = equipo.getPokemons();
            for (int i = 0; i < 6; i++) {
                Picasso.get().load(pokemons[i].getSprites().getFrontDefault()).into(pokemonImages[i]);
                pokemon[i].setText(pokemons[i].getName());
            }

        }else {
            pokemons = new Pokemon[6];
        }



        for (int i = 0; i < pokemonSearch.length; i++) {
            int finalI = i;
            pokemonSearch[i].setOnClickListener(view -> {
                Pokemon buscado = ApiConexion.getInstance().getPokemon(String.valueOf(pokemon[finalI].getText()));
                if(buscado == null){
                    pokemon[finalI].setHint(R.string.pokemonNotFound);
                    pokemon[finalI].setHintTextColor(getResources().getColor(R.color.dark_red,null));
                    pokemon[finalI].setText("");
                    pokemons[finalI] = null;
                    pokemonImages[finalI].setImageResource(R.drawable.pokeball);
                }else{
                    Picasso.get().load(buscado.getSprites().getFrontDefault()).into(pokemonImages[finalI]);
                    pokemons[finalI] = buscado;
                }
            });
        }

        cancelarButton.setOnClickListener(view -> dismiss());

        aceptarButton.setOnClickListener(view -> {

            Equipo e;
            if(equipo == null)
                e = new Equipo();
            else
                e = equipo;
            e.setNombre(String.valueOf(nombreEquipo.getText()));
            if(e.getNombre().equals("")){
                Toast.makeText(getContext(),R.string.stringRequired,Toast.LENGTH_SHORT).show();
                return;
            }
            e.setAutor(getResources().getString(R.string.invitado));
            e.setIdentificador("Local");
            e.setPokemons(pokemons);
            if(equipo==null) {
                EquipoSingleton.getEquipos().add(e);
                ((RecyclerView)(context.findViewById(R.id.recycler_mis_equipos))).getAdapter().notifyItemInserted(EquipoSingleton.getEquipos().indexOf(e));
            }else
                EquipoSingleton.getEquipos().set(pos,e);

            EquipoSingleton.guardarEquipos(context);
            dismiss();
        });

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setView(rootView);
        return alertDialogBuilder.create();
    }
}