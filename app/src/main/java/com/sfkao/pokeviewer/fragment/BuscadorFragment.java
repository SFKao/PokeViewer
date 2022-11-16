package com.sfkao.pokeviewer.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.sfkao.pokeviewer.R;
import com.sfkao.pokeviewer.activities.MainActivity;
import com.sfkao.pokeviewer.adapters.SearchPokemonPagerAdapter;
import com.sfkao.pokeviewer.adapters.WeaknessAdapter;
import com.sfkao.pokeviewer.apis.ApiConexion;
import com.sfkao.pokeviewer.modelo.Pokemon;
import com.sfkao.pokeviewer.utils.Util;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class BuscadorFragment extends Fragment {

    public MainActivity context;

    Button botonBuscar;
    ImageView imagePokemon;
    EditText textoPokemon;

    TextView textError;

    ImageView imageTipoIzquierda, imageTipoMedio, imageTipoDerecha;

    ViewPager2 buscadorDatos;
    SearchPokemonPagerAdapter buscadorDatosAdapter;
    TabLayout tabBuscadorDatos;

    Pokemon pokemonMostrado;

    public BuscadorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BuscadorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BuscadorFragment newInstance(String param1, String param2) {
        return new BuscadorFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        botonBuscar = (Button) context.findViewById(R.id.buttonSearchPokemon);
        imagePokemon = (ImageView) context.findViewById(R.id.imagePokemon);
        textoPokemon = (EditText) context.findViewById(R.id.textfieldPokemonNameOrNumber);

        imageTipoIzquierda = (ImageView) context.findViewById(R.id.imageTypeLeft);
        imageTipoMedio = (ImageView) context.findViewById(R.id.imageTypeMiddle);
        imageTipoDerecha = (ImageView) context.findViewById(R.id.imageTypeRight);

        textError = (TextView) context.findViewById(R.id.textError);

        tabBuscadorDatos = context.findViewById(R.id.tabMainActivity);

        buscadorDatos = context.findViewById(R.id.datosPokemonPager);
        buscadorDatosAdapter = new SearchPokemonPagerAdapter(context);
        buscadorDatos.setAdapter(buscadorDatosAdapter);
        new TabLayoutMediator(tabBuscadorDatos,buscadorDatos,true,true,(tab, position) -> tab.setText(null)).attach();

    }


    @Override
    public void onStart() {
        super.onStart();
        botonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscar();
            }
        });

        textoPokemon.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                buscar();
                return false;
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_buscador, container, false);
    }


    private void buscar(){
        ApiConexion.getInstance().buscarPokemonYMostrar(String.valueOf(textoPokemon.getText()));
    }

    public void imprimirPokemon(Pokemon pokemon){

        //GuardarImagen en local y llamar a que se imprima al crear el fragment. Dividir esto en metodos

        pokemonMostrado = pokemon;

        Picasso.get().load(pokemon.getUrlFoto()).into(imagePokemon);
        if(pokemon.getTipo2()==null){
            imageTipoMedio.setImageDrawable(Util.getType(pokemon.getTipo1()));
            imageTipoDerecha.setVisibility(View.INVISIBLE);
            imageTipoIzquierda.setVisibility(View.INVISIBLE);
            imageTipoMedio.setVisibility(View.VISIBLE);
        }else{
            imageTipoIzquierda.setImageDrawable(Util.getType(pokemon.getTipo1()));
            imageTipoDerecha.setImageDrawable(Util.getType(pokemon.getTipo2()));
            imageTipoDerecha.setVisibility(View.VISIBLE);
            imageTipoIzquierda.setVisibility(View.VISIBLE);
            imageTipoMedio.setVisibility(View.INVISIBLE);
        }
    }



}