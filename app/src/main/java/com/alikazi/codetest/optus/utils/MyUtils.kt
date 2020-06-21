package com.alikazi.codetest.optus.utils

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import com.alikazi.codetest.optus.R
import com.alikazi.codetest.optus.main.MainActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
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

fun Context.showImageWithGlide(url: String, imageView: ImageView, progressBar: ProgressBar) {
    Glide.with(this)
        .asBitmap()
        .apply(RequestOptions().centerCrop())
        .transition(BitmapTransitionOptions().crossFade())
        .load(url)
        .addListener(object : RequestListener<Bitmap> {
            override fun onLoadFailed(e: GlideException?,
                                      model: Any?,
                                      target: Target<Bitmap>?,
                                      isFirstResource: Boolean): Boolean {
                progressBar.visibility = View.GONE
                return true
            }

            override fun onResourceReady(resource: Bitmap?,
                                         model: Any?,
                                         target: Target<Bitmap>?,
                                         dataSource: DataSource?,
                                         isFirstResource: Boolean): Boolean {
                progressBar.visibility = View.GONE
                return true
            }
        })
        .into(imageView)
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