package com.example.nikeapp

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("data")
    val data: UserData
)

data class UserData(
    @SerializedName("id")
    val id: Int,
    @SerializedName("email")
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("avatar")
    val avatar: String
)

data class UserListResponse(
    @SerializedName("data")
    val data: List<UserData>
)