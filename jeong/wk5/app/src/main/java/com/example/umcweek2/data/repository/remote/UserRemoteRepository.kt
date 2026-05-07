package com.example.umcweek2.data.repository.remote

import com.example.umcweek2.network.ReqresUserDto

interface UserRemoteRepository {
    suspend fun getProfile(userId: Int): ReqresUserDto
    suspend fun getFollowing(page: Int, perPage: Int): List<ReqresUserDto>
}
