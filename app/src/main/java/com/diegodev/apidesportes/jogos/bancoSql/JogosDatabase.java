package com.diegodev.apidesportes.jogos.bancoSql;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.diegodev.apidesportes.jogos.item.ItemJogos;


@Database(entities = {ItemJogos.class}, version = 1)
public abstract class JogosDatabase extends RoomDatabase {

    private static volatile JogosDatabase INSTANCE;

    public abstract JogosDao jogosDao();

    public static JogosDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (JogosDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    JogosDatabase.class, "jogos.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
