package com.example.movielover.utils.viewUtils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.Window
import android.view.WindowManager
import com.example.movielover.databinding.LayoutProgressDialogBinding

object ProgressDialogHelper {

    private lateinit var dialog: Dialog
    fun progressDialog(context: Context, message: String = ""): Dialog {
        dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val layoutBinding = LayoutProgressDialogBinding.inflate(LayoutInflater.from(context))
//        val view = LayoutInflater.from(context).inflate(R.layout.layout_progress_dialog, null)
//        (view.findViewById(R.id.tvMessage) as TextView).text = message
        layoutBinding.tvMessage.text = message
        dialog.setContentView(layoutBinding.root)
        dialog.setCancelable(true)
        dialog.window!!.setBackgroundDrawable(
            ColorDrawable(Color.TRANSPARENT)
        )
        dialog.window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        return dialog
    }

}