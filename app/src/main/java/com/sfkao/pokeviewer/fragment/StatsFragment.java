package com.sfkao.pokeviewer.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.sfkao.pokeviewer.R;
import com.sfkao.pokeviewer.activities.MainActivity;
import com.sfkao.pokeviewer.modelo.pojo_pokemon.Pokemon;
import com.sfkao.pokeviewer.utils.PokemonSingleton;

/**
 * Fragmento que muestra las stats de un pokemon
 */
public class StatsFragment extends Fragment {

    TextView habilidad1, habilidad2,habilidadOculta;
    TextView psText, ataqueText, defensaText, sAtaqueText, sDefensaText, velocidadText;
    ProgressBar psBar, ataqueBar, defensaBar, sAtaqueBar, sDefensaBar, velocidadBar;

    public StatsFragment() {
        // Required empty public constructor
    }

    private MainActivity context;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = (MainActivity) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stats, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        habilidad1 = requireView().findViewById(R.id.habilidad1);
        habilidad2 = requireView().findViewById(R.id.habilidad2);
        habilidadOculta = requireView().findViewById(R.id.habilidadOculta);
        psText = requireView().findViewById(R.id.psText);
        psBar = (requireView().findViewById(R.id.psProgress));
        ataqueText = (requireView().findViewById(R.id.attackText));
        ataqueBar = (requireView().findViewById(R.id.attackProgress));
        defensaText = (requireView().findViewById(R.id.defenceText));
        defensaBar = (requireView().findViewById(R.id.defenceProgress));
        sAtaqueText = (requireView().findViewById(R.id.sAtkText));
        sAtaqueBar = (requireView().findViewById(R.id.sAtkProgress));
        sDefensaText = (requireView().findViewById(R.id.sDefText));
        sDefensaBar = (requireView().findViewById(R.id.sDefProgress));
        velocidadText = (requireView().findViewById(R.id.speText));
        velocidadBar = (requireView().findViewById(R.id.speProgress));

        //La clase MutableLiveData permite almacenar un dato y que esta notifique cada vez que este cambia a todos sus
        //observadores. De esta manera no tengo que ver si un fragmento esta vivo para enviarle datos etc.
        PokemonSingleton.getPokemon2LiveData().observe(getViewLifecycleOwner(), new Observer<Pokemon>() {
            @Override
            public void onChanged(Pokemon pokemon2) {
                imprimirCaracteristicas(pokemon2);
            }
        });

        //Al iniciar si ya hay un pokemon buscado muestro sus datos
        if(PokemonSingleton.getPokemon()!=null)
            imprimirCaracteristicas(PokemonSingleton.getPokemon());

    }

    public void imprimirCaracteristicas(Pokemon pokemon){
        //Si el pokemon es nulo paro
        if(pokemon == null)
            return;
        //Coloco sus datos
        psText.setText(String.valueOf(pokemon.getStats().get(0).getBaseStat()));
        psBar.setProgress(pokemon.getStats().get(0).getBaseStat());
        ataqueText.setText(String.valueOf(pokemon.getStats().get(1).getBaseStat()));
        ataqueBar.setProgress(pokemon.getStats().get(1).getBaseStat());
        defensaText.setText(String.valueOf(pokemon.getStats().get(2).getBaseStat()));
        defensaBar.setProgress(pokemon.getStats().get(2).getBaseStat());
        sAtaqueText.setText(String.valueOf(pokemon.getStats().get(3).getBaseStat()));
        sAtaqueBar.setProgress(pokemon.getStats().get(3).getBaseStat());
        sDefensaText.setText(String.valueOf(pokemon.getStats().get(4).getBaseStat()));
        sDefensaBar.setProgress(pokemon.getStats().get(4).getBaseStat());
        velocidadText.setText(String.valueOf(pokemon.getStats().get(5).getBaseStat()));
        velocidadBar.setProgress(pokemon.getStats().get(5).getBaseStat());

        //Coloco sus habilidades
        habilidad1.setText(pokemon.getAbilities().get(0).getAbility().getName());
        if(pokemon.getAbilities().size()==1) {
            habilidadOculta.setText("");
            habilidad2.setText("");
            return;
        }
        if(!pokemon.getAbilities().get(1).getIsHidden()){
            //Si la siguiente habilidad no es oculta es que tiene una 2a
            habilidad2.setText(pokemon.getAbilities().get(1).getAbility().getName());
            habilidadOculta.setText(pokemon.getAbilities().get(2).getAbility().getName());
        }else{
            habilidad2.setText("");
            habilidadOculta.setText(pokemon.getAbilities().get(1).getAbility().getName());
        }
    }

}