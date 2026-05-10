package com.example.nikeappcompose.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nikeappcompose.R

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
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize()
        ) {
            items(wishlist) { product ->
                ProductItem(product = product)
            }
        }
    }
}