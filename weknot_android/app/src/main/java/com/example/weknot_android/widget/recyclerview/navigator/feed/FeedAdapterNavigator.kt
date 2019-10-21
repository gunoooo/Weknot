package com.example.weknot_android.widget.recyclerview.navigator.feed

import com.example.weknot_android.model.feed.Feed

interface FeedAdapterNavigator {
    fun like(feed: Feed)
    fun openProfile(id: String)
    fun openPicture(url: String)
}