package com.example.weknot_android.base

import android.content.Intent
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.weknot_android.databinding.AppBarBinding

abstract class BaseActivity<VB : ViewDataBinding> : AppCompatActivity() {
    protected lateinit var binding: VB
    protected lateinit var appBarBinding: AppBarBinding

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, layoutId())!!

        try {
            val appBarField = binding.javaClass.getField("appbarLayout")
            appBarBinding = appBarField.get(binding) as AppBarBinding
        }
        catch (e: NoSuchFieldException) {

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.unbind()
    }

    override fun setRequestedOrientation(requestedOrientation: Int) {
        if (VERSION.SDK_INT != VERSION_CODES.O) {
            super.setRequestedOrientation(requestedOrientation)
        }
    }

    @LayoutRes
    protected abstract fun layoutId(): Int

    protected fun startActivity(activity: Class<*>) {
        startActivity(Intent(this, activity))
    }

    protected fun startActivityWithFinish(activity: Class<*>) {
        startActivity(Intent(this, activity))
        finish()
    }
}