package com.example.wk8.model

data class UserListResponse(
    val page: Int,
    val data: List<UserData>,
)
