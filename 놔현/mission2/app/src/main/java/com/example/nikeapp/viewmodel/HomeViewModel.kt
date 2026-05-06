package com.example.nikeapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nikeapp.ProductData
import com.example.nikeapp.ProductDataStore
import com.example.nikeapp.R
import com.example.nikeapp.repository.LocalRepository
import kotlinx.coroutines.launch

class HomeViewModel(context: Context) : ViewModel() {

    private val localRepository = LocalRepository(ProductDataStore(context))

    val dummyList = mutableListOf(
        ProductData("Air Jordan XXXVI", "₩185,000", R.drawable.home_bg),
        ProductData("Air Jordan 1 Mid", "₩125,000", R.drawable.home_bg),
        ProductData("Nike Air Force 1", "₩115,000", R.drawable.home_bg),
        ProductData("Nike Zoom", "₩139,000", R.drawable.home_bg),
        ProductData("Nike Pegasus", "₩149,000", R.drawable.home_bg)
    )

    fun saveHomeProducts() {
        viewModelScope.launch {
            localRepository.getProducts(ProductDataStore.HOME_PRODUCTS_KEY).collect { savedList ->
                if (savedList.isEmpty()) {
                    localRepository.saveProducts(ProductDataStore.HOME_PRODUCTS_KEY, dummyList)
                }
            }
        }
    }
}