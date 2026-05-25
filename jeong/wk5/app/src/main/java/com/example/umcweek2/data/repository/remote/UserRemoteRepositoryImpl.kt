package com.example.umcweek2.data.repository.remote

import com.example.umcweek2.network.AuthService
import com.example.umcweek2.network.ReqresUserDto
import javax.inject.Inject

class UserRemoteRepositoryImpl @Inject constructor(
    private val authService: AuthService
) : UserRemoteRepository {
    override suspend fun getProfile(userId: Int): ReqresUserDto {
        val response = authService.getUserById(userId)
        if (!response.isSuccessful) error("유저 정보 조회 실패: ${response.code()}")
        return response.body()?.data ?: error("유저 데이터가 비어 있습니다.")
    }

    override suspend fun getFollowing(page: Int, perPage: Int): List<ReqresUserDto> {
        val response = authService.getUsers(page = page, perPage = perPage)
        if (!response.isSuccessful) error("팔로잉 목록 조회 실패: ${response.code()}")
        return response.body()?.data ?: error("팔로잉 데이터가 비어 있습니다.")
    }
}
