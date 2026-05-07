package com.example.wk3.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wk3.Repository.PurchaseRepository
import com.example.wk3.data.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PurchaseViewModel @Inject constructor(
    private val purchaseRepository: PurchaseRepository
): ViewModel(){

    private val _purchaseList = MutableStateFlow<List<Product>>(emptyList())
    val purchaseList = _purchaseList.asStateFlow()

    fun loadPurchaseList(){
        viewModelScope.launch {
            purchaseRepository.getPurchaseList().collect{ savedList->
                _purchaseList.value = savedList
            }
        }
    }
}