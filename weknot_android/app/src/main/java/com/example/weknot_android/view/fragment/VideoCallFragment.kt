package com.example.weknot_android.view.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import com.example.weknot_android.R
import com.example.weknot_android.base.fragment.BaseFragment
import com.example.weknot_android.databinding.VideoCallFragmentBinding
import com.example.weknot_android.model.entity.videocall.VideoCall
import com.example.weknot_android.viewmodel.VideoCallViewModel
import org.jitsi.meet.sdk.JitsiMeet
import org.jitsi.meet.sdk.JitsiMeetActivity
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions.Builder
import java.net.MalformedURLException
import java.net.URL

class VideoCallFragment : BaseFragment<VideoCallFragmentBinding, VideoCallViewModel>() {

    override fun getLayoutId(): Int {
        return R.layout.video_call_fragment
    }

    override fun getViewModel(): Class<VideoCallViewModel> {
        return VideoCallViewModel::class.java
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun initObserver() {
        with(viewModel) {
            connectVideoCall.observe(this@VideoCallFragment, Observer {
                val options: JitsiMeetConferenceOptions? = Builder()
                        .setRoom("channel" + it.channel)
                        .build()
                JitsiMeetActivity.launch(context, options)
            })
        }
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