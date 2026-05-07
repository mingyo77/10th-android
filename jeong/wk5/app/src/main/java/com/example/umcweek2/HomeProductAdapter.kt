package com.example.umcweek2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.umcweek2.databinding.ItemHomeProductBinding

class HomeProductAdapter(
    private val items: List<Product>,
    private val onClickItem: (Product) -> Unit
) : RecyclerView.Adapter<HomeProductAdapter.HomeProductViewHolder>() {

    inner class HomeProductViewHolder(
        private val binding: ItemHomeProductBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Product) {
            binding.ivProduct.setImageResource(item.imageResId)
            binding.tvName.text = item.name
            binding.tvPrice.text = item.price
            binding.root.setOnClickListener { onClickItem(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeProductViewHolder {
        val binding = ItemHomeProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HomeProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeProductViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}
