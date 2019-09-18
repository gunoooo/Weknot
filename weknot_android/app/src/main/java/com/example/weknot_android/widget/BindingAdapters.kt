package com.example.weknot_android.widget

import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import net.gahfy.mvvmposts.utils.extension.getParentActivity

@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
        view.adapter = adapter
}

@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View,  visibility: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && visibility != null) {
        visibility.observe(parentActivity, Observer { value -> view.visibility = value?:View.VISIBLE})
    }
}

@BindingAdapter("mutableText")
fun setMutableText(view: TextView,  text: MutableLiveData<String>?) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value -> view.text = value?:""})
    }
}

@BindingAdapter("mutableImageDrawable")
fun setMutableImageDrawable(view: ImageView, resid: MutableLiveData<Int>?) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && resid != null) {
        resid.observe(parentActivity, Observer { value -> view.setImageResource(value)})
    }
}

@BindingAdapter("mutableImageUrl")
fun setMutableImageUrl(view: ImageView, url: MutableLiveData<String>?) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && url != null) {
        url.observe(parentActivity, Observer { value -> Glide.with(view.context).load(value).into(view)})
    }
}

@BindingAdapter("mutableImageUri")
fun setMutableImageUri(view: ImageView, uri: MutableLiveData<Uri>?) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && uri != null) {
        uri.observe(parentActivity, Observer { value -> Glide.with(view.context).load(value).into(view)})
    }
}