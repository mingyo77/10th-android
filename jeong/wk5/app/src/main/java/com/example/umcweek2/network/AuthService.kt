package com.example.umcweek2.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AuthService {
    @GET("users/{id}")
    suspend fun getUserById(
        @Path("id") id: Int
    ): Response<ReqresSingleUserResponse>

    @GET("users")
    suspend fun getUsers(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 3
    ): Response<ReqresUsersResponse>
}
