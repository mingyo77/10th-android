package com.example.wk3.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.wk3.data.Product
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.example.wk3.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
private val Context.dataStore by preferencesDataStore(name = "user_prefs")

class DataStoreManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val gson = Gson()

    companion object {
        // 데이터 key
        private val PRODUCT_LIST_KEY = stringPreferencesKey("product_list")
    }

    // 저장하기
    suspend fun saveProductList(products: List<Product>) {
        val jsonString = gson.toJson(products)
        context.dataStore.edit { prefs ->
            prefs[PRODUCT_LIST_KEY] = jsonString
        }
    }
    private fun getInitialDummyData(): ArrayList<Product> {
        return arrayListOf(
            Product("Air Jordan XXXVI", "US$185", R.drawable.ic_jordan, "This is Jordan"),
            Product("Nike Air Force 1 '07", "US$115 ", R.drawable.ic_nike , "This is Nike"),
            Product("Nike Everyday Plus\nCrushioned ", "USS$10", R.drawable.ic_socks, "Training Ankle Socks(6\nPairs)\n5 Colours"),
            Product("Nike2", "$500", R.drawable.ic_nike2, "This is Jordan"),
            )
    }
    //  불러오기
    fun getProductList(): Flow<ArrayList<Product>> = context.dataStore.data.map { prefs ->
        val jsonString = prefs[PRODUCT_LIST_KEY] ?: ""
        if (jsonString.isEmpty()) {
            getInitialDummyData()
        } else {
            val type = object : TypeToken<ArrayList<Product>>() {}.type
            gson.fromJson(jsonString, type)
        }
    }
}