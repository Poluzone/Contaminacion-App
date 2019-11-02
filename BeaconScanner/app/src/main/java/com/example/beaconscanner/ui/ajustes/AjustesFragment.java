package com.example.beaconscanner.ui.ajustes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.beaconscanner.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AjustesFragment extends Fragment {
    //---------------------------------------------------------------------------
    //Clase relacionada con el botón Ajustes
    //---------------------------------------------------------------------------
    private AjustesViewModel ajustesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ajustesViewModel =
                ViewModelProviders.of(this).get(AjustesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_ajustes, container, false);

        FloatingActionButton fab = root.findViewById(R.id.fab);

        /*final TextView textView = root.findViewById(R.id.text_ajustes);
        ajustesViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/ // Esto era para cambiar un texto que tenia por defecto la navigation Activity que te decia en que fragmento estabas
        return root;
    }
}