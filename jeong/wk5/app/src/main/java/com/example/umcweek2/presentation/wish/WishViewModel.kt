package com.example.umcweek2.presentation.wish

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
class WishViewModel @Inject constructor(
    private val productLocalRepository: ProductLocalRepository
) : ViewModel() {
    private val _wishItems = MutableStateFlow<List<Product>>(emptyList())
    val wishItems: StateFlow<List<Product>> = _wishItems.asStateFlow()

    fun loadWishItems() {
        viewModelScope.launch {
            _wishItems.value = productLocalRepository.getWishlistProducts()
        }
    }
}
