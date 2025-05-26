#include <jni.h>
#include <fstream>
#include <string>
#include <iostream>
#include <jni.h>
#include <string>
#include <fstream>
#include <sys/stat.h>
#include <jni.h>
#include <string>
#include <android/log.h>
#include <cstdlib>
#include <vector>
#define LOG_TAG "NATIVE_URL"

std::string base64_decode(const std::string &in) {
    std::string out;
    std::vector<int> T(256, -1);
    for (int i = 0; i < 64; i++)
        T["ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"[i]] = i;

    int val = 0, valb = -8;
    for (unsigned char c : in) {
        if (T[c] == -1) break;
        val = (val << 6) + T[c];
        valb += 6;
        if (valb >= 0) {
            out.push_back(char((val >> valb) & 0xFF));
            valb -= 8;
        }
    }
    return out;
}



extern "C"
JNIEXPORT jstring JNICALL
Java_com_diegodev_apidesportes_jogos_callback_na_ae(JNIEnv *env, jobject thiz) {
    std::string parte1 = "f7d590a9520708141";
    std::string parte2 = "9e884a703d17d3bd";
    std::string parte3 = "39a3435cfc1a1fddb";
    std::string parte4 = "6cd99951bdff26e534238a909";
    std::string parte5 = "b7d01a5c12e1d91039181";
    std::string resultado = parte1 + parte2 + parte3 + parte4 + parte5;
    return env->NewStringUTF(resultado.c_str());
}

extern "C"
JNIEXPORT jboolean JNICALL
Java_com_diegodev_apidesportes_jogos_callback_na_verificarUrlNativa(JNIEnv *env, jobject thiz, jstring urlJava) {
    const char* urlC = env->GetStringUTFChars(urlJava, nullptr);
    std::string urlStr(urlC);
    std::string parte1 = "YXBpLn";
    std::string parte2 = "Nwb3";
    std::string parte3 = "J0c2JyYX";
    std::string parte4 = "BpLmNvbQ==";
    std::string base64Completo = parte1 + parte2 + parte3 + parte4;

    std::string palavraEsperada = base64_decode(base64Completo);
    if (urlStr.find(palavraEsperada) != std::string::npos) {
        env->ReleaseStringUTFChars(urlJava, urlC);
        return JNI_TRUE;
    } else {
        env->ReleaseStringUTFChars(urlJava, urlC);
        exit(0);  // Encerra imediatamente
        return JNI_FALSE; // Só pra segurança
    }
}
