package com.alikazi.codetest.optus.utils

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.View
import androidx.fragment.app.Fragment
import com.alikazi.codetest.optus.R
import com.alikazi.codetest.optus.main.MainActivity
import com.google.android.material.snackbar.Snackbar

fun View.showSnackbar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()
}

fun Fragment.onBackPressedInFragment() {
    if (childFragmentManager.backStackEntryCount > 0) {
        childFragmentManager.popBackStack()
    } else {
        (activity as MainActivity).onBackPressed()
    }
}

fun Context.showAlertDialog(title: String?, message: String,
                            okText: String?, okClickListener: DialogInterface.OnClickListener?,
                            cancelText: String?, cancelClickListener: DialogInterface.OnClickListener?) {
    val builder = AlertDialog.Builder(this, R.style.AppTheme_DialogOverlay)
    builder.setCancelable(false)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(okText ?: this.getString(R.string.ok),
                    okClickListener ?: DialogInterface.OnClickListener { dialog, _ ->
                        dialog.dismiss()
                    })
            .setNegativeButton(cancelText ?: this.getString(R.string.cancel),
                    cancelClickListener ?: DialogInterface.OnClickListener { dialog, _ ->
                        dialog.dismiss()
                    })
            .create()
            .show()
}