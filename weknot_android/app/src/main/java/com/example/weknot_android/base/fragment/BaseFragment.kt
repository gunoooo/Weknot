package com.example.weknot_android.base.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.weknot_android.base.viewmodel.BaseViewModel

abstract class BaseFragment<VB : ViewDataBinding, VM : BaseViewModel<*>> : Fragment() {
    protected lateinit var binding: VB
    protected lateinit var currentView: View
    protected lateinit var viewModel: VM

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    protected abstract fun getViewModel(): Class<VM>

    protected abstract fun getBindingVariable(): Int

    protected abstract fun initObserver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.viewModel = if(::viewModel.isInitialized) viewModel else ViewModelProviders.of(this).get(getViewModel())
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
        setUp()
        initBaseObserver()
        initObserver()
    }

    private fun setUp() {
        binding.setVariable(getBindingVariable(),viewModel)
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }

    private fun initBaseObserver() {
        with(viewModel) {
            onErrorEvent.observe(this@BaseFragment, Observer {
                simpleToast(it.message)
            })
        }
    }

    protected fun startActivity(activity: Class<*>) {
        startActivity(Intent(context, activity))
    }

    protected fun startActivityWithFinish(activity: Class<*>) {
        startActivityWithFinish(Intent(context, activity))
    }

    protected fun startActivityWithFinish(intent: Intent) {
        startActivity(intent)
        activity!!.finish()
    }

    protected fun simpleToast(message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    protected fun simpleToast(message: Int) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun <T : Fragment?> newInstance(fragment: T): T {
            val args = Bundle()
            fragment!!.arguments = args
            return fragment
        }
    }
}