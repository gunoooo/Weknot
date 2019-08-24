package com.example.weknot_android.view.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.example.weknot_android.base.BaseFragment;
import com.example.weknot_android.databinding.VideoCallFragmentBinding;
import com.example.weknot_android.viewmodel.VideoCallViewModel;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class VideoCallFragment extends BaseFragment<VideoCallFragmentBinding> {

    private VideoCallViewModel videoCallViewModel;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initViewModel();
        initVideoCall();

        observeVideoCallViewModel();

        clickEvent();
    }

    private void initViewModel() {
        videoCallViewModel = ViewModelProviders.of(this).get(VideoCallViewModel.class);
    }

    private void initVideoCall() {
        URL serverURL;
        try {
            serverURL = new URL("https://meet.jit.si");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("Invalid server URL!");
        }
        JitsiMeetConferenceOptions defaultOptions
                = new JitsiMeetConferenceOptions.Builder()
                .setServerURL(serverURL)
                .setWelcomePageEnabled(false)
                .build();
        JitsiMeet.setDefaultConferenceOptions(defaultOptions);
    }

    private void observeVideoCallViewModel() {
        videoCallViewModel.getData().observe(this, videoCall -> {
            JitsiMeetConferenceOptions options
                    = new JitsiMeetConferenceOptions.Builder()
                    .setRoom(Integer.toString(videoCall.getRoomIdx()))
                    .build();
            JitsiMeetActivity.launch(getContext(), options);
        });
    }

    private void clickEvent() {
        binding.videoCallBtn.setOnClickListener(v -> videoCallViewModel.requestCall());
    }

    @Override
    protected int layoutId() {
        return 0;
    }
}
