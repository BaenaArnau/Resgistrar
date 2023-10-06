package com.example.saludador;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.saludador.databinding.FragmentBlankBinding;

import org.jetbrains.annotations.Nullable;

public class BlankFragment extends Fragment {

    private FragmentBlankBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentBlankBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Registrador registrador = new Registrador();
                String nombre = binding.nombre.getText().toString();
                String apellidos = binding.apellidos.getText().toString();
                String contraseña = binding.password.getText().toString();
                String nickname = binding.nickname.getText().toString();
                String gmail = binding.email.getText().toString();

                Registrador.Solicitud solicitud = new Registrador.Solicitud(nombre,apellidos,contraseña,nickname,gmail);
                String registrado = registrador.registro(solicitud);

                binding.registrado.setText(String.format(registrado));
            }
        });
    }


}