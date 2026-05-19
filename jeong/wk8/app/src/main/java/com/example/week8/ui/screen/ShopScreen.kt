package com.example.week8.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.week8.R
import com.example.week8.model.Product
import com.example.week8.ui.components.ShopProductItem
import com.example.week8.viewmodel.ProductViewModel

// [ShopAllFragment] + [ShopProductAdapter] → 구매하기(전체 탭) 화면.

@Composable
fun ShopScreen(
    modifier: Modifier = Modifier,
    viewModel: ProductViewModel = viewModel(),
) {
    val products by viewModel.products.collectAsState()

    // wk5 TabLayout의 selectedTab position과 같은 역할.
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("전체", "Top & T-shirts", "sale")

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colorResource(R.color.white))
            .padding(horizontal = 24.dp, vertical = 24.dp),
    ) {
        // wk5 fragment_shop.xml 의 TabLayout과 대응됨
        ScrollableTabRow(
            selectedTabIndex = selectedTab,
            containerColor = colorResource(R.color.white),
            contentColor = colorResource(R.color.black),
            edgePadding = 0.dp,
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index }, // 탭 클릭 시 인덱스 변경 → when 분기로 화면 전환
                    text = {
                        Text(
                            text = title,
                            color = if (selectedTab == index) {
                                colorResource(R.color.black) // 선택 탭: wk5 tabSelectedTextColor
                            } else {
                                colorResource(R.color.gray_500) // 비선택: tabTextColor
                            },
                            fontSize = 16.sp,
                        )
                    },
                )
            }
        }

        when (selectedTab) {
            0 -> ShopProductGrid(
                products = products,
                showWishlistButton = true,
                onToggleWishlist = viewModel::toggleWishlist,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp),
            )

            else -> PlaceholderTabContent(
                title = tabs[selectedTab],
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp),
            )
        }
    }
}

/**
 * RecyclerView(rvShopProducts) + ShopProductAdapter 를 대체하는 Composable.
 *
 * wk5: binding.rvShopProducts.layoutManager = GridLayoutManager(context, 2)
 * wk8: LazyColumn 안에서 products.chunked(2)로 2개씩 묶어 Row에 배치
 */
@Composable
private fun ShopProductGrid(
    products: List<Product>,
    showWishlistButton: Boolean,
    onToggleWishlist: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    // LazyColumn: 미션에서 요구한 세로 Lazy 리스트 (RecyclerView vertical 대응)
    LazyColumn(
        modifier = modifier,
        // contentPadding: 리스트 맨 아래 여백. wk5 RecyclerView paddingBottom="24dp" 대응
        contentPadding = PaddingValues(bottom = 24.dp),
    ) {
        items(
            // chunked(2): [1,2,3,4] → [[1,2],[3,4]] 로 변환. 2열 그리드의 '한 행' 단위 데이터.
            items = products.chunked(2),
            // key: 한 행을 식별. 행 안 상품 id를 "-"로 이어 고유 문자열 생성.
            // (행 단위 key이므로, 행 구성이 바뀌면 Compose가 해당 행 UI를 새로 만듦)
            key = { row -> row.joinToString(separator = "-") { it.id.toString() } },
        ) { row ->
            // Row: 한 줄에 최대 2개 상품. wk5 GridLayoutManager 2열 한 줄과 동일한 레이아웃.
            Row(modifier = Modifier.fillMaxWidth()) {
                row.forEach { product ->
                    ShopProductItem(
                        product = product,
                        showWishlistButton = showWishlistButton,
                        onClickItem = { }, // wk5: navigateToProductDetail(item.id)
                        // wk5: onClickWishlist = { viewModel.toggleWishlist(item.id) }
                        onClickWishlist = { onToggleWishlist(product.id) },
                        // weight(1f): Row 안에서 가로 공간을 균등 분할 → 2열 너비 맞춤
                        modifier = Modifier.weight(1f),
                    )
                }
                // 마지막 행에 상품이 1개만 있을 때(홀수 개): 오른쪽 빈 칸을 Spacer로 채움
                if (row.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
private fun PlaceholderTabContent(title: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        HorizontalDivider(color = colorResource(R.color.gray_200))
        Text(
            text = "$title 탭 (wk5 ShopPlaceholderFragment)",
            color = colorResource(R.color.gray_500),
            fontSize = 16.sp,
            modifier = Modifier.padding(top = 24.dp),
        )
    }
}
