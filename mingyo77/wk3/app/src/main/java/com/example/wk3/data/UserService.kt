package com.example.wk3.data


import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {
    @Headers("x-api-key: reqres_2f04e79a468f4cfe8ac30e593c43ee12")
    @GET("api/users/{id}")
    suspend fun getUserById(
        //@Path(경로변수) 유일한 ID값을 가진 특정리소스에 접근할 때 사용
        @Path("id") id: Int
    ): UserResponse
    @Headers("x-api-key: reqres_2f04e79a468f4cfe8ac30e593c43ee12")
    @GET("api/users")
    suspend fun getUsers(
        //@Query(쿼리스트링) 리스트를 가져올 때 페이지 번호를 지정하거나, 검색어, 정렬 순서등 "옵션"을 줄 때 사용
        @Query("page") page: Int
    ): UserListResponse
}