package com.example.umc.data.model

import androidx.annotation.DrawableRes

data class Product(
    val name: String,
    val subInfo: String,
    val price: String,
    @DrawableRes val imageResId: Int
)
