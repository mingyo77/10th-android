package com.example.umc.data.repository

import com.example.umc.data.local.ProductLocalDataSource
import com.example.umc.data.model.Product
import javax.inject.Inject
import javax.inject.Singleton

interface LocalProductRepository {
    fun getHomeProducts(): List<Product>
    fun getBuyAllProducts(): List<Product>
    fun getWishlistProducts(): List<Product>
}

@Singleton
class LocalProductRepositoryImpl @Inject constructor(
    private val productLocalDataSource: ProductLocalDataSource
) : LocalProductRepository {
    override fun getHomeProducts(): List<Product> = productLocalDataSource.getHomeProducts()

    override fun getBuyAllProducts(): List<Product> = productLocalDataSource.getBuyAllProducts()

    override fun getWishlistProducts(): List<Product> = productLocalDataSource.getWishlistProducts()
}
