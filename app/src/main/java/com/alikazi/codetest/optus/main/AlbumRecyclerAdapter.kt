package com.alikazi.codetest.optus.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alikazi.codetest.optus.R
import com.alikazi.codetest.optus.databinding.RecyclerItemAlbumBinding
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
        val binding: RecyclerItemAlbumBinding = DataBindingUtil.inflate(
            inflater, R.layout.recycler_item_album, parent, false)
        return AlbumItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlbumItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class AlbumItemViewHolder(private val binding: RecyclerItemAlbumBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(photo: Photo) {
            binding.photo = photo
            binding.onAlbumItemClickListener = listener
            binding.executePendingBindings()
        }
    }

    interface OnAlbumItemClickListener {
        fun onAlbumItemClicked(photo: Photo)
    }

}