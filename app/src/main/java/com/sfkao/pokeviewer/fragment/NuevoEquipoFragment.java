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
import com.sfkao.pokeviewer.adapters.EquipoAdapter;
import com.sfkao.pokeviewer.apis.ApiConexion;
import com.sfkao.pokeviewer.modelo.EquipoForAdapterInterface;
import com.sfkao.pokeviewer.modelo.pojo_pokemon.Pokemon;
import com.sfkao.pokeviewer.realm.EquipoRealm;
import com.sfkao.pokeviewer.realm.EquipoRealmOperaciones;
import com.sfkao.pokeviewer.utils.Login;
import com.squareup.picasso.Picasso;

/**
 * Fragmento de dialogo que permite crear y editar un equipo dependiendo del constructor utilizado
 */
public class NuevoEquipoFragment extends DialogFragment {

    EditText nombreEquipo;
    EditText[] pokemonText;
    ImageView[] pokemonImages;
    ImageButton[] pokemonSearch;

    Button aceptarButton, cancelarButton;
    MainActivity context;

    EquipoRealm equipo;
    int pos = -1;

    //Si se envia un equipo lo almacenara y lo usara para editarlo.

    public NuevoEquipoFragment(EquipoForAdapterInterface equipo,int pos) {
        if(equipo instanceof EquipoRealm)
            this.equipo = (EquipoRealm) equipo;
        else
            this.equipo = new EquipoRealm(equipo);
        this.pos = pos;
    }

    //Si se envia sin equipo se inicia en modo insercion
    public NuevoEquipoFragment() {
        equipo = null;
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
        //Obtengo todos los elementos
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View rootView = inflater.inflate(R.layout.fragment_new_equipo_fragment,null);
        nombreEquipo = rootView.findViewById(R.id.nombreEquipoInput);
        pokemonText = new EditText[6];
        pokemonText[0] = rootView.findViewById(R.id.pokemon1Input);
        pokemonText[1] = rootView.findViewById(R.id.pokemon2Input);
        pokemonText[2] = rootView.findViewById(R.id.pokemon3Input);
        pokemonText[3] = rootView.findViewById(R.id.pokemon4Input);
        pokemonText[4] = rootView.findViewById(R.id.pokemon5Input);
        pokemonText[5] = rootView.findViewById(R.id.pokemon6Input);

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
            equipo = new EquipoRealm(this.equipo);
            nombreEquipo.setText(equipo.getName());
            for (int i = 0; i < 6; i++) {
                if(equipo.isPokemon(i)) {
                    Picasso.get().load(equipo.getPokImg(i)).into(pokemonImages[i]);
                    pokemonText[i].setText(equipo.getPokName(i));
                }
            }
        }else {
            equipo = new EquipoRealm();
        }

        //Añado un listener a cada boton de pokemon, estan colocados en fila
        for (int i = 0; i < pokemonSearch.length; i++) {
            int finalI = i;
            pokemonSearch[i].setOnClickListener(view -> {
                if(pokemonText[finalI].getText().toString().equals(""))
                    return;
                Pokemon buscado = ApiConexion.getInstance().getPokemon(String.valueOf(pokemonText[finalI].getText()));
                //Si el pokemon no existe
                if(buscado == null){
                    pokemonText[finalI].setHint(R.string.pokemonNotFound);
                    pokemonText[finalI].setHintTextColor(getResources().getColor(R.color.dark_red,null));
                    pokemonText[finalI].setText("");
                    pokemonImages[finalI].setImageResource(R.drawable.pokeball);
                }else{
                    pokemonText[finalI].setHint(R.string.pokemonOrNumber);
                    pokemonText[finalI].setHintTextColor(getResources().getColor(R.color.gray,null));
                    Picasso.get().load(buscado.getSprites().getFrontDefault()).into(pokemonImages[finalI]);
                    equipo.setPokemon(finalI, buscado.getId(), buscado.getName(), buscado.getSprites().getFrontDefault(),buscado.getSprites().getFrontShiny());
                }
            });
        }

        //El boton de cancelar no almacenara nada
        cancelarButton.setOnClickListener(view -> dismiss());

        aceptarButton.setOnClickListener(view -> {

            //Si no hay un equipo para editar, croe uno nuevo, si no pongo el de editar

            //El nombre es obligatorio
            if(nombreEquipo.getText().toString().equals("")){
                Toast.makeText(getContext(),R.string.stringRequired,Toast.LENGTH_SHORT).show();
                return;
            }

            //Coloco el nuevo nombre, autor a partir del usuario y local ya que este no se ha subido
            equipo.setName(nombreEquipo.getText().toString());
            equipo.setUser(Login.getUsername());
            equipo.setApiID("Local");

            //Si estoy en modo añadir
            if(pos==-1) {
                EquipoAdapter adapter = (EquipoAdapter) ((RecyclerView) (context.findViewById(R.id.recycler_mis_equipos))).getAdapter();
                adapter.getEquipos().add(equipo);
                adapter.notifyItemInserted(adapter.getEquipos().size()-1);
            //Si estoy en modo editar
            }else {
                EquipoAdapter adapter = (EquipoAdapter) ((RecyclerView) (context.findViewById(R.id.recycler_mis_equipos))).getAdapter();
                adapter.getEquipos().set(pos,equipo);
                adapter.notifyItemChanged(pos);
            }
            //Guardo los cambios y salgo
            EquipoRealmOperaciones.insertaOActualiza(equipo);
            dismiss();
        });

        //Creo el dialog fragment
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setView(rootView);
        return alertDialogBuilder.create();
    }
}