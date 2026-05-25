package com.example.umc.ui.wishlist

import androidx.lifecycle.ViewModel
import com.example.umc.data.repository.LocalProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class WishlistViewModel @Inject constructor(
    productRepository: LocalProductRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(
        WishlistUiState(products = productRepository.getWishlistProducts())
    )
    val uiState: StateFlow<WishlistUiState> = _uiState.asStateFlow()
}
