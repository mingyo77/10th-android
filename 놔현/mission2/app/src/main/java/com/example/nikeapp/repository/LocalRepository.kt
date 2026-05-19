package com.example.nikeapp.repository

import com.example.nikeapp.ProductData
import com.example.nikeapp.ProductDataStore

class LocalRepository(private val dataStore: ProductDataStore) {

    suspend fun saveProducts(key: androidx.datastore.preferences.core.Preferences.Key<String>, products: List<ProductData>) {
        dataStore.saveProducts(key, products)
    }

    fun getProducts(key: androidx.datastore.preferences.core.Preferences.Key<String>) =
        dataStore.getProducts(key)

    suspend fun toggleWishlist(product: ProductData) {
        dataStore.toggleWishlist(product)
    }

    fun getWishlistProducts() =
        dataStore.getProducts(com.example.nikeapp.ProductDataStore.WISHLIST_PRODUCTS_KEY)
}