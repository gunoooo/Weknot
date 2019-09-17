package com.example.weknot_android.view.activity

import android.Manifest.permission
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore.Images.Media
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.weknot_android.BR
import com.example.weknot_android.R
import com.example.weknot_android.base.BaseActivity
import com.example.weknot_android.databinding.FeedWriteActivityBinding
import com.example.weknot_android.view.navigator.FeedWriteNavigator
import com.example.weknot_android.viewmodel.FeedViewModel
import com.example.weknot_android.viewmodel.FeedWriteViewModel
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody.Part
import okhttp3.RequestBody
import java.io.File
import java.io.IOException
import java.util.*

class FeedWriteActivity : BaseActivity<FeedWriteActivityBinding, FeedWriteViewModel>(), FeedWriteNavigator {

    private val PICK_FROM_ALBUM = 1
    private val REQUEST_IMAGE_CROP = 2

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNavigator(this)
        setUp()
    }

    private fun setUp() {
        goToAlbum()
    }

    private fun goToAlbum() {
        tedPermission()
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = Media.CONTENT_TYPE
        startActivityForResult(intent, PICK_FROM_ALBUM)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            PICK_FROM_ALBUM -> {
                if (data == null) {
                    return
                }
                viewModel.tempPictureUri.value = data.data
                cropImage()
            }
            REQUEST_IMAGE_CROP -> if (resultCode == Activity.RESULT_OK) {
                Glide.with(this).load(viewModel.pictureUri.value).into(binding.image)
            }
        }
    }

    private fun cropImage() {
        val file = File(Environment.getExternalStorageDirectory().toString() + "/Weknot")
        if (!file.exists()) file.mkdirs()
        viewModel.pictureFile.value = File(Environment.getExternalStorageDirectory().toString() + "/Weknot/" + Random().nextInt(100000000).toString() + ".jpg")
        try {
            viewModel.pictureFile.value!!.createNewFile()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        viewModel.pictureUri.setValue(Uri.fromFile(viewModel.pictureFile.value))
        val cropIntent = Intent("com.android.camera.action.CROP")
        cropIntent.flags = Intent.FLAG_GRANT_WRITE_URI_PERMISSION
        cropIntent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        cropIntent.setDataAndType(viewModel.tempPictureUri.value, "image/*")
        cropIntent.putExtra("aspectX", 1)
        cropIntent.putExtra("aspectY", 1)
        cropIntent.putExtra("scale", true)
        cropIntent.putExtra("output", viewModel.pictureUri.value)
        startActivityForResult(cropIntent, REQUEST_IMAGE_CROP)
    }
}