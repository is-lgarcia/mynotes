package com.androidavanzado.prueba;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.androidavanzado.prueba.model.NotaRoomDataBase;
import com.androidavanzado.prueba.model.dao.NotaDAO;
import com.androidavanzado.prueba.model.entity.NotaEntity;

import java.util.List;

public class NotaRepository {

    private NotaDAO notaDAO;
    private LiveData<List<NotaEntity>> allNotas;
    private LiveData<List<NotaEntity>> allFavoritas;

    public NotaRepository(Application application){
        NotaRoomDataBase db = NotaRoomDataBase.getDataBase(application);
        notaDAO = db.getNotaDao();
        allNotas = notaDAO.getAll();
        allFavoritas = notaDAO.getFavorites();
    }

    public LiveData<List<NotaEntity>> getAll(){ return allNotas; }

    public LiveData<List<NotaEntity>> getAllFavs(){ return allFavoritas; }

    public void insertNota (NotaEntity nota){
        NotaRoomDataBase.databaseWriteExecutor.execute(() -> {
            notaDAO.insert(nota);
        });
    }

    public void updateNota (NotaEntity nota){
        NotaRoomDataBase.databaseWriteExecutor.execute(() ->
                notaDAO.update(nota));
    }

    public void deleteAll(){notaDAO.deleteAll();}

    public void deleteById(int id){notaDAO.deleteById(id);}
}
