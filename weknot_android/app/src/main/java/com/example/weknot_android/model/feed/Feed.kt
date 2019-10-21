package com.example.weknot_android.model.feed

data class Feed(var feedId: Int,
                var name: String,
                var writer: String,
                var photo: String,
                var picture: String,
                var time: String,
                var comment: String,
                var like: Int,
                var likeCount: Int)