package com.example.nikeappcompose.screen

import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import com.example.nikeappcompose.R

data class Product(
    val name: String,
    val price: String,
    val imageRes: Int = R.drawable.home_bg
)

@Composable
fun ShopScreen() {
    val tabs = listOf("전체", "Tops & T-Shirts", "Sale")
    var selectedTab by remember { mutableStateOf(0) }

    val products = listOf(
        Product("Nike Everyday Plus", "₩10,000"),
        Product("Nike Elite Crew", "₩16,000"),
        Product("Nike Air Force 1", "₩115,000"),
        Product("Jordan ENike", "₩115,000")
    )

    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(
            selectedTabIndex = selectedTab,
            contentColor = Color.Black,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                    color = Color.Black
                )
            }
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    text = {
                        Text(
                            text = title,
                            color = if (selectedTab == index) Color.Black else Color.Gray
                        )
                    }
                )
            }
        }

        when (selectedTab) {
            0 -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(products) { product ->
                        ProductItem(product = product)
                    }
                }
            }
            1 -> {
                Box(modifier = Modifier.fillMaxSize())
            }
            2 -> {
                Box(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun ProductItem(product: Product) {
    Column(modifier = Modifier.padding(4.dp)) {
        Image(
            painter = painterResource(id = product.imageRes),
            contentDescription = product.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = product.name,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 4.dp)
        )
        Text(
            text = product.price,
            fontSize = 11.sp,
            color = Color.Gray
        )
    }
}