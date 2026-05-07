package com.example.wk3.Repository

import com.example.wk3.data.UserListResponse
import com.example.wk3.data.UserResponse
import com.example.wk3.data.UserService
import retrofit2.Response
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val userService: UserService
) : ProfileRepository {

    override suspend fun getUserInfo(): Response<UserResponse> {
        return userService.getUserById(1)
    }

    override suspend fun getFollowingList(page: Int): Response<UserListResponse> {
        return userService.getUsers(page)
    }
}