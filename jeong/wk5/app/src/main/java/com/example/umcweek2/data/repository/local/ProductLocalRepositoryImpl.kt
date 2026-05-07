package com.example.umcweek2.data.repository.local

import com.example.umcweek2.Product
import com.example.umcweek2.data.local.ProductLocalDataSource
import javax.inject.Inject

class ProductLocalRepositoryImpl @Inject constructor(
    private val productLocalDataSource: ProductLocalDataSource
) : ProductLocalRepository {

    override suspend fun getAllProducts(): List<Product> {
        return productLocalDataSource.getAllProducts()
    }

    override suspend fun getWishlistProducts(): List<Product> {
        return productLocalDataSource.getAllProducts().filter { it.isWishlisted }
    }

    override suspend fun getProduct(productId: Int): Product? {
        return productLocalDataSource.getAllProducts().find { it.id == productId }
    }

    override suspend fun toggleWishlist(productId: Int) {
        val updated = productLocalDataSource.getAllProducts().map {
            if (it.id == productId) it.copy(isWishlisted = !it.isWishlisted) else it
        }
        productLocalDataSource.saveProducts(updated)
    }
}
