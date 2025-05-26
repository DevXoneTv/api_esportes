package com.diegodev.apidesportes.jogos.interfac;

import com.diegodev.apidesportes.jogos.item.ItemJogos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Url;

public interface ServiceJogos {
    @GET
    Call<List<ItemJogos>> getJogos(@Url String url, @Header("Authorization") String authHeader);

}