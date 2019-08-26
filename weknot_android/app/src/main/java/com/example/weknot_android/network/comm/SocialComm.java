package com.example.weknot_android.network.comm;

import com.example.weknot_android.base.BaseComm;
import com.example.weknot_android.network.api.SocialApi;
import com.example.weknot_android.room.entity.user.Friend;

import java.util.List;

import io.reactivex.Single;

public class SocialComm extends BaseComm<SocialApi> {

    public Single<List<Friend>> getFriends(String token) {
        return api.getFriends(token).map(getResponseObjectsFunction());
    }

    @Override
    protected Class<SocialApi> type() {
        return SocialApi.class;
    }
}
