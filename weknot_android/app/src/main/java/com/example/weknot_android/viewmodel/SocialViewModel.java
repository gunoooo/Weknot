package com.example.weknot_android.viewmodel;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.example.weknot_android.base.BaseViewModel;
import com.example.weknot_android.network.comm.SocialComm;
import com.example.weknot_android.room.entity.user.Friend;

import java.util.List;

public class SocialViewModel extends BaseViewModel<List<Friend>, Void, Void> {

    public MutableLiveData<List<Friend>> receiveList = new MutableLiveData<>();
    public MutableLiveData<List<Friend>> friendList = new MutableLiveData<>();

    private SocialComm socialComm;

    public MutableLiveData<List<Friend>> getReceiveList() {
        return receiveList;
    }

    public MutableLiveData<List<Friend>> getFriendList() {
        return friendList;
    }

    public SocialViewModel(Application application) {
        super(application);
        socialComm = new SocialComm();
    }

    public void getFriends() {
        addDisposable(socialComm.getFriends(getToken().getToken()),getDataObserver());
    }
}
