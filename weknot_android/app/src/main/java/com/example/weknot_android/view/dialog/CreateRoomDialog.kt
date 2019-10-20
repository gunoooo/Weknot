package com.example.weknot_android.view.dialog

import android.content.Context
import android.content.Intent
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
import com.example.weknot_android.view.activity.ChatActivity
import com.example.weknot_android.viewmodel.CreateRoomViewModel
import com.example.weknot_android.widget.SingleLiveEvent

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
                chatRoom.roomType = types[selectedPosition]

                if (isEmpty()) {
                    simpleToast(R.string.empty_message)
                    return@Observer
                }
                getUser()
            })

            closeEvent.observe(this@CreateRoomDialog, Observer {
                this@CreateRoomDialog.dismiss()
            })

            openChatRoom.observe(this@CreateRoomDialog, Observer {
                val intent = Intent(context, ChatActivity::class.java)
                intent.putExtra("key", it)
                startActivityWithFinish(intent)
            })
        }
    }

    fun show(fragmentManager: FragmentManager?) {
        super.show(fragmentManager!!, TAG)
    }

    private lateinit var types: Array<String>

    override fun onAttach(context: Context) {
        super.onAttach(context)

        types = resources.getStringArray(R.array.room_type)
    }

    private fun isEmpty(): Boolean {
        return viewModel.chatRoom.roomName == null || viewModel.chatRoom.roomType == null
    }

    override fun dismiss() {
        super.dismiss()
        binding.roomNameText.setText("")
        binding.roomTypeSpinner.setSelection(0)
    }

}