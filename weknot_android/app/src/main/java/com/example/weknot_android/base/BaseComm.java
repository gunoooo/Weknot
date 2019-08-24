package com.example.weknot_android.base;

import com.example.weknot_android.network.response.Response;
import com.example.weknot_android.util.Utils;

import org.json.JSONObject;

import java.util.Objects;

import io.reactivex.functions.Function;

public abstract class BaseComm<V> {

    protected V api;

    public BaseComm() {
        api = (V) Utils.RETROFIT.create(api.getClass());
    }

    protected <T> Function<retrofit2.Response<Response<T>>, T> getResponseObjectsFunction() {
        return response -> {
                    if (!response.isSuccessful()) {
                        JSONObject errorBody = new JSONObject(Objects
                                .requireNonNull(
                                        response.errorBody()).string());

                        throw new Exception(errorBody.getString("message"));
                    }
                    return (T) response.body().getData();
                };
    }

}
