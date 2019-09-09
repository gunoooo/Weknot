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
import com.example.weknot_android.R
import com.example.weknot_android.base.BaseActivity
import com.example.weknot_android.databinding.FeedWriteActivityBinding
import com.example.weknot_android.viewmodel.FeedViewModel
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody.Part
import okhttp3.RequestBody
import java.io.File
import java.io.IOException
import java.util.*

class FeedWriteActivity : BaseActivity<FeedWriteActivityBinding>() {
    private lateinit var feedViewModel: FeedViewModel

    private val PICK_FROM_ALBUM = 1
    private val REQUEST_IMAGE_CROP = 2
    private var photoURI: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        observeFeedViewModel()
    }

    private fun init() {
        initAppbar()
        initViewModel()
        initView()
    }

    private fun observeFeedViewModel() {
        feedViewModel.getSuccessMessage().observe(this, androidx.lifecycle.Observer { message: String ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            startActivityWithFinish(MainActivity::class.java)
        })
    }

    private fun initView() {
        goToAlbum()
    }

    private fun initViewModel() {
        feedViewModel = ViewModelProviders.of(this).get(FeedViewModel::class.java)
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
                photoURI = data.data
                cropImage()
            }
            REQUEST_IMAGE_CROP -> if (resultCode == Activity.RESULT_OK) {
                Glide.with(this).load(feedViewModel.pictureUri.value).into(binding.image)
            }
        }
    }

    private fun cropImage() {
        val file = File(Environment.getExternalStorageDirectory().toString() + "/Weknot")
        if (!file.exists()) file.mkdirs()
        feedViewModel.pictureFile.value = File(Environment.getExternalStorageDirectory().toString() + "/Weknot/" + Random().nextInt(100000000).toString() + ".jpg")
        try {
            feedViewModel.pictureFile.value!!.createNewFile()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        feedViewModel.pictureUri.setValue(Uri.fromFile(feedViewModel.pictureFile.value))
        val cropIntent = Intent("com.android.camera.action.CROP")
        cropIntent.flags = Intent.FLAG_GRANT_WRITE_URI_PERMISSION
        cropIntent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        cropIntent.setDataAndType(photoURI, "image/*")
        cropIntent.putExtra("aspectX", 1)
        cropIntent.putExtra("aspectY", 1)
        cropIntent.putExtra("scale", true)
        cropIntent.putExtra("output", feedViewModel.pictureUri.value)
        startActivityForResult(cropIntent, REQUEST_IMAGE_CROP)
    }

    private fun tedPermission() {
        val permissionListener: PermissionListener = object : PermissionListener {
            override fun onPermissionGranted() {}
            override fun onPermissionDenied(deniedPermissions: ArrayList<String?>?) {
                Toast.makeText(applicationContext, "접근을 허용해야 사진을 등록할 수 있습니다", Toast.LENGTH_LONG).show()
            }
        }
        TedPermission.with(this)
                .setPermissionListener(permissionListener)
                .setPermissions(permission.WRITE_EXTERNAL_STORAGE, permission.READ_EXTERNAL_STORAGE, permission.CAMERA)
                .check()
    }

    private fun initAppbar() {
        val actionBar = supportActionBar

        actionBar?.setDisplayHomeAsUpEnabled(true)

        appBarBinding.textView.setText(R.string.feed_write_title)
        Glide.with(this).load(R.drawable.ic_next).into(appBarBinding.menu)

        appbarClickEvent()
    }

    private fun appbarClickEvent() {
        appBarBinding.back.setOnClickListener { startActivityWithFinish(MainActivity::class.java) }

        appBarBinding.menu.setOnClickListener {
            setRequest()
            feedViewModel.createFeed()
        }
    }

    private fun setRequest() {
        val requestFile: RequestBody = RequestBody.create("image/*".toMediaTypeOrNull(),feedViewModel.pictureFile.value!!)

        feedViewModel.picture.value = Part.createFormData("picture", feedViewModel.pictureFile.value!!.name, requestFile)

        feedViewModel.comment.value = RequestBody.create("text/plain".toMediaTypeOrNull(), binding.commentInput.text.toString())

    }

    override fun layoutId(): Int {
        return R.layout.feed_write_activity
    }
}