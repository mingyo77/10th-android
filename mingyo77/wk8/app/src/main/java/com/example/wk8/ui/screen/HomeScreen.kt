package com.example.wk8.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wk8.R
import com.example.wk8.ui.components.HomeProductItem
import com.example.wk8.viewmodel.ProductViewModel

@Composable
fun HomeScreen(
    viewModel: ProductViewModel,
    modifier: Modifier = Modifier,
) {
    val products by viewModel.products.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState()),
    ) {
        Text(
            text = "Discover",
            color = Color.Black,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 32.dp, top = 36.dp),
        )
        Text(
            text = "Find your next favorite Nike item",
            color = Color.Gray,
            fontSize = 15.sp,
            modifier = Modifier.padding(start = 32.dp, top = 8.dp, bottom = 20.dp),
        )
        Image(
            painter = painterResource(R.drawable.ic_homelogo_background),
            contentDescription = "Home banner",
            modifier = Modifier
                .fillMaxWidth()
                .height(420.dp),
        )
        LazyRow(
            contentPadding = PaddingValues(horizontal = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(top = 12.dp, bottom = 24.dp),
        ) {
            items(
                items = products,
                key = { product -> product.id },
            ) { product ->
                HomeProductItem(
                    product = product,
                    onClick = {},
                )
            }
        }
    }
}
