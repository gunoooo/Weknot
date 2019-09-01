package com.example.weknot_android.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toolbar
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.weknot_android.R
import com.example.weknot_android.databinding.AppBarBinding

abstract class BaseFragment<VB : ViewDataBinding> : Fragment() {
    protected lateinit var binding: VB
    protected lateinit var currentView: View
    protected lateinit var appBarBinding: AppBarBinding

    protected var isOpenWriteBtn : Boolean = true

    protected lateinit var animToolbarHide : Animation
    protected lateinit var animToolbarShow : Animation

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
                inflater, layoutId(), container, false)!!
        currentView = binding.root

        initData()

        return currentView
    }

    private fun initData() {
        animToolbarShow = AnimationUtils.loadAnimation(context, R.anim.animation_toolbar_show)
        animToolbarHide = AnimationUtils.loadAnimation(context, R.anim.animation_toolbar_hide)

        val appBarField = binding.javaClass.getField("appbarLayout")
        appBarBinding = appBarField.get(binding) as AppBarBinding
    }

    @LayoutRes
    protected abstract fun layoutId(): Int

    companion object {
        fun <T : Fragment?> newInstance(fragment: T): T {
            val args = Bundle()
            fragment!!.arguments = args
            return fragment
        }
    }
}