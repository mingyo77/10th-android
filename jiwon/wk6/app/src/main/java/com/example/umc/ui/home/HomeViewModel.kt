package com.example.umc.ui.home

import androidx.lifecycle.ViewModel
import com.example.umc.data.repository.LocalProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    productRepository: LocalProductRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(
        HomeUiState(products = productRepository.getHomeProducts())
    )
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()
}
