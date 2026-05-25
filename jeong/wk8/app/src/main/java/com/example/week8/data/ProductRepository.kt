package com.example.week8.data

import com.example.week8.R
import com.example.week8.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * wk5 [ProductLocalDataSource] + [ProductLocalRepository] 역할 (in-memory 단순화).
 *
 * DataStore 대신 MutableStateFlow로 목록을 들고 있어,
 * 값이 바뀌면 이를 구독하는 ViewModel → Screen이 자동 갱신됨.
 */
object ProductRepository {

    // 내부에서만 수정 가능한 Flow. wk5의 로컬 DB/Preferences에 해당하는 '원본 데이터'
    private val _products = MutableStateFlow(defaultProducts())

    // 외부에는 읽기 전용 StateFlow만 공개 (캡슐화)
    val products: StateFlow<List<Product>> = _products.asStateFlow()

    /**
     * 위시 토글. wk5에서 상품 JSON 저장 후 isWishlisted 플래그를 바꾸던 로직과 동일 목적.
     * update { }: 현재 리스트를 받아 새 리스트를 만들어 _products에 반영 → collect 중인 UI 갱신
     */
    fun toggleWishlist(productId: Int) {
        _products.update { list ->
            list.map { product ->
                if (product.id == productId) {
                    product.copy(isWishlisted = !product.isWishlisted) // data class copy로 불변 업데이트
                } else {
                    product
                }
            }
        }
    }

    // wk5 ProductLocalDataSource.defaultProducts() 와 동일한 초기 목록
    private fun defaultProducts(): List<Product> = listOf(
        Product(
            id = 1,
            name = "Nike Everyday Plus Cushioned",
            subtitle = "Training Ankle Socks (6 Pairs)",
            colors = "5 Colours",
            price = "US$10",
            imageResId = R.drawable.image1,
            description = "The Nike Everyday Plus Cushioned Socks bring comfort to your workout with extra cushioning under the heel and forefoot and a snug, supportive arch band.",
        ),
        Product(
            id = 2,
            name = "Nike Elite Crew",
            subtitle = "Basketball Socks",
            colors = "7 Colours",
            price = "US$16",
            imageResId = R.drawable.image2,
            description = "Step into the court with breathable knit zones and strategic cushioning that helps reduce distraction during play.",
        ),
        Product(
            id = 3,
            name = "Air Jordan XXXVI",
            subtitle = "Men's Shoes",
            colors = "2 Colours",
            price = "US$185",
            imageResId = R.drawable.image3,
            description = "Designed for daily style and comfort, the Air Jordan XXXVI combines responsive cushioning with a lightweight upper.",
        ),
        Product(
            id = 4,
            name = "Nike Air Force 1 '07",
            subtitle = "Women's Shoes",
            colors = "5 Colours",
            price = "US$115",
            imageResId = R.drawable.image4,
            description = "The radiance lives on in the basketball original with classic details and durable leather that ages to soft perfection.",
        ),
    )
}
