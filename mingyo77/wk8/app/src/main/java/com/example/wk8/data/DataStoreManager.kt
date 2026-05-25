package com.example.wk8.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.wk8.R
import com.example.wk8.data.DataStoreManager.Companion.PRODUCT_LIST_KEY
import com.example.wk8.model.Product
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "product_prefs")

class DataStoreManager(
    private val context: Context,
) {
    private val gson = Gson()

    private companion object {
        val PRODUCT_LIST_KEY = stringPreferencesKey("product_list")
    }

    suspend fun saveProductList(products: List<Product>) {
        val jsonString = gson.toJson(products)
        context.dataStore.edit { prefs ->
            prefs[PRODUCT_LIST_KEY] = jsonString
        }
    }

    fun getProductList(): Flow<List<Product>> = context.dataStore.data.map { prefs ->
        val jsonString = prefs[PRODUCT_LIST_KEY].orEmpty()
        if (jsonString.isEmpty()) {
            getInitialDummyData()
        } else {
            val type = object : TypeToken<List<Product>>() {}.type
            gson.fromJson<List<Product>>(jsonString, type)
        }
    }

    private fun getInitialDummyData(): List<Product> = listOf(
        Product(1, "Air Jordan XXXVI", "US$185", R.drawable.ic_jordan, "This is Jordan"),
        Product(2, "Nike Air Force 1 '07", "US$115", R.drawable.ic_nike, "This is Nike"),
        Product(3, "Nike Everyday Plus Cushioned", "US$10", R.drawable.ic_socks, "Training Ankle Socks (6 Pairs)"),
        Product(4, "Nike2", "$500", R.drawable.ic_nike2, "This is Nike2"),
    )
}
