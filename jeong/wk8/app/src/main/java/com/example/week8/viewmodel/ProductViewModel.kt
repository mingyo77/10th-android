package com.example.week8.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.week8.data.ProductRepository
import com.example.week8.model.Product
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

// wk5에서 HomeViewModel / ShopAllViewModel / WishViewModel로 구성되어 있던 ViewModel을 통합
// Fragment 가 RecyclerView Adapter에 데이터를 넘기던 흐름을 Screen이 collectAsState()로 구독하는 흐름으로 바꿈.
// collectAsState()로 구독할 수 있도록 StateFlow를 준비해둔다. products & wishItems StateFlow.
class ProductViewModel : ViewModel() {

    // ProductRepository.products (MutableStateFlow)를 UI용 StateFlow로 노출
    val products: StateFlow<List<Product>> = ProductRepository.products
        .stateIn(
            scope = viewModelScope, // ViewModel이 파괴되면 수집 자동 취소
            started = SharingStarted.WhileSubscribed(5_000), // 구독자 없으면 5초 후 upstream 수집 중단 (리소스 절약)
            initialValue = emptyList(), // 첫 collect 전까지 화면에 보여줄 기본값
        )

    // 전체 상품 중 isWishlisted == true 만 추출 → WishViewModel.wishItems 와 동일
    val wishItems: StateFlow<List<Product>> = ProductRepository.products
        .map { list -> list.filter { it.isWishlisted } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    // wk5 ShopAllViewModel.toggleWishlist(item.id)
    fun toggleWishlist(productId: Int) {
        ProductRepository.toggleWishlist(productId)
    }
}
