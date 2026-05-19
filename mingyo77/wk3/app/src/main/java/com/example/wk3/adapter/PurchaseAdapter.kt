package com.example.wk3.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wk3.MainActivity
import com.example.wk3.data.Product
import com.example.wk3.databinding.ItemPurchaseProductBinding

class PurchaseAdapter(
    val purchaseList: ArrayList<Product>,
    private val onWishClick: (Product) -> Unit,
    private val onItemClick: (Product) -> Unit) :
    RecyclerView.Adapter<PurchaseAdapter.PurchaseViewHolder>() {

    interface MyItemClickListener {
        fun onItemClick(product: Product)
    }
    private lateinit var mItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener: MyItemClickListener) {
        mItemClickListener = itemClickListener
    }

    // 아이템 갯수
    override fun getItemCount(): Int = purchaseList.size

    // 틀 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PurchaseViewHolder {
        val binding = ItemPurchaseProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PurchaseViewHolder(binding)
    }

    // 틀에 데이터 넣기
    override fun onBindViewHolder(holder: PurchaseViewHolder, position: Int) {
        holder.bind(purchaseList[position])

        holder.itemView.setOnClickListener {
            mItemClickListener.onItemClick(purchaseList[position])
        }
    }

    // 뷰홀더
    inner class PurchaseViewHolder(val binding: ItemPurchaseProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            // [중요] 액티비티를 직접 찾지 않고, 이미 세팅된 product의 데이터만 보여줍니다.
            binding.ivPurchaseWishHeart.isSelected = product.isWish
            binding.ivPurchaseProduct.setImageResource(product.image)
            binding.tvPurchaseProductName.text = product.name
            binding.tvPurchaseProductExplain.text = product.explain
            binding.tvPurchaseProductPrice.text = product.price

            // 아이템 자체 클릭
            binding.root.setOnClickListener {
                onItemClick(product)
            }

            // 하트(위시) 클릭
            binding.ivPurchaseWishHeart.setOnClickListener {
                it.isSelected = !it.isSelected
                product.isWish = it.isSelected
                // 클릭되었다는 사실만 밖(프래그먼트)으로 알립니다.
                onWishClick(product)
            }
        }
    }
    fun clear() {
        purchaseList.clear()
        notifyDataSetChanged()
    }
    fun updateData(newList: List<Product>) {
        purchaseList.clear()
        purchaseList.addAll(newList)
        notifyDataSetChanged()
    }

}