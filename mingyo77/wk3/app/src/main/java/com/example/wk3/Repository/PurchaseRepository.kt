package com.example.wk3.Repository

import com.example.wk3.data.Product
import kotlinx.coroutines.flow.Flow

interface PurchaseRepository {
    suspend fun getPurchaseList(): Flow<List<Product>>
}