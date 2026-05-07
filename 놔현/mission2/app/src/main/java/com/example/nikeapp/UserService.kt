package com.example.nikeapp

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface UserService {

    // 1번 유저 정보 가져오기
    @GET("users/1")
    suspend fun getUser(
        @Header("x-api-key") apiKey: String
    ): Response<UserResponse>

    // 유저 리스트 가져오기
    @GET("users")
    suspend fun getUserList(
        @Header("x-api-key") apiKey: String
    ): Response<UserListResponse>
}