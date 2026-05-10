package com.example.nikeappcompose.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nikeappcompose.R

@Composable
fun HomeScreen() {
    val products = listOf(
        Product("Air Jordan XXXVI", "₩185,000"),
        Product("Air Jordan 1 Mid", "₩125,000"),
        Product("Nike Air Force 1", "₩115,000"),
        Product("Nike Zoom", "₩139,000"),
        Product("Nike Pegasus", "₩149,000")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Image(
            painter = painterResource(id = R.drawable.home_bg),
            contentDescription = "배너",
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            contentScale = ContentScale.Crop
        )

        Text(
            text = "What's new",
            fontSize = 14.sp,
            color = androidx.compose.ui.graphics.Color.Gray,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp)
        )

        Text(
            text = "나이키 최신 상품",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp, top = 4.dp, bottom = 8.dp)
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(products) { product ->
                HorizontalProductItem(product = product)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun HorizontalProductItem(product: Product) {
    Column(modifier = Modifier.width(180.dp)) {
        Image(
            painter = painterResource(id = R.drawable.home_bg),
            contentDescription = product.name,
            modifier = Modifier
                .width(180.dp)
                .height(180.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = product.name,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 4.dp)
        )
        Text(
            text = product.price,
            fontSize = 12.sp,
            color = androidx.compose.ui.graphics.Color.Gray
        )
    }
}