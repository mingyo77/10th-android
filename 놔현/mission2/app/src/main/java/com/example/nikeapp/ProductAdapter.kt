package com.example.nikeapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nikeapp.databinding.ItemProductBinding

class ProductAdapter(
    private var productList: MutableList<ProductData>,
    private val onHeartClick: (ProductData) -> Unit,
    private val wishlistNames: MutableSet<String> = mutableSetOf()
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: ProductData) {
            binding.itemProductNameTv.text = product.name
            binding.itemProductPriceTv.text = product.price
            binding.itemProductIv.setImageResource(product.imageRes)

            // 하트 상태 설정
            if (wishlistNames.contains(product.name)) {
                binding.itemProductHeartIv.setImageResource(R.drawable.ic_heart_on)
            } else {
                binding.itemProductHeartIv.setImageResource(R.drawable.ic_heart_off)
            }

            // 하트 클릭
            // 하트 클릭
            binding.itemProductHeartIv.setOnClickListener {
                if (wishlistNames.contains(product.name)) {
                    wishlistNames.remove(product.name)
                    binding.itemProductHeartIv.setImageResource(R.drawable.ic_heart_off)
                } else {
                    wishlistNames.add(product.name)
                    binding.itemProductHeartIv.setImageResource(R.drawable.ic_heart_on)
                }
                onHeartClick(product)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    override fun getItemCount(): Int = productList.size

    fun updateWishlist(names: Set<String>) {
        wishlistNames.clear()
        wishlistNames.addAll(names)
        notifyDataSetChanged()
    }
}