package com.diegodev.apidesportes.jogos.bancoSql;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.diegodev.apidesportes.jogos.item.ItemJogos;

import java.util.List;

@Dao
public interface JogosDao {

    @Insert
    void insertAll(List<ItemJogos> jogos);

    @Query("SELECT * FROM jogos ORDER BY start ASC")
    List<ItemJogos> getTodos();

    @Query("DELETE FROM jogos")
    void limpar();

    @Query("SELECT * FROM jogos WHERE id_camp = :idCamp ORDER BY start ASC")
    List<ItemJogos> getJogosPorIdCamp(int idCamp);

    @Query("SELECT * FROM jogos WHERE start LIKE :data || '%' ORDER BY start ASC")
    List<ItemJogos> getJogosPorData(String data);

}
