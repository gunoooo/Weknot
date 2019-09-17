package com.example.weknot_android.view.navigator

import com.example.weknot_android.base.BaseNavigator
import com.example.weknot_android.model.entity.videocall.VideoCall

interface VideoCallNavigator : BaseNavigator {
    fun connectVideoCall(videoCall: VideoCall)
}