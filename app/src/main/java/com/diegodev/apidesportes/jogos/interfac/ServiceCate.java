package com.diegodev.apidesportes.jogos.interfac;


import com.diegodev.apidesportes.jogos.item.ItemCat;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Url;

public interface ServiceCate {
    @GET
    Call<List<ItemCat>> getOndemanCategories(@Url String url, @Header("Authorization") String authHeader);
}
