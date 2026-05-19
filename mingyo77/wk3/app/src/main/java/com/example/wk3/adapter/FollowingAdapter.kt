package com.example.wk3.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wk3.data.UserData
import com.example.wk3.databinding.ItemFollowingBinding

class FollowingAdapter(private var followingList: List<UserData>):
    RecyclerView.Adapter<FollowingAdapter.FollowingViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowingViewHolder {
        val binding = ItemFollowingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowingViewHolder, position: Int){
        holder.bind(followingList[position])
    }

    override fun getItemCount(): Int = followingList.size

    fun updateData(newList: List<UserData>){
        followingList = newList
        notifyDataSetChanged()
    }
    class FollowingViewHolder(private val binding: ItemFollowingBinding):
            RecyclerView.ViewHolder(binding.root){
        fun bind(user: UserData){
            binding.itemFollowingName.text = user.firstName

            Glide.with(itemView.context)
                .load(user.avatar)
                .circleCrop()
                .into(binding.itemFollowingImg)
        }
            }
}