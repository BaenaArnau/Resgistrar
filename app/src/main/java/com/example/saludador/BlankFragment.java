package com.example.saludador;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.text.TextUtils;
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
        final RegistradorViewModel registradorViewModel = new ViewModelProvider(this).get(RegistradorViewModel.class);
        binding.registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean error = false;
                String nombre = binding.nombre.getText().toString();
                String apellidos = binding.apellidos.getText().toString();
                String contraseña = binding.password.getText().toString();
                String nickname = binding.nickname.getText().toString();
                String gmail = binding.email.getText().toString();

                if (TextUtils.isEmpty(nombre)) {
                    binding.nombre.setError("No se pueden dejar campos vacíos");
                    error = true;
                }

                if (TextUtils.isEmpty(apellidos)) {
                    binding.apellidos.setError("No se pueden dejar campos vacíos");
                    error = true;
                }

                if (TextUtils.isEmpty(contraseña)) {
                    binding.password.setError("No se pueden dejar campos vacíos");
                    error = true;
                } else if (!validarContraseña(contraseña)) {
                    binding.password.setError("La contraseña debe contener al menos una mayúscula, una minúscula, un símbolo y un número");
                    error = true;
                }

                if (TextUtils.isEmpty(nickname)) {
                    binding.nickname.setError("No se pueden dejar campos vacíos");
                    error = true;
                }

                if (TextUtils.isEmpty(gmail)) {
                    binding.email.setError("No se pueden dejar campos vacíos");
                    error = true;
                } else if (!gmail.contains("@")) {
                    binding.email.setError("Introduzca una dirección de correo electrónico válida");
                    error = true;
                }

                if (!error){
                    registradorViewModel.registro(nombre, apellidos, contraseña, nickname, gmail);
                }
            }
        });

        registradorViewModel.registro.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String registro) {
                binding.registrado.setText(String.format(registro));
            }
        });

        registradorViewModel.calculando.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean calculando) {
                if (calculando) {
                    binding.calculando.setVisibility(View.VISIBLE);
                    binding.registrado.setVisibility(View.GONE);
                } else {
                    binding.calculando.setVisibility(View.GONE);
                    binding.registrado.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private boolean validarContraseña(String contraseña) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
        return contraseña.matches(regex);
    }
}
