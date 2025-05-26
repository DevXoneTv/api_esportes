package com.diegodev.apidesportes.jogos.callback;

import android.util.Log;

import java.nio.charset.StandardCharsets;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

    // chamar isso para encripar     dji.ept("player.normal.np.activity.GuideActivity");
    public class dji {


        public static String ept(String value) {


          String TAG = "AesEncryption23";
          String IV = "XoneDevRebrand!n";
          String KEY = "XoneDevRebrand!n";
          String ALGORITHM = "AES/CBC/PKCS5PADDING";


            try {
                IvParameterSpec ivParameterSpec = new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8));
                SecretKeySpec secretKeySpec = new SecretKeySpec(KEY.getBytes(StandardCharsets.UTF_8), "AES");
                Cipher cipher = Cipher.getInstance(ALGORITHM);
                cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

                byte[] encrypted = cipher.doFinal(value.getBytes(StandardCharsets.UTF_8));

                StringBuilder sb = new StringBuilder();
                for (byte b : encrypted) {
                    sb.append(String.format("%02x", b));
                }

                String encryptedString = sb.toString();
                 Log.d(TAG, "Encrypted String: " + encryptedString);
                return encryptedString;
            } catch (Exception e) {
                // Log.e(TAG, "Encryption error", e);
                return null;
            }
        }
    }