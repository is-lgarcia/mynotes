package com.androidavanzado.prueba.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.androidavanzado.prueba.model.dao.NotaDAO;
import com.androidavanzado.prueba.model.entity.NotaEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {NotaEntity.class}, version = 1)
public abstract class NotaRoomDataBase extends RoomDatabase {
    public abstract NotaDAO getNotaDao();
    private static volatile NotaRoomDataBase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static NotaRoomDataBase getDataBase(final Context context){
        if (INSTANCE == null){
            synchronized (NotaRoomDataBase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NotaRoomDataBase.class, "notas_database").build();
                }
            }
        }

        return INSTANCE;
    }
}
