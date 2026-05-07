package com.example.nikeapp.repository

import com.example.nikeapp.ApiClient
import com.example.nikeapp.UserData

class RemoteRepository {

    private val apiKey = "reqres_df73b9dd436f45ee8ae8925f9dd0f2aa"

    suspend fun getUser(): UserData? {
        return try {
            val response = ApiClient.userService.getUser(apiKey)
            if (response.isSuccessful) response.body()?.data else null
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getUserList(): List<UserData> {
        return try {
            val response = ApiClient.userService.getUserList(apiKey)
            if (response.isSuccessful) response.body()?.data ?: emptyList()
            else emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }
}