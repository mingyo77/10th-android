package com.example.umc.ui.buy

import androidx.lifecycle.ViewModel
import com.example.umc.data.repository.LocalProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class BuyAllViewModel @Inject constructor(
    productRepository: LocalProductRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(
        BuyAllUiState(products = productRepository.getBuyAllProducts())
    )
    val uiState: StateFlow<BuyAllUiState> = _uiState.asStateFlow()
}
