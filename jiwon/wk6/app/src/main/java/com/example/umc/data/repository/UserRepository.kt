package com.example.umc.data.repository

import com.example.umc.data.model.ReqResUser
import javax.inject.Inject
import javax.inject.Singleton

interface UserRepository {
    suspend fun getMyProfile(): ReqResUser
    suspend fun getFollowers(): List<ReqResUser>
}

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val remoteUserRepository: RemoteUserRepository,
    private val localUserRepository: LocalUserRepository
) : UserRepository {
    override suspend fun getMyProfile(): ReqResUser {
        return runCatching {
            remoteUserRepository.fetchMyProfile().also { localUserRepository.saveMyProfile(it) }
        }.getOrElse { throwable ->
            localUserRepository.getMyProfile()
                ?: throw throwable
        }
    }

    override suspend fun getFollowers(): List<ReqResUser> {
        return runCatching {
            remoteUserRepository.fetchFollowers().also { localUserRepository.saveFollowers(it) }
        }.getOrElse { throwable ->
            localUserRepository.getFollowers().takeIf { it.isNotEmpty() }
                ?: throw throwable
        }
    }
}
