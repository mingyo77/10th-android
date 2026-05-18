package com.example.week8.model

/**
 * wk5 [Product.kt] 와 동일한 상품 데이터 클래스.
 *
 * @property id LazyColumn/LazyRow items(key = { it.id }) 에서 상태 추적에 사용하는 고유 식별자
 * @property imageResId Compose에서 painterResource(imageResId) 로 이미지 표시
 * @property isWishlisted 위시리스트 포함 여부. 토글 시 copy()로만 변경 (불변 데이터 유지)
 */
data class Product(
    val id: Int,
    val name: String,
    val subtitle: String,
    val colors: String,
    val price: String,
    val imageResId: Int,
    val description: String,
    val isWishlisted: Boolean = false,
)
