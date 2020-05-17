package com.androidavanzado.prueba;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

import androidx.lifecycle.LiveData;

import com.androidavanzado.prueba.model.entity.NotaEntity;

import java.util.List;

public class NewNotaDialogViewModel extends AndroidViewModel {
    private LiveData<List<NotaEntity>> allNotas;
    private NotaRepository notaRepository;

    public NewNotaDialogViewModel(Application application){
        super(application);

        notaRepository = new NotaRepository(application);
        allNotas = notaRepository.getAll();
    }

    // El Fragmento que recibira la nueva lista de datos.
    public LiveData<List<NotaEntity>> getAllNotas(){return allNotas;}

    // El Fragmento que inserte una nueva nota
    public void insertarNota(NotaEntity notaEntity){notaRepository.insertNota(notaEntity);}
}
