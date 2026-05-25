package com.example.wk8.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.wk8.data.DataStoreManager
import com.example.wk8.data.ProductRepository
import com.example.wk8.model.Product
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProductViewModel(
    application: Application,
) : AndroidViewModel(application) {
    private val repository = ProductRepository(
        DataStoreManager(application.applicationContext)
    )

    val products: StateFlow<List<Product>> = repository.getProducts()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList(),
        )

    val wishItems: StateFlow<List<Product>> = products
        .map { productList -> productList.filter { it.isWish } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList(),
        )

    fun updateWish(productId: Int, isWish: Boolean) {
        viewModelScope.launch {
            repository.updateWish(productId, isWish)
        }
    }
}
