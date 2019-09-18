package com.example.weknot_android.base.activity

import android.content.Intent
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProviders
import com.example.weknot_android.base.BaseViewModel
import com.example.weknot_android.databinding.AppBarBinding

abstract class BaseActivity<VB : ViewDataBinding, VM : BaseViewModel<*, *>> : AppCompatActivity() {
    protected lateinit var binding: VB
    protected lateinit var viewModel: VM

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    protected abstract fun getViewModel(): Class<VM>

    protected abstract fun getBindingVariable(): Int

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        performDataBinding()
    }

    private fun performDataBinding() {
        performViewDataBinding()
        performAppBarDataBinding()
    }

    private fun performViewDataBinding() {
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        this.viewModel = if(::viewModel.isInitialized) ViewModelProviders.of(this).get(getViewModel()) else viewModel
        binding.setVariable(getBindingVariable(), viewModel)
        binding.executePendingBindings()
    }

    private fun performAppBarDataBinding() {
        val appBarBinding: AppBarBinding
        try {
            val appBarField = binding.javaClass.getField("appbarLayout")
            appBarBinding = appBarField.get(binding) as AppBarBinding
            setSupportActionBar(appBarBinding.toolbar)
        }
        catch (e: NoSuchFieldException) {

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if(::binding.isInitialized) binding.unbind()
    }

    override fun setRequestedOrientation(requestedOrientation: Int) {
        if (VERSION.SDK_INT != VERSION_CODES.O) {
            super.setRequestedOrientation(requestedOrientation)
        }
    }

    protected fun startActivity(activity: Class<*>) {
        startActivity(Intent(this, activity))
    }

    protected fun startActivityWithFinish(activity: Class<*>) {
        startActivity(Intent(this, activity))
        finish()
    }
}