package com.alikazi.codetest.optus.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alikazi.codetest.optus.R
import com.alikazi.codetest.optus.models.User

class UsersRecyclerAdapter(context: Context?, private val listener: OnUserItemClickListener) :
    ListAdapter<User, UsersRecyclerAdapter.UserViewHolder>(DIFF_UTIL) {

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =
                oldItem.id == newItem.id
        }
    }

    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = inflater.inflate(R.layout.recycler_item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        holder.userName.text = user.name
        holder.userEmail.text = user.email
        holder.userPhone.text = user.phone
        holder.itemView.setOnClickListener {
            listener.onUserClicked(user.id)
        }
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userName: TextView = itemView.findViewById(R.id.userName)
        val userEmail: TextView = itemView.findViewById(R.id.userEmail)
        val userPhone: TextView = itemView.findViewById(R.id.userPhone)
    }

    interface OnUserItemClickListener {
        fun onUserClicked(userId: Int)
    }

}