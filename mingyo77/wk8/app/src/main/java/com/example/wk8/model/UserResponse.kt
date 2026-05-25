package com.example.wk8.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("data")
    val data: UserData,
)

data class UserData(
    @SerializedName("id")
    val id: Int,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("avatar")
    val avatar: String,
)
