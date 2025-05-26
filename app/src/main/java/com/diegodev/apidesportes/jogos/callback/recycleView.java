package com.diegodev.apidesportes.jogos.callback;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Base64;

import java.io.InputStream;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

@SuppressLint("AppCompatCustomView")
public class recycleView  {

    private static final String TAG = "ApiTextView";

    public static void getSit(Context context) {

        if (context == null) {
           
            return;
        } else { // // Log.d(TAG, "✅ Contexto válido: " + context.getPackageName());
        }


        String EXPECTED_SIGNATURE = "MIIDSjCCAjICAQEwDQYJKoZIhvcNAQELBQAwajESMBAGA1UEAwwJbWF5YWxvcGVzMRIwEAYDVQQLDAltYXlhbG9wZXMxEjAQBgNVBAoMCW1heWFsb3BlczESMBAGA1UEBwwJc2FvIHBhdWxvMQswCQYDVQQIDAJzcDELMAkGA1UEBhMCMjcwIBcNMjMxMTE1MTQyMTIxWhgPMjEyMjEwMjIxNDIxMjFaMGoxEjAQBgNVBAMMCW1heWFsb3BlczESMBAGA1UECwwJbWF5YWxvcGVzMRIwEAYDVQQKDAltYXlhbG9wZXMxEjAQBgNVBAcMCXNhbyBwYXVsbzELMAkGA1UECAwCc3AxCzAJBgNVBAYTAjI3MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAymkHiblWCh/9V/1rjgVBqRMT0+qGJSS5Oj5g2708CNnqD51Ftjwas0jwhed77SZmpmlyqVACvoLzkdhyXdnzpcjXBIjnQnY5tIovrFdLDocD8hBCz+JdbTSHc1jKeZ36Ah8ittGTSL65LDtnfwq5WQfE8J+27/Tu0LhWjEuj7DaqpFoqlJa5C4/OjedodZsM3zbxoVPRvIA+OWeugXCWb65ycoK+E+NAw7r5UjmKsn6QcjlhnsFQSGN7NjeNITU5p98l3WA9hw7vQPf5TbnB9wybAxLKmTgsNmqr8mwfoUCldj30F7jm7/fxAwJroscyG2O/xcF9RmVOp13hKvTQZQIDAQABMA0GCSqGSIb3DQEBCwUAA4IBAQBrPDNzyLODJLBRKNDSuVJ+6sW4TNmOYTqMQJYu0ly31k3C9/xLZGc6xTVpx4+tJ/QUga1w3Fvc3dn0xrkbf7WmN7GULjBvCeYWmoZYPNqWb/8ECb6/aFM7KZxxssjRdi90O2+PpbIG3FybleZlbs0qn5adAuAXRTe4biOjpFJszCsXYqEqqrWa6VBz/8qjNP+v0aQDnaIIotdH7QGRONjTxos4WDvfkwvLiVL4dR/+umde74I0lgu5N2I/WD6UErvmBcClStHBTZHzw/CR+8CkDHWB2Etm6fXIjH8Bhc7diDcfgD9wwoD07zBx44Z8ROuVBWdzTfd8ZoixF/HH6KQC";
        try {

            String apkPath = na.getRealApkPath(context);
            boolean isValid = na.isValidApkPath(apkPath);
       
            if (!isValid) {
                // // Log.e(TAG, "APK não está na pasta oficial ou foi modificado! Fechando app...");
                  System.exit(0);
            } else {
                // // Log.d(TAG, "APK autenticado com sucesso.");
            }



            if (apkPath == null) {
                // // Log.e(TAG, "❌ Caminho do APK não encontrado. Abortando verificação.");
                return;
            }
            // // Log.d(TAG, "✅ Caminho do APK obtido: " + apkPath);

            // 📦 Abrindo o APK como um arquivo ZIP
            ZipFile zipFile = new ZipFile(apkPath);
            // // Log.d(TAG, "📂 APK aberto como ZIP. Iniciando busca por certificados...");

            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            boolean foundCertificate = false;

            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                //   // // Log.d(TAG, "🔍 Verificando entrada: " + entry.getName());

                if (entry.getName().startsWith("META-INF/") &&
                        (entry.getName().endsWith(".RSA") || entry.getName().endsWith(".DSA") || entry.getName().endsWith(".EC"))) {

                    // // Log.d(TAG, "🔑 Certificado encontrado: " + entry.getName());
                    foundCertificate = true;

                    InputStream is = zipFile.getInputStream(entry);
                    CertificateFactory cf = CertificateFactory.getInstance("X.509");
                    Certificate cert = cf.generateCertificate(is);

                    if (cert instanceof X509Certificate) {
                        X509Certificate x509Cert = (X509Certificate) cert;
                        String signature = Base64.encodeToString(x509Cert.getEncoded(), Base64.NO_WRAP);
                        // // Log.d(TAG, "🔏 Certificado extraído: " + signature);

                        if (EXPECTED_SIGNATURE.equals(signature)) {
                            // // Log.d(TAG, "✅ Assinatura válida confirmada.");

                        } else {
                            // // Log.e(TAG, "❌ Assinatura inválida! O APK pode estar modificado.");
                System.exit(0);
                        }
                    }
                    is.close();
                }
            }

            zipFile.close();

            if (!foundCertificate) {
                // Log.w(TAG, "⚠ Nenhum certificado foi encontrado no APK!");
    //   System.exit(0);

            }

        } catch (Exception e) {
            // // Log.e(TAG, "❌ Erro ao verificar assinaturas do APK", e);
        System.exit(0);
        }
    }


}

