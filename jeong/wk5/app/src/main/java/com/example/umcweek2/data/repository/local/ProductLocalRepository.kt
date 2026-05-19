package com.example.umcweek2.data.repository.local

import com.example.umcweek2.Product

interface ProductLocalRepository {
    suspend fun getAllProducts(): List<Product>
    suspend fun getWishlistProducts(): List<Product>
    suspend fun getProduct(productId: Int): Product?
    suspend fun toggleWishlist(productId: Int)
}
