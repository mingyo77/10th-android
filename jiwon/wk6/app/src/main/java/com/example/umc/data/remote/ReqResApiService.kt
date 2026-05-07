package com.example.umc.data.remote

import com.example.umc.data.model.ReqResSingleUserResponse
import com.example.umc.data.model.ReqResUserListResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ReqResApiService {
    @GET("api/users/{userId}")
    suspend fun getUser(
        @Path("userId") userId: Int,
        @Header("x-api-key") apiKey: String = API_KEY
    ): ReqResSingleUserResponse

    @GET("api/users")
    suspend fun getUsers(
        @Query("page") page: Int = 1,
        @Header("x-api-key") apiKey: String = API_KEY
    ): ReqResUserListResponse

    companion object {
        private const val API_KEY = "reqres_0fdb12297a944572a572183840092802"
    }
}
