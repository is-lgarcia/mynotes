package com.androidavanzado.prueba;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {NotaEntity.class}, version = 1)
public abstract class NotaRoomDataBase extends RoomDatabase {
    public abstract NotaDAO getNotaDao();
    private static volatile NotaRoomDataBase INSTANCE;
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
