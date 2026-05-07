package com.example.nikeapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nikeapp.databinding.ItemProductHorizontalBinding

class ProductHorizontalAdapter(private var productList: MutableList<ProductData>) :
    RecyclerView.Adapter<ProductHorizontalAdapter.ProductHorizontalViewHolder>() {

    inner class ProductHorizontalViewHolder(val binding: ItemProductHorizontalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: ProductData) {
            binding.itemProductHNameTv.text = product.name
            binding.itemProductHPriceTv.text = product.price
            binding.itemProductHIv.setImageResource(product.imageRes)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHorizontalViewHolder {
        val binding = ItemProductHorizontalBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ProductHorizontalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductHorizontalViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    override fun getItemCount(): Int = productList.size
}