package com.diegodev.apidesportes.jogos.bancoSql;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.diegodev.apidesportes.jogos.item.ItemCat;

import java.util.List;

@Dao
public interface CategoriaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<ItemCat> categorias);

    @Query("DELETE FROM categorias")
    void limpar();

    @Query("SELECT * FROM categorias")
    List<ItemCat> getTodas();
}
