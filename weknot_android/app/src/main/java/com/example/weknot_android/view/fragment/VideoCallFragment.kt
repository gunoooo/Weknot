package com.example.weknot_android.view.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.weknot_android.R
import com.example.weknot_android.base.BaseFragment
import com.example.weknot_android.databinding.VideoCallFragmentBinding
import com.example.weknot_android.model.entity.videocall.VideoCall
import com.example.weknot_android.viewmodel.VideoCallViewModel
import org.jitsi.meet.sdk.JitsiMeet
import org.jitsi.meet.sdk.JitsiMeetActivity
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions.Builder
import java.net.MalformedURLException
import java.net.URL

class VideoCallFragment : BaseFragment<VideoCallFragmentBinding>() {
    private lateinit var videoCallViewModel: VideoCallViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModel()
        initView()
        initVideoCall()

        observeVideoCallViewModel()

        clickEvent()
    }

    private fun observeVideoCallViewModel() {
        videoCallViewModel.getData().observe(this, Observer { videoCall: VideoCall ->
            val options: JitsiMeetConferenceOptions? = Builder()
                    .setRoom("channel" + videoCall.channel)
                    .build()
            JitsiMeetActivity.launch(context, options)
        })
    }

    private fun clickEvent() {
        binding.videoCallBtn.setOnClickListener { videoCallViewModel.requestCall() }
    }

    private fun initView() {
//        Glide.with(this).load(R.drawable.profile).into(binding.profileImage)
    }

    private fun initVideoCall() {
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

    private fun initViewModel() {
        videoCallViewModel = ViewModelProviders.of(this).get(VideoCallViewModel::class.java)
    }

    override fun layoutId(): Int {
        return R.layout.video_call_fragment
    }
}