package com.example.wk3.data

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("data")
    val data: UserData
)

data class UserData(
    @SerializedName("id")
    val id: Int, // 고유번호

    @SerializedName("first_name")
    val firstName: String,

    @SerializedName("last_name")
    val lastName: String,

    @SerializedName("avatar")
    val avatar: String     // 이미지 URL
)