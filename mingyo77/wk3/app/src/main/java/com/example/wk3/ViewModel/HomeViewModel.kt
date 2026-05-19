package com.example.wk3.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wk3.Repository.HomeRepository
import com.example.wk3.data.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
): ViewModel(){

    //뷰모델 안에서만 수정할수 있는 데이터 상자 "Mutable" 변할수 있다는 뜻, 상품이
    //들어오면 이 상자 내용을 바꿈
    private val _productList = MutableStateFlow<List<Product>>(emptyList())
    //읽기 전용 코드 asStateFlow()
    val productList = _productList.asStateFlow()

     fun loadProducts() {
        viewModelScope.launch {
            repository.getProductList().collect{ savedList->
                _productList.value = savedList
            }
        }
    }
}