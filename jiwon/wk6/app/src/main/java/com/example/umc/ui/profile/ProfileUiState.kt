package com.example.umc.ui.profile

import com.example.umc.data.model.ReqResUser

data class ProfileUiState(
    val isLoading: Boolean = true,
    val myProfile: ReqResUser? = null,
    val followers: List<ReqResUser> = emptyList(),
    val errorMessage: String? = null
)
