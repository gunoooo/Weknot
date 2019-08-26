package com.example.weknot_android.base;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.weknot_android.room.repository.TokenRepository;
import com.example.weknot_android.room.repository.RoomRepository;
import com.example.weknot_android.room.sharedpreference.Token;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseViewModel<DT, RT, ET> extends AndroidViewModel {

    private CompositeDisposable disposable;
    private TokenRepository tokenManager;
    private RoomRepository repository;

    protected final MutableLiveData<String> successMessage = new MutableLiveData<>();
    protected final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    final MutableLiveData<DT> data = new MutableLiveData<>();
    final MutableLiveData<ET> entity = new MutableLiveData<>();
    public final MutableLiveData<RT> request = new MutableLiveData<>();

    public LiveData<String> getSuccessMessage() {
        return successMessage;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public LiveData<DT> getData() {
        return data;
    }

    public LiveData<ET> getEntity() {
        return entity;
    }

    public Token getToken() {
        return tokenManager.getToken();
    }

    public RoomRepository getRepository() {
        return repository;
    }

    protected BaseViewModel(Application application) {
        super(application);
        disposable = new CompositeDisposable();
        tokenManager = new TokenRepository(application);
        repository = new RoomRepository(application);
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

    public DisposableSingleObserver<ET> getEntityObserver() {
        return new DisposableSingleObserver<ET>() {
            @Override
            public void onSuccess(ET t) {
                entity.setValue(t);
            }

            @Override
            public void onError(Throwable e) {
                errorMessage.setValue(e.getMessage());
            }
        };
    }
}
