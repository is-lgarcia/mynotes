package com.androidavanzado.prueba;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;

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

    //Funcion para actualizar una nueva nota
    public void updateNota(NotaEntity entity){notaRepository.updateNota(entity);}

    //Funcion para eliminar todas las notas
    public void deleteAll(){notaRepository.deleteAll();}

    //Funcion para eliminar por Id
    public void deleteById(int id){notaRepository.deleteById(id);}
}
