package com.example.weknot.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weknot.R;
import com.example.weknot.data.OpenChat;

import java.util.ArrayList;

public class OpenChatRecyclerViewAdapter extends RecyclerView.Adapter<OpenChatRawCell> {

    private View view;
    private ArrayList<OpenChat> data;

    public OpenChatRecyclerViewAdapter() {

        data = new ArrayList<>();
    }

    public void setData(ArrayList<OpenChat> data) {

        this.data = data;
    }

    @NonNull
    @Override
    public OpenChatRawCell onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_list_item,parent,false);

        return new OpenChatRawCell(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OpenChatRawCell holder, int position) {

        OpenChat chat = data.get(position);

        holder.roomNumber.setText(chat.getRoomNumber());
        holder.masterName.setText(chat.getMasterName());
        holder.roomName.setText(chat.getRoomName());
    }

    @Override
    public int getItemCount() {

        return data.size();
    }
}
