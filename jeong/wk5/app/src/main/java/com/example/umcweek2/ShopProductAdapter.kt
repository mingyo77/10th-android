package com.example.umcweek2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.umcweek2.databinding.ItemShopProductBinding

class ShopProductAdapter(
    private val items: List<Product>,
    private val showWishlistButton: Boolean,
    private val onClickItem: (Product) -> Unit,
    private val onClickWishlist: (Product) -> Unit
) : RecyclerView.Adapter<ShopProductAdapter.ShopProductViewHolder>() {

    inner class ShopProductViewHolder(
        private val binding: ItemShopProductBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Product) {
            binding.ivProduct.setImageResource(item.imageResId)
            binding.tvName.text = item.name
            binding.tvSubtitle.text = item.subtitle
            binding.tvColors.text = item.colors
            binding.tvPrice.text = item.price

            binding.btnWishlist.visibility = if (showWishlistButton) View.VISIBLE else View.GONE
            binding.btnWishlist.setImageResource(
                if (item.isWishlisted) R.drawable.ic_heart_filled else R.drawable.ic_heart
            )

            binding.root.setOnClickListener { onClickItem(item) }
            binding.btnWishlist.setOnClickListener { onClickWishlist(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopProductViewHolder {
        val binding = ItemShopProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ShopProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShopProductViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}
