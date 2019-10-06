package com.example.weknot_android.widget.recyclerview.navigator.social

import com.example.weknot_android.model.entity.user.Friend

interface SocialAdapterNavigator {
    fun checkFriend(message: String, friend: Friend)
}