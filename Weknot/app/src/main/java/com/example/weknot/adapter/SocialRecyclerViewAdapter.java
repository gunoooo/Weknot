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

        return new SocialRawCell(view, type);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
