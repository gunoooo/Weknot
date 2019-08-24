package com.example.weknot_android.widget.recyclerview.adapter;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weknot_android.R;
import com.example.weknot_android.room.entity.OpenChat.OpenChatRoom;
import com.example.weknot_android.widget.recyclerview.holder.OpenChatViewHolder;

import java.util.List;

public class OpenChatAdapter extends RecyclerView.Adapter<OpenChatViewHolder> {

    private Context context;

    private List<OpenChatRoom> openChatRooms;

    public OpenChatAdapter(Context context, List<OpenChatRoom> openChatRooms) {
        this.context = context;
        this.openChatRooms = openChatRooms;
    }

    @NonNull
    @Override
    public OpenChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OpenChatViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.open_chat_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OpenChatViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return openChatRooms.size();
    }
}
