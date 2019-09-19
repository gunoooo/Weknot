package com.example.weknot_android.base.fragment

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.weknot_android.R
import com.example.weknot_android.base.viewmodel.BaseViewModel

abstract class BaseListFragment<VB : ViewDataBinding, VM : BaseViewModel<*>> : BaseFragment<VB, VM>() {

    private var isOpenWriteBtn : Boolean = true

    protected lateinit var animAddShow : Animation
    protected lateinit var animAddHide : Animation

    protected lateinit var scrollListener: RecyclerView.OnScrollListener

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    private fun setUp() {
        setUpAnim()
        setUpScrollListener()
    }

    private fun setUpAnim() {
        animAddShow = AnimationUtils.loadAnimation(context, R.anim.animation_add_show)
        animAddHide = AnimationUtils.loadAnimation(context, R.anim.animation_add_hide)
    }

    private fun setUpScrollListener() {
        scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (dy > 10) {
                    if (isOpenWriteBtn) {
                        btnHide()
                        isOpenWriteBtn = false
                    }
                }
                else if (dy < -10) {
                    if (!isOpenWriteBtn) {
                        btnShow()
                        isOpenWriteBtn = true
                    }
                }
            }
        }
    }

    protected abstract fun btnShow()

    protected abstract fun btnHide()
}