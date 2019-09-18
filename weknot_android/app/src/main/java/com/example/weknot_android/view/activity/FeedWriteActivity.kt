package com.example.weknot_android.view.activity

import android.R.id
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.weknot_android.BR
import com.example.weknot_android.R
import com.example.weknot_android.base.activity.BasePictureActivity
import com.example.weknot_android.databinding.FeedWriteActivityBinding
import com.example.weknot_android.view.navigator.FeedWriteNavigator
import com.example.weknot_android.viewmodel.FeedWriteViewModel

class FeedWriteActivity : BasePictureActivity<FeedWriteActivityBinding, FeedWriteViewModel>(), FeedWriteNavigator {

    override fun getLayoutId(): Int {
        return R.layout.feed_write_activity
    }

    override fun getViewModel(): Class<FeedWriteViewModel> {
        return FeedWriteViewModel::class.java
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun handleError(throwable: Throwable) {
        Toast.makeText(this,throwable.message,Toast.LENGTH_SHORT).show()
    }

    override fun goToCropPage() {
        goToCropPage(viewModel.tempPictureUri.value, viewModel.pictureUri.value)
    }

    override fun toastBackMessage() {
        Toast.makeText(this, R.string.exist_message, Toast.LENGTH_SHORT).show()
        startActivityWithFinish(MainActivity::class.java)
    }

    override fun openMainActivity() {
        startActivityWithFinish(MainActivity::class.java)
    }

    override fun requestNotOkEvent() {
        viewModel.deleteFile()
    }

    override fun pickNextEvent(data: Intent) {
        viewModel.savePickData(data)
        viewModel.cropImage()
    }

    override fun cropNextEvent() { }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (supportActionBar != null) supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        viewModel.setNavigator(this)
        setUp()
    }

    private fun setUp() {
        tedPermission()
        goToAlbum()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_write, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.home) {
            finish()
            return true
        }
        else if (item.itemId == R.id.menu_write) {
            viewModel.postFeed()
            return true
        }
        return false
    }
}