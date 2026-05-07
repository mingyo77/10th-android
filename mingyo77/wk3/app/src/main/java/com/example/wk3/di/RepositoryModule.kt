package com.example.wk3.di

import com.example.wk3.Repository.HomeRepository
import com.example.wk3.Repository.HomeRepositoryImpl
import com.example.wk3.Repository.ProfileRepository
import com.example.wk3.Repository.ProfileRepositoryImpl
import com.example.wk3.Repository.PurchaseRepository
import com.example.wk3.Repository.PurchaseRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindProfileRepository(
        profileRepositoryImpl: ProfileRepositoryImpl
    ): ProfileRepository

    @Binds
    abstract fun bindHomeRepository(
        homeRepositoryImpl: HomeRepositoryImpl
    ): HomeRepository

    @Binds
    abstract fun bindPurchaseRepository(
        purchaseRepositoryImpl: PurchaseRepositoryImpl
    ): PurchaseRepository
}