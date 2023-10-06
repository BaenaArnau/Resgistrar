package com.example.saludador;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.concurrent.Executors;

public class RegistradorViewModel extends AndroidViewModel {
    Executors executors;

    public RegistradorViewModel(@NonNull Application application) {
        super(application);
    }
}
