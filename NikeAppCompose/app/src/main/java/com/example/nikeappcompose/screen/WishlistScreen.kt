package com.example.nikeappcompose.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WishlistScreen() {
    val wishlist = listOf(
        Product("Air Jordan 1 Mid", "₩125,000"),
        Product("Nike Everyday Plus", "₩10,000"),
        Product("Nike Elite Crew", "₩16,000"),
        Product("Nike Air Force 1", "₩115,000")
    )

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "위시리스트",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp, top = 24.dp, bottom = 8.dp)
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(
                items = wishlist,
                key = { product -> product.name }
            ) { product ->
                ProductItem(product = product)
            }
        }
    }
}