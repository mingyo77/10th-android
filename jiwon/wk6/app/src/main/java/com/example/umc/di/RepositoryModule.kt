package com.example.umc.di

import com.example.umc.data.local.DefaultProductLocalDataSource
import com.example.umc.data.local.MemoryUserLocalDataSource
import com.example.umc.data.local.ProductLocalDataSource
import com.example.umc.data.local.UserLocalDataSource
import com.example.umc.data.repository.LocalProductRepository
import com.example.umc.data.repository.LocalProductRepositoryImpl
import com.example.umc.data.repository.LocalUserRepository
import com.example.umc.data.repository.LocalUserRepositoryImpl
import com.example.umc.data.repository.RemoteUserRepository
import com.example.umc.data.repository.RemoteUserRepositoryImpl
import com.example.umc.data.repository.UserRepository
import com.example.umc.data.repository.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindProductLocalDataSource(
        impl: DefaultProductLocalDataSource
    ): ProductLocalDataSource

    @Binds
    @Singleton
    abstract fun bindUserLocalDataSource(
        impl: MemoryUserLocalDataSource
    ): UserLocalDataSource

    @Binds
    @Singleton
    abstract fun bindLocalProductRepository(
        impl: LocalProductRepositoryImpl
    ): LocalProductRepository

    @Binds
    @Singleton
    abstract fun bindLocalUserRepository(
        impl: LocalUserRepositoryImpl
    ): LocalUserRepository

    @Binds
    @Singleton
    abstract fun bindRemoteUserRepository(
        impl: RemoteUserRepositoryImpl
    ): RemoteUserRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        impl: UserRepositoryImpl
    ): UserRepository
}
