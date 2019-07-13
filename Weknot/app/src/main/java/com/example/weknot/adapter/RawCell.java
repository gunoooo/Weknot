package com.example.weknot.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.weknot.R;

public class RawCell extends RecyclerView.ViewHolder {

    public TextView roomNumber;
    public TextView masterName;
    public TextView roomName;

    public RawCell(View view) {
        super(view);

        initData(view);
    }

    private void initData(View view) {

        roomNumber = view.findViewById(R.id.roomNumber);
        masterName = view.findViewById(R.id.masterName);
        roomName = view.findViewById(R.id.roomName);
    }
}