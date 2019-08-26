package com.example.weknot_android.util;

import com.example.weknot_android.BuildConfig;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.Executors;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class Utils {

    public static Retrofit RETROFIT =
            new Retrofit.Builder()
                    .client(getClient())
                    .baseUrl(Strings.MAIN_HOST)
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .callbackExecutor(Executors.newSingleThreadExecutor())
                    .build();

    private static OkHttpClient getClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG)
            builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        return builder.build();
    }

    public static String encryptSHA512(String target) throws NoSuchAlgorithmException {

        final MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
        final StringBuilder encryptedPassword = new StringBuilder();

        messageDigest.update(target.getBytes());

        final byte[] buffer = messageDigest.digest();

        for (byte temp : buffer) {

            StringBuilder sb = new StringBuilder(Integer.toHexString(temp));

            while (sb.length() < 2) {

                sb.insert(0, "0");
            }

            sb = new StringBuilder(sb.substring(sb.length() - 2));
            encryptedPassword.append(sb);
        }

        return encryptedPassword.toString();
    }
}
