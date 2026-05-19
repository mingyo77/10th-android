package com.example.umcweek2.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.umcweek2.Product
import com.example.umcweek2.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.productDataStore by preferencesDataStore(name = "product_store")

class ProductLocalDataSource @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val gson = Gson()
    private val productsKey = stringPreferencesKey("products_json")

    private fun defaultProducts(): List<Product> = listOf(
        Product(
            id = 1,
            name = "Nike Everyday Plus Cushioned",
            subtitle = "Training Ankle Socks (6 Pairs)",
            colors = "5 Colours",
            price = "US$10",
            imageResId = R.drawable.image1,
            description = "The Nike Everyday Plus Cushioned Socks bring comfort to your workout with extra cushioning under the heel and forefoot and a snug, supportive arch band."
        ),
        Product(
            id = 2,
            name = "Nike Elite Crew",
            subtitle = "Basketball Socks",
            colors = "7 Colours",
            price = "US$16",
            imageResId = R.drawable.image2,
            description = "Step into the court with breathable knit zones and strategic cushioning that helps reduce distraction during play."
        ),
        Product(
            id = 3,
            name = "Air Jordan XXXVI",
            subtitle = "Men's Shoes",
            colors = "2 Colours",
            price = "US$185",
            imageResId = R.drawable.image3,
            description = "Designed for daily style and comfort, the Air Jordan XXXVI combines responsive cushioning with a lightweight upper."
        ),
        Product(
            id = 4,
            name = "Nike Air Force 1 '07",
            subtitle = "Women's Shoes",
            colors = "5 Colours",
            price = "US$115",
            imageResId = R.drawable.image4,
            description = "The radiance lives on in the basketball original with classic details and durable leather that ages to soft perfection."
        )
    )

    suspend fun getAllProducts(): List<Product> {
        initializeIfNeeded()
        val json = context.productDataStore.data.map { it[productsKey] }.first().orEmpty()
        val type = object : TypeToken<List<Product>>() {}.type
        val parsed = gson.fromJson<List<Product>>(json, type) ?: emptyList()
        val normalized = normalizeProducts(parsed)
        if (normalized != parsed) {
            saveProducts(normalized)
        }
        return normalized
    }

    suspend fun saveProducts(products: List<Product>) {
        val json = gson.toJson(products)
        context.productDataStore.edit { preferences ->
            preferences[productsKey] = json
        }
    }

    private suspend fun initializeIfNeeded() {
        val json = context.productDataStore.data.map { it[productsKey] }.first()
        if (json.isNullOrBlank()) {
            saveProducts(defaultProducts())
        }
    }

    private fun normalizeProducts(saved: List<Product>): List<Product> {
        val defaultsById = defaultProducts().associateBy { it.id }
        return defaultsById.keys.mapNotNull { id ->
            val default = defaultsById[id] ?: return@mapNotNull null
            val stored = saved.find { it.id == id }
            if (stored == null) default else default.copy(isWishlisted = stored.isWishlisted)
        }
    }
}
