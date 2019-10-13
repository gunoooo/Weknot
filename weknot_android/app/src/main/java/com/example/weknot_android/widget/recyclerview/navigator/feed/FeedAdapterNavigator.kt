package com.example.weknot_android.widget.recyclerview.navigator.feed

import com.example.weknot_android.model.entity.feed.Feed

interface FeedAdapterNavigator {
    fun like(feed: Feed)
    fun openProfile(id: String)
}