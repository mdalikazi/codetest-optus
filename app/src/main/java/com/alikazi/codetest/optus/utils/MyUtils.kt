package com.alikazi.codetest.optus.utils

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alikazi.codetest.optus.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.google.android.material.snackbar.Snackbar

fun View.showSnackbar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()
}

@BindingAdapter("showWhile")
fun showWhile(view: View, shouldShow: Boolean) {
    view.visibility = if (shouldShow) View.VISIBLE else View.GONE
}

@BindingAdapter("setAdapter")
fun setAdapter(recyclerView: RecyclerView, adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
    recyclerView.adapter = adapter
}

@BindingAdapter("showImageWithGlide", "imageProgressBar", requireAll = false)
fun showImageWithGlide(imageView: ImageView, url: String, progressBar: ProgressBar?) {
    // Workaround for an issue with via.placeholder.com
    val glideUrl = GlideUrl(
        url,
        LazyHeaders.Builder().addHeader("User-Agent", "your-user-agent").build())
    Glide.with(imageView)
        .asDrawable()
        .load(glideUrl)
        .apply(RequestOptions().error(R.drawable.ic_error))
        .transition(DrawableTransitionOptions().crossFade())
        .addListener(object : RequestListener<Drawable> {
            override fun onLoadFailed(e: GlideException?,
                                      model: Any?,
                                      target: Target<Drawable>?,
                                      isFirstResource: Boolean): Boolean {
                DLog.d("onLoadFailed ${e?.message}")
                progressBar?.visibility = View.GONE
                return false
            }

            override fun onResourceReady(resource: Drawable?,
                                         model: Any?,
                                         target: Target<Drawable>?,
                                         dataSource: DataSource?,
                                         isFirstResource: Boolean): Boolean {
                progressBar?.visibility = View.GONE
                return false
            }
        })
        .into(imageView)
}