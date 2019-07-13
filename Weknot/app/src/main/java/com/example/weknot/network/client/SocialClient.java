package com.example.weknot.network.client;

import com.example.weknot.api.FriendApi;
import com.example.weknot.data.Friend;
import com.example.weknot.data.SuccessResult;
import com.example.weknot.main_page.fragment.SocialFragment;
import com.example.weknot.manage.Token;
import com.example.weknot.network.requestmodel.FriendRequest;
import com.example.weknot.retrofit.MyRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SocialClient {

    public Callback<List<Friend>> getFriend() {

        return new Callback<List<Friend>>() {
            @Override
            public void onResponse(Call<List<Friend>> call, Response<List<Friend>> response) {

            }

            @Override
            public void onFailure(Call<List<Friend>> call, Throwable t) {

            }
        };
    }

    public Call<SuccessResult> addFriend(FriendRequest friendRequest) {
        return null;
    }
}
