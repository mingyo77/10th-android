package com.example.umcweek2.presentation.detail

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
class ProductDetailViewModel @Inject constructor(
    private val productLocalRepository: ProductLocalRepository
) : ViewModel() {
    private val _product = MutableStateFlow<Product?>(null)
    val product: StateFlow<Product?> = _product.asStateFlow()

    fun loadProduct(productId: Int) {
        viewModelScope.launch {
            _product.value = productLocalRepository.getProduct(productId)
        }
    }

    fun toggleWishlist(productId: Int) {
        viewModelScope.launch {
            productLocalRepository.toggleWishlist(productId)
            _product.value = productLocalRepository.getProduct(productId)
        }
    }
}
