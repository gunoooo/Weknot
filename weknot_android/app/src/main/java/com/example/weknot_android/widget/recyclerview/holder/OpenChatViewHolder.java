package com.example.weknot_android.widget.recyclerview.holder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weknot_android.databinding.OpenChatItemBinding;

public class OpenChatViewHolder extends RecyclerView.ViewHolder {

    private OpenChatItemBinding binding;

    public OpenChatViewHolder(@NonNull View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }
}
