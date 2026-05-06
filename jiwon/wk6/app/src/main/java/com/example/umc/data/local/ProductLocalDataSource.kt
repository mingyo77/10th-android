package com.example.umc.data.local

import com.example.umc.R
import com.example.umc.data.model.Product
import javax.inject.Inject
import javax.inject.Singleton

interface ProductLocalDataSource {
    fun getHomeProducts(): List<Product>
    fun getBuyAllProducts(): List<Product>
    fun getWishlistProducts(): List<Product>
}

@Singleton
class DefaultProductLocalDataSource @Inject constructor() : ProductLocalDataSource {
    private val allProducts = listOf(
        Product("Nike Air Max", "Men's Shoes", "US$115", R.drawable.shoe),
        Product("Nike Everyday Socks", "Training Socks", "US$18", R.drawable.sock),
        Product("Nike White Runner", "Women's Shoes", "US$125", R.drawable.whiteshoe),
        Product("Nike Court Vision", "Lifestyle Shoes", "US$89", R.drawable.shoe)
    )

    override fun getHomeProducts(): List<Product> = allProducts

    override fun getBuyAllProducts(): List<Product> = allProducts

    override fun getWishlistProducts(): List<Product> = listOf(
        Product("Nike Air Max", "찜한 상품", "US$115", R.drawable.shoe),
        Product("Nike Everyday Socks", "찜한 상품", "US$18", R.drawable.sock)
    )
}
