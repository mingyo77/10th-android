package com.example.umcweek2.network

import com.google.gson.annotations.SerializedName

data class ReqresUserDto(
    val id: Int,
    val email: String,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    val avatar: String
)

data class ReqresSingleUserResponse(
    val data: ReqresUserDto
)

data class ReqresUsersResponse(
    val page: Int,
    @SerializedName("per_page") val perPage: Int,
    val total: Int,
    @SerializedName("total_pages") val totalPages: Int,
    val data: List<ReqresUserDto>
)
