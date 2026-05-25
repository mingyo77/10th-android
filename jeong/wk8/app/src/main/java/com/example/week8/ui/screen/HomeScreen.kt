package com.example.week8.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.week8.R
import com.example.week8.ui.components.HomeProductItem
import com.example.week8.viewmodel.ProductViewModel

// [HomeFragment] + [HomeProductAdapter] → Compose 홈 화면으로 구성.

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    // viewModel(): Activity 범위 ViewModel을 가져옴. wk5의 by viewModels()와 동일한 역할.
    viewModel: ProductViewModel = viewModel(),
) {
    // collectAsState(): ViewModel의 StateFlow<List<Product>>를 Compose State로 변환.
    // products가 바뀌면(위시 토글 등) 이 Composable이 자동으로 다시 그려짐.
    // wk5에서는 lifecycleScope + collect { adapter = ... } 패턴이었음.
    val products by viewModel.products.collectAsState()

    // LazyColumn: 화면에 보이는 구간만 그리는 세로 스크롤 리스트 (RecyclerView와 유사한 지연 로딩).
    // wk5의 ScrollView + RecyclerView 조합을 하나의 LazyColumn으로 통합.
    LazyColumn(
        modifier = modifier
            .fillMaxSize() // 부모(Scaffold)가 준 영역을 전부 사용
            .background(colorResource(R.color.white))
            .padding(horizontal = 16.dp),
    ) {
        // item { }: 리스트가 아닌 '고정 UI 1칸'을 넣을 때 사용 (배너, 제목 등).
        // Adapter의 ViewType이 아닌, 단순 헤더 영역에 해당.
        item {
            Column(modifier = Modifier.padding(top = 50.dp, bottom = 24.dp)) {
                Text(
                    text = "Discover",
                    color = colorResource(R.color.black),
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "9월 4일 목요일",
                    color = colorResource(R.color.gray_500),
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 4.dp),
                )
                Image(
                    // painterResource: wk5의 setImageResource(imageResId)와 동일
                    painter = painterResource(R.drawable.home_banner),
                    contentDescription = "홈 배너",
                    contentScale = ContentScale.Crop, // scaleType="centerCrop" 대응
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(505.dp)
                        .padding(top = 50.dp)
                        .background(colorResource(R.color.gray_200)),
                )
                Text(
                    text = "What's new",
                    color = colorResource(R.color.black),
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 18.dp),
                )
                Text(
                    text = "나이키 최신 상품",
                    color = colorResource(R.color.gray_500),
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 8.dp),
                )
            }
        }

        // 상품 가로 목록: wk5 rvHomeProducts (LinearLayoutManager.HORIZONTAL)
        item {
            // LazyRow: 가로 방향 Lazy 리스트. 가로 RecyclerView의 Compose 대응.
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 24.dp),
            ) {
                // wk5: HomeProductAdapter(items) → getItemCount / onBindViewHolder
                // items()가 리스트 크기만큼 슬롯을 만들고 각 product마다 아래 람다 실행
                items(
                    items = products, // 표시할 데이터 리스트 (Adapter 생성자의 items와 동일)

                    // product.id: 상품마다 고유 ID. 리스트 순서가 바뀌거나 항목이 삽입/삭제될 때
                    // Compose가 '같은 상품 = 같은 UI 슬롯'으로 인식해 상태(스크롤 위치, 애니메이션 등)가 섞이지 않음.
                    key = { product -> product.id },
                ) { product ->
                    // product: 현재 인덱스의 Product. wk5 onBindViewHolder(holder, position)의 items[position]과 동일.
                    HomeProductItem(
                        product = product,
                        // wk5: binding.root.setOnClickListener { onClickItem(item) }
                        onClick = { /* 상세 화면은 추후 마이그레이션 */ },
                    )
                }
            }
        }
    }
}
