package com.example.weknot_android.widget.recyclerview.holder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weknot_android.databinding.OpenChatItemBinding;
import com.example.weknot_android.databinding.SocialItemBinding;

public class SocialViewHolder extends RecyclerView.ViewHolder {

    private SocialItemBinding binding;

    public SocialViewHolder(@NonNull View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }
}
