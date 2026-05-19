package com.example.umcweek2.di

import com.example.umcweek2.data.repository.local.ProductLocalRepository
import com.example.umcweek2.data.repository.local.ProductLocalRepositoryImpl
import com.example.umcweek2.data.repository.remote.UserRemoteRepository
import com.example.umcweek2.data.repository.remote.UserRemoteRepositoryImpl
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
    abstract fun bindProductLocalRepository(
        impl: ProductLocalRepositoryImpl
    ): ProductLocalRepository

    @Binds
    @Singleton
    abstract fun bindUserRemoteRepository(
        impl: UserRemoteRepositoryImpl
    ): UserRemoteRepository
}
