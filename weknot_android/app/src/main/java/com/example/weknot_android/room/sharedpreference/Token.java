package com.example.weknot_android.room.sharedpreference;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.f2prateek.rx.preferences2.Preference;
import com.f2prateek.rx.preferences2.RxSharedPreferences;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class Token extends ContextWrapper{

    private String token;

    public Token(Context context) {
        super(context);
    }

    public void setToken(String token) {

        SharedPreferences sharedPreferences = getSharedPreferences("weknot",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("token", token);

        editor.commit();
    }

    public String getToken() {

        SharedPreferences sharedPreferences = getSharedPreferences("weknot",MODE_PRIVATE);
        RxSharedPreferences rxPreferences = RxSharedPreferences.create(sharedPreferences);

        Preference<String> tokenObservable = rxPreferences.getString("token");

        tokenObservable.asObservable().subscribe(token -> this.token = token);

        return token;

    }
}
