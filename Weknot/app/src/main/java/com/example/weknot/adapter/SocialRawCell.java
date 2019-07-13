package com.example.weknot.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.weknot.R;

public class SocialRawCell extends RecyclerView.ViewHolder {

    private final boolean REQUEST = false;
    private final boolean FRIEND = true;

    private ImageView userPhoto;
    private TextView userName;
    private TextView userScore;

    private TextView acceptBtn;
    private TextView cutBtn;

    public SocialRawCell(View view, boolean type) {
        super(view);

        initData(view);
        checkType(type);
    }

    private void initData(View view) {

        userPhoto = view.findViewById(R.id.userPhotoImageView);
        userName = view.findViewById(R.id.userNameTextView);
        userScore = view.findViewById(R.id.userScoreTextView);

        acceptBtn = view.findViewById(R.id.acceptFriendBtn);
        cutBtn = view.findViewById(R.id.cutFriendBtn);

    }

    private void checkType(boolean type) {

        if (type == REQUEST) {
            acceptBtn.setVisibility(View.VISIBLE);
        }
        else if (type == FRIEND) {
            acceptBtn.setVisibility(View.INVISIBLE);
        }

    }

}
