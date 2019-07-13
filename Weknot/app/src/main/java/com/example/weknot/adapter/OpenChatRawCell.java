package com.example.weknot.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.weknot.R;

public class OpenChatRawCell extends RecyclerView.ViewHolder {

    public TextView roomNumber;
    public TextView masterName;
    public TextView roomName;

    public OpenChatRawCell(View view) {
        super(view);

        initData(view);
    }

    private void initData(View view) {

        roomNumber = view.findViewById(R.id.roomNumber);
        masterName = view.findViewById(R.id.masterName);
        roomName = view.findViewById(R.id.roomName);
    }
}