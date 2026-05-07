package com.example.umc

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.umc.databinding.ItemProductBinding

class ProductAdapter(
    private val productList: List<Product>,
    private val itemClick: ((Product) -> Unit)? = null
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(
        private val binding: ItemProductBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.ivProduct.setImageResource(product.imageResId)
            binding.tvProductName.text = product.name
            binding.tvProductPrice.text = product.price
            binding.tvProductSubInfo.text = product.subInfo

            if (product.isWish) {
                binding.ivWish.setImageResource(android.R.drawable.btn_star_big_on)
            } else {
                binding.ivWish.setImageResource(android.R.drawable.btn_star_big_off)
            }

            binding.root.setOnClickListener {
                itemClick?.invoke(product)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    override fun getItemCount(): Int = productList.size
}