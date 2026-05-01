package com.example.wk3.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface UserService {
    @Headers("x-api-key: reqres_2f04e79a468f4cfe8ac30e593c43ee12")
    @GET("api/users/{id}")
    fun getUserById(
        @Path("id") id: Int
    ): Call<UserResponse>
}