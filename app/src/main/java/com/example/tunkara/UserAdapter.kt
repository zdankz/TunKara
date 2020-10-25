package com.example.tunkara

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tunkara.R
import com.example.tunkara.entity.User

class UserAdapter(val users: ArrayList<User>) : RecyclerView.Adapter<UserViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.item_user_layout, parent, false)
        return UserViewHolder(v)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.bind(user)
    }
}