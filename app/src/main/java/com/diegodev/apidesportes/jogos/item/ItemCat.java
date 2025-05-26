package com.diegodev.apidesportes.jogos.item;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "categorias")
public class ItemCat {

    @PrimaryKey
    @ColumnInfo(name = "category")
    @SerializedName("id")
    public int category;

    @ColumnInfo(name = "category_name")
    @SerializedName("campName")
    public String categoryname;

    @ColumnInfo(name = "logo")
    @SerializedName("logoCamp")
    public String logo;

    // Getters
    public int getCategory() {
        return category;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public String getLogo() {
        return logo;
    }

    // Construtor vazio obrigatório
    public ItemCat() { }
}

