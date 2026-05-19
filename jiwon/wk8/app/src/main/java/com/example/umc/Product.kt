package com.example.umc

data class Product(
    val imageResId: Int,
    val name: String,
    val price: String,
    val subInfo: String = "",
    val isWish: Boolean = false,
    val id: String = "${name}_${price}_${imageResId}"
)
