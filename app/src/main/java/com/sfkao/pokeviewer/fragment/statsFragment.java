package com.sfkao.pokeviewer.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sfkao.pokeviewer.R;
import com.sfkao.pokeviewer.activities.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class statsFragment extends Fragment {

    public statsFragment() {
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
        this.context.setHabilidad1(this.context.findViewById(R.id.habilidad1));
        this.context.setHabilidad2(this.context.findViewById(R.id.habilidad2));
        this.context.setHabilidadOculta(this.context.findViewById(R.id.habilidadOculta));
        this.context.setPsText(this.context.findViewById(R.id.psText));
        this.context.setPsBar(this.context.findViewById(R.id.psProgress));
        this.context.setAtaqueText(this.context.findViewById(R.id.attackText));
        this.context.setAtaqueBar(this.context.findViewById(R.id.attackProgress));
        this.context.setDefensaText(this.context.findViewById(R.id.defenceText));
        this.context.setDefensaBar(this.context.findViewById(R.id.defenceProgress));
        this.context.setsAtaqueText(this.context.findViewById(R.id.sAtkText));
        this.context.setsAtaqueBar(this.context.findViewById(R.id.sAtkProgress));
        this.context.setsDefensaText(this.context.findViewById(R.id.sDefText));
        this.context.setsDefensaBar(this.context.findViewById(R.id.sDefProgress));
        this.context.setVelocidadText(this.context.findViewById(R.id.speText));
        this.context.setVelocidadBar(this.context.findViewById(R.id.speProgress));
    //ACTION BAR

        this.context.cargadasCaracteristicas();

    }
}