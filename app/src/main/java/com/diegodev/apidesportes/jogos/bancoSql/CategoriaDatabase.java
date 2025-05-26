package com.diegodev.apidesportes.jogos.bancoSql;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.diegodev.apidesportes.jogos.item.ItemCat;


@Database(entities = {ItemCat.class}, version = 1, exportSchema = false)
public abstract class CategoriaDatabase extends RoomDatabase {

    private static volatile CategoriaDatabase INSTANCE;

    public abstract CategoriaDao categoriaDao();

    public static CategoriaDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (CategoriaDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    CategoriaDatabase.class, "categoria_database")
                            .fallbackToDestructiveMigration() // destrói e recria se houver mudança de versão
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
