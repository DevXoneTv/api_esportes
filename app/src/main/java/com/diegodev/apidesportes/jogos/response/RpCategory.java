package com.diegodev.apidesportes.jogos.response;

import android.content.Context;
import android.util.Log;

import com.diegodev.apidesportes.jogos.item.ItemCat;
import com.diegodev.apidesportes.jogos.interfac.ServiceCate;
import com.diegodev.apidesportes.jogos.utils.UnsafeOkHttpClient;
import com.diegodev.apidesportes.jogos.bancoSql.CategoriaDatabase;
import com.diegodev.apidesportes.jogos.callback.na;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RpCategory {

    private Context activity;
    private Context context;
    private ServiceCate apiService;

    // Construtor
    public RpCategory(Context activity) {
        this.activity = activity;
        this.context = activity.getApplicationContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://your_base_url/") // substitua pela URL base da sua API
                .client(UnsafeOkHttpClient.getUnsafeOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ServiceCate.class);
    }

    // Método executando a API diretamente, sem listener
    public void execute(String fullUrl, String token) {
        String authHeader = "Bearer " + token;

        Call<List<ItemCat>> call = apiService.getOndemanCategories(fullUrl, authHeader);
        call.enqueue(new Callback<List<ItemCat>>() {
            @Override
            public void onResponse(Call<List<ItemCat>> call, Response<List<ItemCat>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String urlChamada = call.request().url().toString();
                    if (!na.verificarUrlNativa(urlChamada)) {
                        System.exit(0);
                        return;
                    }
                    List<ItemCat> itemCategories = response.body();
                    Log.d("categoryjogos", "Resposta do servidor: " + itemCategories);

                    if (!itemCategories.isEmpty()) {
                        // Salva no banco Room em background
                        new Thread(() -> {
                            CategoriaDatabase db = CategoriaDatabase.getInstance(context); // certifique-se que 'context' está acessível aqui
                            db.categoriaDao().limpar(); // se quiser limpar antes
                            db.categoriaDao().insertAll(itemCategories);
                            Log.d("categoryjogos", "Itens salvos no banco de dados Room: " + itemCategories.size());
                        }).start();
                    }
                } else {
                    Log.e("categoryjogos", "Erro na resposta: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<ItemCat>> call, Throwable t) {
                Log.e("categoryjogos", "Falha na requisição: " + t.getMessage(), t);
            }
        });
    }

}
