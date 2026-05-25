package com.example.umc.data.repository

import com.example.umc.data.local.UserLocalDataSource
import com.example.umc.data.model.ReqResUser
import javax.inject.Inject
import javax.inject.Singleton

interface LocalUserRepository {
    suspend fun getMyProfile(): ReqResUser?
    suspend fun getFollowers(): List<ReqResUser>
    suspend fun saveMyProfile(user: ReqResUser)
    suspend fun saveFollowers(users: List<ReqResUser>)
}

@Singleton
class LocalUserRepositoryImpl @Inject constructor(
    private val userLocalDataSource: UserLocalDataSource
) : LocalUserRepository {
    override suspend fun getMyProfile(): ReqResUser? = userLocalDataSource.getMyProfile()

    override suspend fun getFollowers(): List<ReqResUser> = userLocalDataSource.getFollowers()

    override suspend fun saveMyProfile(user: ReqResUser) {
        userLocalDataSource.saveMyProfile(user)
    }

    override suspend fun saveFollowers(users: List<ReqResUser>) {
        userLocalDataSource.saveFollowers(users)
    }
}
