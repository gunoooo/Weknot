package com.example.weknot_android.view.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import com.example.weknot_android.BR
import com.example.weknot_android.R
import com.example.weknot_android.base.BaseDialog
import com.example.weknot_android.databinding.CreateRoomDialogBinding
import com.example.weknot_android.viewmodel.CreateRoomViewModel

class CreateRoomDialog : BaseDialog<CreateRoomDialogBinding, CreateRoomViewModel>() {

    private val TAG: String? = CreateRoomDialog::class.java.simpleName

    override fun getLayoutId(): Int {
        return R.layout.create_room_dialog
    }

    override fun getViewModel(): Class<CreateRoomViewModel> {
        return CreateRoomViewModel::class.java
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun initObserver() {
        with(viewModel) {
            createEvent.observe(this@CreateRoomDialog, Observer {
                if (isEmpty()) {
                    simpleToast(R.string.empty_message)
                    return@Observer
                }
                getUser()
            })
        }
    }

    fun show(fragmentManager: FragmentManager?) {
        super.show(fragmentManager!!, TAG)
    }

    private fun isEmpty(): Boolean {
        return viewModel.chatRoom.roomName == null || viewModel.chatRoom.roomType == null
    }

}