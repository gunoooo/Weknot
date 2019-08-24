package com.example.weknot_android.base;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.weknot_android.room.entity.Token;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseViewModel<DT, RT> extends AndroidViewModel {

    private CompositeDisposable disposable;
    private Token token;

    final MutableLiveData<String> successMessage = new MutableLiveData<>();
    final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    final MutableLiveData<DT> data = new MutableLiveData<>();
    public final MutableLiveData<RT> request = new MutableLiveData<>();

    public LiveData<String> getSuccessMessage() {
        return successMessage;
    }

    public LiveData<DT> getData() {
        return data;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public Token getToken() {
        return token;
    }

    protected BaseViewModel(Application application) {
        super(application);
        disposable = new CompositeDisposable();
//        token = new Token(context);
    }

    public void addDisposable(Single single, DisposableSingleObserver observer) {
        disposable.add((Disposable) single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(observer));
    }

    public DisposableSingleObserver<String> getBaseObserver() {
        return new DisposableSingleObserver<String>() {
            @Override
            public void onSuccess(String s) {
                successMessage.setValue(s);
            }

            @Override
            public void onError(Throwable e) {
                errorMessage.setValue(e.getMessage());
            }
        };
    }

    public DisposableSingleObserver<DT> getDataObserver() {
        return new DisposableSingleObserver<DT>() {
            @Override
            public void onSuccess(DT t) {
                data.setValue(t);
            }

            @Override
            public void onError(Throwable e) {
                errorMessage.setValue(e.getMessage());
            }
        };
    }
}
