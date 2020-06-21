package com.alikazi.codetest.optus.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import com.alikazi.codetest.optus.main.MainActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.material.snackbar.Snackbar

fun View.showSnackbar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()
}

fun View.processVisibility(shouldShow: Boolean) {
    this.visibility = if (shouldShow) View.VISIBLE else View.GONE
}

fun Fragment.onBackPressedInFragment() {
    if (childFragmentManager.backStackEntryCount > 0) {
        childFragmentManager.popBackStack()
    } else {
        (activity as MainActivity).onBackPressed()
    }
}

fun ImageView.showImageWithGlide(url: String, progressBar: ProgressBar) {
    // Workaround for an issue with via.placeholder.com
    val glideUrl = GlideUrl(
        url,
        LazyHeaders.Builder().addHeader("User-Agent", "your-user-agent").build())
    Glide.with(this)
        .asDrawable()
        .load(glideUrl)
        .transition(DrawableTransitionOptions().crossFade())
        .addListener(object : RequestListener<Drawable> {
            override fun onLoadFailed(e: GlideException?,
                                      model: Any?,
                                      target: Target<Drawable>?,
                                      isFirstResource: Boolean): Boolean {
                DLog.d("onLoadFailed ${e?.message}")
                progressBar.visibility = View.GONE
                return false
            }

            override fun onResourceReady(resource: Drawable?,
                                         model: Any?,
                                         target: Target<Drawable>?,
                                         dataSource: DataSource?,
                                         isFirstResource: Boolean): Boolean {
                progressBar.visibility = View.GONE
                return false
            }
        })
        .into(this)
}