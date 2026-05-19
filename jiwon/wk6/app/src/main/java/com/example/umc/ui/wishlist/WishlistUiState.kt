package com.example.umc.ui.wishlist

import com.example.umc.data.model.Product

data class WishlistUiState(
    val products: List<Product> = emptyList()
)
