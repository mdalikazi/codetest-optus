package com.alikazi.codetest.optus.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alikazi.codetest.optus.R
import com.alikazi.codetest.optus.models.Photo
import com.alikazi.codetest.optus.utils.showImageWithGlide

class AlbumRecyclerAdapter(context: Context?, private val listener: OnAlbumItemClickListener) :
    ListAdapter<Photo, AlbumRecyclerAdapter.AlbumItemViewHolder>(DIFF_UTIL) {

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<Photo>() {
            override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean =
                oldItem.id == newItem.id
        }
    }

    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumItemViewHolder {
        val view = inflater.inflate(R.layout.recycler_item_album, parent, false)
        return AlbumItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlbumItemViewHolder, position: Int) {
        val photo = getItem(position)
        holder.albumTitle.text = photo.title
        holder.albumThumbnail.showImageWithGlide(photo.thumbnailUrl, holder.albumThumbnailProgressBar)
        holder.itemView.setOnClickListener {
            listener.onAlbumItemClicked(photo)
        }
    }

    inner class AlbumItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val albumThumbnail: ImageView = itemView.findViewById(R.id.albumThumbnail)
        val albumThumbnailProgressBar: ProgressBar = itemView.findViewById(R.id.albumThumbnailProgressBar)
        val albumTitle: TextView = itemView.findViewById(R.id.albumTitle)
    }

    interface OnAlbumItemClickListener {
        fun onAlbumItemClicked(photo: Photo)
    }

}