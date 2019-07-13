package com.example.weknot.viewmodel;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.weknot.retrofit.MyRetrofit;

public class BaseViewModel<T> extends ViewModel {

    public Context context;

    public T api;

    public BaseViewModel(Context context, Class c) {
        this.context = context;

        api = (T) MyRetrofit.getRetrofit().create(c);
    }
}
