package com.example.umcweek2

data class Product(
    val id: Int,
    val name: String,
    val subtitle: String,
    val colors: String,
    val price: String,
    val imageResId: Int,
    val description: String,
    var isWishlisted: Boolean = false
)
