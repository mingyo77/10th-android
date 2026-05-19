package com.example.nikeapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nikeapp.ProductData
import com.example.nikeapp.ProductDataStore
import com.example.nikeapp.R
import com.example.nikeapp.repository.LocalRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ShopViewModel(context: Context) : ViewModel() {

    private val localRepository = LocalRepository(ProductDataStore(context))

    private val _wishlistNames = MutableStateFlow<Set<String>>(emptySet())
    val wishlistNames: StateFlow<Set<String>> = _wishlistNames

    val dummyList = mutableListOf(
        ProductData("Nike Everyday Plus", "₩10,000", R.drawable.home_bg),
        ProductData("Nike Elite Crew", "₩16,000", R.drawable.home_bg),
        ProductData("Nike Air Force 1", "₩115,000", R.drawable.home_bg),
        ProductData("Jordan ENike", "₩115,000", R.drawable.home_bg)
    )

    fun loadWishlist() {
        viewModelScope.launch {
            localRepository.getWishlistProducts().collect { wishlist ->
                _wishlistNames.value = wishlist.map { it.name }.toSet()
            }
        }
    }

    fun toggleWishlist(product: ProductData) {
        viewModelScope.launch {
            localRepository.toggleWishlist(product)
        }
    }

    fun saveShopProducts() {
        viewModelScope.launch {
            localRepository.getProducts(ProductDataStore.SHOP_PRODUCTS_KEY).collect { savedList ->
                if (savedList.isEmpty()) {
                    localRepository.saveProducts(ProductDataStore.SHOP_PRODUCTS_KEY, dummyList)
                }
            }
        }
    }
}