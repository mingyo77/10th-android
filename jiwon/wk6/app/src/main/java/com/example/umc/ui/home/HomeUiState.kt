package com.example.umc.ui.home

import com.example.umc.data.model.Product

data class HomeUiState(
    val products: List<Product> = emptyList()
)
