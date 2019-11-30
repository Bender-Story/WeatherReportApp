package com.rahul.weatherreportapp.component

import android.content.Context
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.databinding.BindingAdapter
import com.rahul.weatherreportapp.R

class ActivityUIExt( val context : Context){
    // Build Error dialog.
    fun buildDialog(error:String, title:String=context.getString(R.string.dialog_title),
                    positiveText:String=context.getString(R.string.dialog_retry),
                    negativeText:String=context.getString(R.string.dialog_no),
                    onReload:() -> Unit,
                    onNegitive:()->Unit){
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(error)
        builder.setCancelable(false)
        builder.setPositiveButton(positiveText){dialog, which ->
        onReload.invoke()
            dialog.dismiss()
        }

        builder.setNegativeButton(negativeText){dialog,which ->
            onNegitive.invoke()
            dialog.dismiss()
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    fun buildLoadingDialog(): AlertDialog {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(context.getString(R.string.loading_head))
        builder.setMessage(context.getString(R.string.loading_note))
        builder.setCancelable(false)

        val dialog: AlertDialog = builder.create()
        dialog.show()
        return dialog
    }
}
