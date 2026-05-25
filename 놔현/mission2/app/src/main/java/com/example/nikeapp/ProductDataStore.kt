package com.example.nikeapp

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "nike_store")

class ProductDataStore(private val context: Context) {

    private val gson = Gson()

    companion object {
        val HOME_PRODUCTS_KEY = stringPreferencesKey("home_products")
        val SHOP_PRODUCTS_KEY = stringPreferencesKey("shop_products")
        val WISHLIST_PRODUCTS_KEY = stringPreferencesKey("wishlist_products")
    }

    // 저장
    suspend fun saveProducts(key: Preferences.Key<String>, products: List<ProductData>) {
        val json = gson.toJson(products)
        context.dataStore.edit { it[key] = json }
    }

    // 불러오기
    fun getProducts(key: Preferences.Key<String>): Flow<List<ProductData>> {
        return context.dataStore.data.map { preferences ->
            val json = preferences[key] ?: return@map emptyList()
            val type = object : TypeToken<List<ProductData>>() {}.type
            gson.fromJson(json, type)
        }
    }

    // 위시리스트에 추가/제거
    suspend fun toggleWishlist(product: ProductData) {
        context.dataStore.edit { preferences ->
            val json = preferences[WISHLIST_PRODUCTS_KEY] ?: "[]"
            val type = object : TypeToken<List<ProductData>>() {}.type
            val list: MutableList<ProductData> = gson.fromJson(json, type)

            val existing = list.find { it.name == product.name }
            if (existing != null) {
                list.remove(existing)
            } else {
                list.add(ProductData(product.name, product.price, R.drawable.home_bg))
            }
            preferences[WISHLIST_PRODUCTS_KEY] = gson.toJson(list)
        }
    }

    // 위시리스트 여부 확인
    fun isInWishlist(productName: String): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            val json = preferences[WISHLIST_PRODUCTS_KEY] ?: "[]"
            val type = object : TypeToken<List<ProductData>>() {}.type
            val list: List<ProductData> = gson.fromJson(json, type)
            list.any { it.name == productName }
        }
    }
}