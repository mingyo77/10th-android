package com.example.week8.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.week8.R
import com.example.week8.ui.components.ShopProductItem
import com.example.week8.viewmodel.ProductViewModel

// [WishFragment] + [ShopProductAdapter](showWishlistButton = false) → Compose 홈 화면으로 구성.

@Composable
fun WishlistScreen(
    modifier: Modifier = Modifier,
    viewModel: ProductViewModel = viewModel(),
) {
    // isWishlisted == true 인 상품만 필터링된 리스트 (wk5 WishViewModel.wishItems)
    val wishItems by viewModel.wishItems.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colorResource(R.color.white))
            .padding(horizontal = 16.dp),
    ) {
        // wk5 fragment_wish.xml 상단 66dp 타이틀 영역
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(66.dp),
        ) {
            Text(
                text = "위시리스트",
                color = colorResource(R.color.black),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.BottomStart),
            )
        }

        if (wishItems.isEmpty()) {
            // wk5: binding.tvEmptyWish.visibility = if (wishItems.isEmpty()) VISIBLE else GONE
            Text(
                text = "위시리스트가 비어 있습니다.",
                color = colorResource(R.color.gray_500),
                fontSize = 15.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, bottom = 36.dp),
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 10.dp),
                contentPadding = PaddingValues(bottom = 24.dp),
            ) {
                items(
                    items = wishItems.chunked(2), // 2열 그리드용 행 단위 리스트
                    key = { row -> row.joinToString(separator = "-") { it.id.toString() } },
                ) { row ->
                    Row(modifier = Modifier.fillMaxWidth()) {
                        row.forEach { product ->
                            ShopProductItem(
                                product = product,
                                // 위시 화면에서는 하트 버튼 숨김 (wk5 showWishlistButton = false)
                                showWishlistButton = false,
                                onClickItem = { },
                                onClickWishlist = { }, // 버튼이 없으므로 호출되지 않음
                                modifier = Modifier.weight(1f),
                            )
                        }
                        if (row.size == 1) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
    }
}
