package com.example.umc.data.local

import com.example.umc.data.model.ReqResUser
import javax.inject.Inject
import javax.inject.Singleton

interface UserLocalDataSource {
    suspend fun getMyProfile(): ReqResUser?
    suspend fun getFollowers(): List<ReqResUser>
    suspend fun saveMyProfile(user: ReqResUser)
    suspend fun saveFollowers(users: List<ReqResUser>)
}

/**
 * Room 같은 실제 Local DB를 붙이기 전까지 Local DB 역할을 대신하는 DataSource입니다.
 * Repository는 이 DataSource만 호출하도록 분리해 두었습니다.
 */
@Singleton
class MemoryUserLocalDataSource @Inject constructor() : UserLocalDataSource {
    private var cachedMyProfile: ReqResUser? = null
    private var cachedFollowers: List<ReqResUser> = emptyList()

    override suspend fun getMyProfile(): ReqResUser? = cachedMyProfile

    override suspend fun getFollowers(): List<ReqResUser> = cachedFollowers

    override suspend fun saveMyProfile(user: ReqResUser) {
        cachedMyProfile = user
    }

    override suspend fun saveFollowers(users: List<ReqResUser>) {
        cachedFollowers = users
    }
}
