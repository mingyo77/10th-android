package com.example.wk8.data

import com.example.wk8.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class ProductRepository(
    private val dataStoreManager: DataStoreManager,
) {
    fun getProducts(): Flow<List<Product>> = dataStoreManager.getProductList()

    suspend fun updateWish(productId: Int, isWish: Boolean) {
        val products = dataStoreManager.getProductList().first().map { product ->
            if (product.id == productId) {
                product.copy(isWish = isWish)
            } else {
                product
            }
        }
        dataStoreManager.saveProductList(products)
    }
}
