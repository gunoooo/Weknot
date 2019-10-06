package com.example.weknot_android.base

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.weknot_android.base.viewmodel.BaseItemViewModel
import java.lang.ref.WeakReference

abstract class BaseViewHolder<N>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private lateinit var navigator: WeakReference<N>

    fun getNavigator(): N {
        return navigator.get()!!
    }
    fun setNavigator(navigator: N) {
        this.navigator = WeakReference(navigator)
    }
}