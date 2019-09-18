package com.example.weknot_android.base.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.weknot_android.base.BaseViewModel

abstract class BaseFragment<VB : ViewDataBinding, VM : BaseViewModel<*, *>> : Fragment() {
    protected lateinit var binding: VB
    protected lateinit var currentView: View
    protected lateinit var viewModel: VM

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    protected abstract fun getViewModel(): Class<VM>

    protected abstract fun getBindingVariable(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.viewModel = if(::viewModel.isInitialized) ViewModelProviders.of(this).get(getViewModel()) else viewModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
                inflater, getLayoutId(), container, false)!!
        currentView = binding.root
        return currentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.setVariable(getBindingVariable(),viewModel)
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }

    protected fun startActivity(activity: Class<*>) {
        startActivity(Intent(context, activity))
    }

    protected fun startActivityWithFinish(activity: Class<*>) {
        startActivity(Intent(context, activity))
        getActivity()!!.finish()
    }

    companion object {
        fun <T : Fragment?> newInstance(fragment: T): T {
            val args = Bundle()
            fragment!!.arguments = args
            return fragment
        }
    }
}