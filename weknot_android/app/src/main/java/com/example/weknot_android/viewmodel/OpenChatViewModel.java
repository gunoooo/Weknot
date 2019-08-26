package com.example.weknot_android.viewmodel;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.example.weknot_android.base.BaseViewModel;
import com.example.weknot_android.network.comm.OpenChatComm;
import com.example.weknot_android.network.request.OpenChatRequest;
import com.example.weknot_android.room.entity.OpenChat.OpenChatRoom;
import com.example.weknot_android.room.entity.user.User;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.observers.DisposableSingleObserver;

public class OpenChatViewModel extends BaseViewModel<List<OpenChatRoom>, OpenChatRequest, Void> {

    private OpenChatComm openChatComm;
    public MutableLiveData<List<User>> chatRoomUsers = new MutableLiveData<>();

    public MutableLiveData<List<User>> getChatRoomUsers() {
        return chatRoomUsers;
    }

    public OpenChatViewModel(Application application) {
        super(application);
        openChatComm = new OpenChatComm();
    }

    public void getChattingRooms() {
        addDisposable(openChatComm.getChattingRooms(getToken().getToken()),getDataObserver());
    }

    public void createChattingRoom() {
        addDisposable(openChatComm.createChattingRoom(getToken().getToken(),request.getValue()),getBaseObserver());
    }

    public void getChattingRoomUsers(String roomNumber) {

        DisposableSingleObserver<List<User>> observer = new DisposableSingleObserver<List<User>>() {
                @Override
                public void onSuccess(List<User> t) {
                    chatRoomUsers.setValue(t);
                }

                @Override
                public void onError(Throwable e) {
                    errorMessage.setValue(e.getMessage());
                }
        };

        addDisposable(openChatComm.getChattingRoomUsers(getToken().getToken(),roomNumber),observer);
    }
}
