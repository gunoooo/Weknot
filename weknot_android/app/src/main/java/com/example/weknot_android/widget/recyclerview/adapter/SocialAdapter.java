package com.example.weknot_android.widget.recyclerview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weknot_android.R;
import com.example.weknot_android.room.entity.OpenChat.OpenChatRoom;
import com.example.weknot_android.room.entity.user.Friend;
import com.example.weknot_android.widget.recyclerview.holder.OpenChatViewHolder;
import com.example.weknot_android.widget.recyclerview.holder.SocialViewHolder;

import java.util.List;

public class SocialAdapter extends RecyclerView.Adapter<SocialViewHolder> {

    private Context context;

    private List<Friend> friends;

    public SocialAdapter(Context context, List<Friend> friends) {
        this.context = context;
        this.friends = friends;
    }

    @NonNull
    @Override
    public SocialViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SocialViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.social_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SocialViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return friends.size();
    }
}
