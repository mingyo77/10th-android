package com.example.umc.data.repository

import com.example.umc.data.model.ReqResUser
import com.example.umc.data.remote.ReqResApiService
import javax.inject.Inject
import javax.inject.Singleton

interface RemoteUserRepository {
    suspend fun fetchMyProfile(): ReqResUser
    suspend fun fetchFollowers(): List<ReqResUser>
}

@Singleton
class RemoteUserRepositoryImpl @Inject constructor(
    private val apiService: ReqResApiService
) : RemoteUserRepository {
    override suspend fun fetchMyProfile(): ReqResUser = apiService.getUser(userId = 1).data

    override suspend fun fetchFollowers(): List<ReqResUser> = apiService.getUsers(page = 1).data
}
