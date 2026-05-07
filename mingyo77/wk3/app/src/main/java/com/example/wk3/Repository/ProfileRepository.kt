package com.example.wk3.Repository

import com.example.wk3.data.UserListResponse
import com.example.wk3.data.UserResponse
import retrofit2.Response
import com.example.wk3.data.UserService
import javax.inject.Inject

interface ProfileRepository {
    suspend fun getUserInfo() : Response<UserResponse>
    suspend fun getFollowingList(page: Int) : Response<UserListResponse>
}
