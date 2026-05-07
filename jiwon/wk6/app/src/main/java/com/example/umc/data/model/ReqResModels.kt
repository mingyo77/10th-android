package com.example.umc.data.model

import com.google.gson.annotations.SerializedName

data class ReqResSingleUserResponse(
    @SerializedName("data") val data: ReqResUser
)

data class ReqResUserListResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("per_page") val perPage: Int,
    @SerializedName("total") val total: Int,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("data") val data: List<ReqResUser>
)

data class ReqResUser(
    @SerializedName("id") val id: Int,
    @SerializedName("email") val email: String,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("avatar") val avatar: String
) {
    val nickname: String get() = "$firstName $lastName"
}
