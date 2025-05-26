package com.diegodev.apidesportes.jogos.utils;

import android.content.Context;
import android.content.SharedPreferences;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class SharedUtil {


    public static void salvarHoraRedeSaoPaulo(Context context) {
        new Thread(() -> {
            SharedPreferences prefs = context.getSharedPreferences("ClienteSetup", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();

            String dataFinal;

            try {
                URL url = new URL("https://www.google.com");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("HEAD");
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);
                connection.connect();

                String serverDate = connection.getHeaderField("Date");
                if (serverDate != null && !serverDate.isEmpty()) {
                    SimpleDateFormat formatoGMT = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
                    formatoGMT.setTimeZone(TimeZone.getTimeZone("GMT"));
                    Date date = formatoGMT.parse(serverDate);

                    SimpleDateFormat formatoSaoPaulo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                    formatoSaoPaulo.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));

                    dataFinal = formatoSaoPaulo.format(date);
                } else {
                    throw new Exception("Data do servidor ausente");
                }
            } catch (Exception e) {
                e.printStackTrace();
                // Fallback para hora local do dispositivo
                dataFinal = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                        .format(new Date());
            }

            editor.putString("DataAtual", dataFinal);
            editor.apply();
        }).start();
    }



}
