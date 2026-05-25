package com.example.umcweek2.presentation.shop

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.umcweek2.Product
import com.example.umcweek2.data.repository.local.ProductLocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ShopAllViewModel @Inject constructor(
    private val productLocalRepository: ProductLocalRepository
) : ViewModel() {
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products.asStateFlow()

    fun loadProducts() {
        viewModelScope.launch {
            _products.value = productLocalRepository.getAllProducts()
        }
    }

    fun toggleWishlist(productId: Int) {
        viewModelScope.launch {
            productLocalRepository.toggleWishlist(productId)
            _products.value = productLocalRepository.getAllProducts()
        }
    }
}
