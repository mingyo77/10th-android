package com.example.wk3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wk3.databinding.ItemProductBinding

class ProductAdapter(val productList: ArrayList<Product>):
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

        interface MyItemClickListener {
            fun onItemClick(product: Product)
        }
        private lateinit var mItemClickListener: MyItemClickListener
        fun setMyItemClickListener(itemClickListener: MyItemClickListener) {
            mItemClickListener = itemClickListener
        }
    //뷰홀더: item_product.xml의 뷰들을 붙잡는 역할
    class ProductViewHolder(val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.ivNikeShoes.setImageResource(product.image)
            binding.tvNikeShoes.text = product.name
            binding.textView3.text = product.price
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }
    override fun getItemCount(): Int {
        return productList.size
    }
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(productList[position])
        holder.itemView.setOnClickListener {
            mItemClickListener.onItemClick(productList[position])
        }
    }
}


