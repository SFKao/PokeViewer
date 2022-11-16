package com.sfkao.pokeviewer.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sfkao.pokeviewer.R;
import com.sfkao.pokeviewer.activities.MainActivity;
import com.sfkao.pokeviewer.modelo.Pokemon;

/**
 * A simple {@link Fragment} subclass.
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
        habilidad1 = this.context.findViewById(R.id.habilidad1);
        habilidad2 = this.context.findViewById(R.id.habilidad2);
        habilidadOculta = this.context.findViewById(R.id.habilidadOculta);
        psText = this.context.findViewById(R.id.psText);
        psBar = (this.context.findViewById(R.id.psProgress));
        ataqueText = (this.context.findViewById(R.id.attackText));
        ataqueBar = (this.context.findViewById(R.id.attackProgress));
        defensaText = (this.context.findViewById(R.id.defenceText));
        defensaBar = (this.context.findViewById(R.id.defenceProgress));
        sAtaqueText = (this.context.findViewById(R.id.sAtkText));
        sAtaqueBar = (this.context.findViewById(R.id.sAtkProgress));
        sDefensaText = (this.context.findViewById(R.id.sDefText));
        sDefensaBar = (this.context.findViewById(R.id.sDefProgress));
        velocidadText = (this.context.findViewById(R.id.speText));
        velocidadBar = (this.context.findViewById(R.id.speProgress));
    }


    public void imprimirCaracteristicas(Pokemon pokemon){
        if(pokemon == null)
            return;
        if(psText==null)
            return;
        psText.setText(String.valueOf(pokemon.getPs()));
        psBar.setProgress(pokemon.getPs());
        ataqueText.setText(String.valueOf(pokemon.getAtk()));
        ataqueBar.setProgress(pokemon.getAtk());
        defensaText.setText(String.valueOf(pokemon.getDef()));
        defensaBar.setProgress(pokemon.getDef());
        sAtaqueText.setText(String.valueOf(pokemon.getsAtk()));
        sAtaqueBar.setProgress(pokemon.getsAtk());
        sDefensaText.setText(String.valueOf(pokemon.getsDef()));
        sDefensaBar.setProgress(pokemon.getsDef());
        velocidadText.setText(String.valueOf(pokemon.getSpe()));
        velocidadBar.setProgress(pokemon.getSpe());

        habilidad1.setText(pokemon.getHabilidad1());
        if(pokemon.getHabilidad2()==null)
            habilidad2.setText("");
        else
            habilidad2.setText(pokemon.getHabilidad2());
        if(pokemon.getHabilidadOculta()==null)
            habilidadOculta.setText("");
        else
            habilidadOculta.setText(pokemon.getHabilidadOculta());
    }
}