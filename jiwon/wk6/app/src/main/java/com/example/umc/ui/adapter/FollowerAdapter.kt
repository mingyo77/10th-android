package com.example.umc.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.umc.data.model.ReqResUser
import com.example.umc.databinding.ItemFollowerBinding

class FollowerAdapter : RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>() {
    private val followers = mutableListOf<ReqResUser>()

    fun submitList(newFollowers: List<ReqResUser>) {
        followers.clear()
        followers.addAll(newFollowers)
        notifyDataSetChanged()
    }

    inner class FollowerViewHolder(private val binding: ItemFollowerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: ReqResUser) {
            binding.tvFollowerName.text = user.nickname
            binding.tvFollowerEmail.text = user.email
            Glide.with(binding.ivFollowerAvatar)
                .load(user.avatar)
                .circleCrop()
                .into(binding.ivFollowerAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val binding = ItemFollowerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.bind(followers[position])
    }

    override fun getItemCount(): Int = followers.size
}
