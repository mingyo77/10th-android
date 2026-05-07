package com.example.wk3.Repository

import com.example.wk3.data.DataStoreManager
import com.example.wk3.data.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface HomeRepository {
    suspend fun getProductList(): Flow<List<Product>>

}