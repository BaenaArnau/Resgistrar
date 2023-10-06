package com.example.saludador;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RegistradorViewModel extends AndroidViewModel {
    ExecutorService executors;
    Registrador registrador;
    MutableLiveData<String> registro = new MutableLiveData<>();
    MutableLiveData<Boolean> calculando = new MutableLiveData<>();
    public RegistradorViewModel(@NonNull Application application) {
        super(application);

        executors = Executors.newSingleThreadExecutor();
        registrador = new Registrador();
    }

    public void registro(String nombre, String apellido, String contraseña,String nickname, String gmail){
        final Registrador.Solicitud solicitud = new Registrador.Solicitud(nombre,apellido,contraseña,nickname,gmail);

        executors.execute(new Runnable() {
            @Override
            public void run() {
                registrador.registro(solicitud, new Registrador.Callback() {
                    @Override
                    public void cuandoEsteElRegistro(String registroResultado) {
                        registro.postValue(registroResultado);
                    }
                    @Override
                    public void cuandoEmpieceElCalculo() {
                        calculando.postValue(true);
                    }

                    @Override
                    public void cuandoFinaliceElCalculo() {
                        calculando.postValue(false);
                    }
                });
            }
        });
    }

}
