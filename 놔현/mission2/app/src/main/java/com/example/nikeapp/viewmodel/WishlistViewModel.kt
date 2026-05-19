package com.example.nikeapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nikeapp.ProductData
import com.example.nikeapp.ProductDataStore
import com.example.nikeapp.repository.LocalRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WishlistViewModel(context: Context) : ViewModel() {

    private val localRepository = LocalRepository(ProductDataStore(context))

    private val _wishlist = MutableStateFlow<List<ProductData>>(emptyList())
    val wishlist: StateFlow<List<ProductData>> = _wishlist

    fun loadWishlist() {
        viewModelScope.launch {
            localRepository.getWishlistProducts().collect { wishlist ->
                _wishlist.value = wishlist
            }
        }
    }

    fun toggleWishlist(product: ProductData) {
        viewModelScope.launch {
            localRepository.toggleWishlist(product)
        }
    }
}