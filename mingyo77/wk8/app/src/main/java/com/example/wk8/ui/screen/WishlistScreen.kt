package com.example.wk8.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.wk8.ui.components.ProductGridItem
import com.example.wk8.viewmodel.ProductViewModel

@Composable
fun WishlistScreen(
    viewModel: ProductViewModel,
    modifier: Modifier = Modifier,
) {
    val wishItems by viewModel.wishItems.collectAsState()

    if (wishItems.isEmpty()) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Color.White),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = "Your wishlist is empty", color = Color.Gray)
        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = modifier
                .fillMaxSize()
                .background(Color.White),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            items(
                items = wishItems,
                key = { product -> product.id },
            ) { product ->
                ProductGridItem(
                    product = product,
                    onWishClick = viewModel::updateWish,
                    modifier = Modifier.padding(bottom = 4.dp),
                )
            }
        }
    }
}
