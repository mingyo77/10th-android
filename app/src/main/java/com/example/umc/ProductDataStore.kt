package com.example.umc

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "product_store")

object ProductDataStore {

    private val gson = Gson()

    private val HOME_PRODUCTS_KEY = stringPreferencesKey("home_products")
    private val BUY_PRODUCTS_KEY = stringPreferencesKey("buy_products")

    private val defaultHomeProducts = listOf(
        Product(
            id = 1,
            imageResId = R.drawable.shoe,
            name = "Air Jordan XXVI",
            price = "US$185",
            subInfo = "Men's Shoes",
            category = "TOP"
        ),
        Product(
            id = 2,
            imageResId = R.drawable.whiteshoe,
            name = "Nike Air Force 1",
            price = "US$115",
            subInfo = "Women's Shoes",
            category = "TOP"
        ),
        Product(
            id = 3,
            imageResId = R.drawable.sock,
            name = "Nike Everyday Plus",
            price = "US$10",
            subInfo = "Training Socks",
            category = "SALE"
        )
    )

    private val defaultBuyProducts = listOf(
        Product(
            id = 4,
            imageResId = R.drawable.sock,
            name = "Nike Everyday Plus Cushioned",
            price = "US$10",
            subInfo = "Training Ankle Socks (6 Pairs)",
            category = "TOP"
        ),
        Product(
            id = 5,
            imageResId = R.drawable.sock,
            name = "Nike Elite Crew",
            price = "US$16",
            subInfo = "Basketball Socks",
            category = "SALE",
            isWish = true
        ),
        Product(
            id = 6,
            imageResId = R.drawable.whiteshoe,
            name = "Nike Air Force 1 '07",
            price = "US$115",
            subInfo = "Women's Shoes",
            category = "TOP"
        ),
        Product(
            id = 7,
            imageResId = R.drawable.whiteshoe,
            name = "Jordan Ernie Air Force 1 '07",
            price = "US$115",
            subInfo = "Men's Shoes",
            category = "SALE"
        )
    )

    suspend fun seedInitialData(context: Context) {
        val prefs = context.dataStore.data.first()

        val hasHome = !prefs[HOME_PRODUCTS_KEY].isNullOrBlank()
        val hasBuy = !prefs[BUY_PRODUCTS_KEY].isNullOrBlank()

        if (!hasHome || !hasBuy) {
            context.dataStore.edit { pref ->
                if (!hasHome) {
                    pref[HOME_PRODUCTS_KEY] = gson.toJson(defaultHomeProducts)
                }
                if (!hasBuy) {
                    pref[BUY_PRODUCTS_KEY] = gson.toJson(defaultBuyProducts)
                }
            }
        }
    }

    fun getHomeProducts(context: Context): Flow<List<Product>> {
        return context.dataStore.data.map { prefs ->
            prefs[HOME_PRODUCTS_KEY].toProductList()
        }
    }

    fun getBuyProducts(context: Context): Flow<List<Product>> {
        return context.dataStore.data.map { prefs ->
            prefs[BUY_PRODUCTS_KEY].toProductList()
        }
    }

    fun getWishlistProducts(context: Context): Flow<List<Product>> {
        return combine(
            getHomeProducts(context),
            getBuyProducts(context)
        ) { homeList, buyList ->
            (homeList + buyList)
                .distinctBy { it.id }
                .filter { it.isWish }
        }
    }

    suspend fun toggleWish(context: Context, productId: Int) {
        val prefs = context.dataStore.data.first()

        val homeList = prefs[HOME_PRODUCTS_KEY].toProductList().map {
            if (it.id == productId) it.copy(isWish = !it.isWish) else it
        }

        val buyList = prefs[BUY_PRODUCTS_KEY].toProductList().map {
            if (it.id == productId) it.copy(isWish = !it.isWish) else it
        }

        context.dataStore.edit { newPrefs ->
            newPrefs[HOME_PRODUCTS_KEY] = gson.toJson(homeList)
            newPrefs[BUY_PRODUCTS_KEY] = gson.toJson(buyList)
        }
    }

    private fun String?.toProductList(): List<Product> {
        if (this.isNullOrBlank()) return emptyList()
        return try {
            val type = object : TypeToken<List<Product>>() {}.type
            gson.fromJson(this, type)
        } catch (e: Exception) {
            emptyList()
        }
    }
}