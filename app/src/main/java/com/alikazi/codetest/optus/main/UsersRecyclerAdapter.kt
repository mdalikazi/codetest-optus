package com.alikazi.codetest.optus.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alikazi.codetest.optus.R
import com.alikazi.codetest.optus.databinding.RecyclerItemUserBinding
import com.alikazi.codetest.optus.models.User

class UsersRecyclerAdapter(context: Context?, private val listener: OnUserItemClickListener) :
    ListAdapter<User, UsersRecyclerAdapter.UserItemViewHolder>(DIFF_UTIL) {

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =
                oldItem.id == newItem.id
        }
    }

    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemViewHolder {
        val binding: RecyclerItemUserBinding = DataBindingUtil.inflate(
            inflater, R.layout.recycler_item_user, parent, false)
        return UserItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class UserItemViewHolder(private var binding: RecyclerItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.user = user
            binding.onUserItemClickListener = listener
            binding.executePendingBindings()
        }

    }

    interface OnUserItemClickListener {
        fun onUserClicked(userId: Int)
    }

}