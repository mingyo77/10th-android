package com.example.wk3.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wk3.data.Product
import com.example.wk3.databinding.ItemProductBinding

class ProductAdapter(val productList: ArrayList<Product>):
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    // 클릭 리스너 설정
    interface MyItemClickListener {
        fun onItemClick(product: Product)
    }
    private lateinit var mItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener: MyItemClickListener) {
        mItemClickListener = itemClickListener
    }

    //  아이템 갯수
    override fun getItemCount(): Int = productList.size

    //  틀 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    // 틀에 데이터 넣기
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(productList[position]) // 데이터 넣기

        // 클릭 이벤트 연결
        holder.itemView.setOnClickListener {
            mItemClickListener.onItemClick(productList[position])
        }
    }

    // 뷰홀더
    class ProductViewHolder(val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.ivNikeShoes.setImageResource(product.image)
            binding.tvNikeShoes.text = product.name
            binding.textView3.text = product.price
        }
    }
}