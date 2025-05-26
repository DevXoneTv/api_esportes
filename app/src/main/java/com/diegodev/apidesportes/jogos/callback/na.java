package com.diegodev.apidesportes.jogos.callback;

import android.content.Context;

public class na {
    static {
        System.loadLibrary("api_esportes");
    }
    public static native String getRealApkPath(Context context);
    public static native boolean isValidApkPath(String apkPath);
    public static native String ae();
    public static native boolean verificarUrlNativa(String url);
}
