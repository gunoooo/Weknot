package com.example.weknot.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weknot.R;

public class SocialRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private boolean type;

    private View view;

    public SocialRecyclerViewAdapter(boolean type) {

        this.type = type;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_list_item,parent,false);

        return new RawCell(view, type);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    private static class RawCell extends RecyclerView.ViewHolder {

        private final boolean REQUEST = false;
        private final boolean FRIEND = true;

        private ImageView userPhoto;
        private TextView userName;
        private TextView userScore;

        private TextView acceptBtn;
        private TextView cutBtn;

        public RawCell(View view, boolean type) {
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
}
