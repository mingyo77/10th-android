package com.example.wk8.model

data class Product(
    val id: Int,
    val name: String,
    val price: String,
    val imageResId: Int,
    val description: String = "",
    val isWish: Boolean = false,
)
