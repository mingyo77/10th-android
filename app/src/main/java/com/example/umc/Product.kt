package com.example.umc

data class Product(
    val id: Int,
    val imageResId: Int,
    val name: String,
    val price: String,
    val subInfo: String = "",
    val category: String = "ALL",
    val isWish: Boolean = false
)