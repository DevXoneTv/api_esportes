package com.diegodev.apidesportes.jogos.utils;

import android.util.Log;

import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;

public class UnsafeOkHttpClient {
    private static final String TAG = "UnsafeOkHttpClient";

    public static OkHttpClient getUnsafeOkHttpClient() {
        try {
            Log.d(TAG, "Iniciando configuração do OkHttpClient ignorando SSL...");

            final TrustManager[] trustAllCertificates = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                            Log.d(TAG, "checkClientTrusted chamado com authType: " + authType);
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                            Log.d(TAG, "checkServerTrusted chamado com authType: " + authType);
                            if (chain != null) {
                                for (java.security.cert.X509Certificate cert : chain) {
                                    Log.d(TAG, "Certificado do Servidor: " + cert.getSubjectDN().toString());
                                }
                            } else {
                                Log.w(TAG, "Nenhum certificado recebido no checkServerTrusted.");
                            }
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            Log.d(TAG, "getAcceptedIssuers chamado.");
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            Log.d(TAG, "Criando SSLContext...");
            final SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCertificates, new java.security.SecureRandom());

            Log.d(TAG, "Criando OkHttpClient ignorando verificação SSL...");
            OkHttpClient client = new OkHttpClient.Builder()
                    .sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) trustAllCertificates[0])
                    .hostnameVerifier((hostname, session) -> {
                        Log.d(TAG, "Ignorando verificação de hostname para: " + hostname);
                        return true;
                    })
                    .build();

            Log.d(TAG, "OkHttpClient configurado com sucesso.");
            return client;
        } catch (Exception e) {
            Log.e(TAG, "Erro ao configurar OkHttpClient inseguro", e);
            throw new RuntimeException(e);
        }
    }
}
