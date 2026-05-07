package com.example.umcweek2.user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.umcweek2.databinding.ItemFollowingBinding
import com.example.umcweek2.network.ReqresUserDto

class FollowingAdapter(
    private var items: List<ReqresUserDto>
) : RecyclerView.Adapter<FollowingAdapter.FollowingViewHolder>() {

    inner class FollowingViewHolder(
        private val binding: ItemFollowingBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ReqresUserDto) {
            binding.ivFollowing.load(item.avatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowingViewHolder {
        val binding = ItemFollowingBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FollowingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowingViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun submitList(newItems: List<ReqresUserDto>) {
        items = newItems
        notifyDataSetChanged()
    }
}
