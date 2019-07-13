package com.example.weknot.viewmodel;


import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.weknot.api.FriendApi;
import com.example.weknot.manage.Token;
import com.example.weknot.network.client.SocialClient;
import com.example.weknot.retrofit.MyRetrofit;

public class SocialViewModel extends BaseViewModel<FriendApi> {

    private SocialClient socialClient;

    public SocialViewModel(Context context, Class c) {
        super(context,c);
        socialClient = new SocialClient();
    }

    public void getFriend() {
        api.getFriends(new Token(context).getToken()).enqueue(socialClient.getFriend());
    }
}
