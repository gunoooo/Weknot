package com.example.weknot_android.base

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.weknot_android.base.viewmodel.BaseItemViewModel

abstract class BaseViewHolder<T, VM: BaseItemViewModel<*,*>>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    protected lateinit var viewModel: VM

    abstract fun bind(data: T)
}