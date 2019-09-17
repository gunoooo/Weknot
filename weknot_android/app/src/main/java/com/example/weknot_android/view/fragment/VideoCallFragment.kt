package com.example.weknot_android.view.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.weknot_android.R
import com.example.weknot_android.base.BaseFragment
import com.example.weknot_android.databinding.VideoCallFragmentBinding
import com.example.weknot_android.model.entity.videocall.VideoCall
import com.example.weknot_android.view.navigator.VideoCallNavigator
import com.example.weknot_android.viewmodel.VideoCallViewModel
import org.jitsi.meet.sdk.JitsiMeet
import org.jitsi.meet.sdk.JitsiMeetActivity
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions.Builder
import java.net.MalformedURLException
import java.net.URL

class VideoCallFragment : BaseFragment<VideoCallFragmentBinding, VideoCallViewModel>(), VideoCallNavigator {

    override fun getLayoutId(): Int {
        return R.layout.video_call_fragment
    }

    override fun getViewModel(): Class<VideoCallViewModel> {
        return VideoCallViewModel::class.java
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun connectVideoCall(videoCall: VideoCall) {
        val options: JitsiMeetConferenceOptions? = Builder()
                .setRoom("channel" + videoCall.channel)
                .build()
        JitsiMeetActivity.launch(context, options)
    }

    override fun handleError(throwable: Throwable) {
        Toast.makeText(context,throwable.message,Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNavigator(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    private fun setUp() {
        val serverURL: URL
        try {
            serverURL = URL("https://meet.jit.si")
        } catch (e: MalformedURLException) {
            e.printStackTrace()
            throw RuntimeException("Invalid server URL!")
        }
        val defaultOptions: JitsiMeetConferenceOptions? = Builder()
                .setServerURL(serverURL)
                .setWelcomePageEnabled(false)
                .build()
        JitsiMeet.setDefaultConferenceOptions(defaultOptions)
    }
}