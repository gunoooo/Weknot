package com.example.weknot_android.widget.recyclerview.navigator.social

import com.example.weknot_android.model.user.Friend

interface SocialAdapterNavigator {
    fun checkFriend(message: String, friend: Friend)
    fun openChatRoom(id: String)
    fun openProfile(id: String)
}