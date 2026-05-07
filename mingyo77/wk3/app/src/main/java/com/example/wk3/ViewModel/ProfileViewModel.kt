package com.example.wk3.ViewModel

import retrofit2.Response
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wk3.Repository.ProfileRepository
import com.example.wk3.data.UserData
import com.example.wk3.data.UserResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepository
): ViewModel() {
    private val _userData = MutableStateFlow<UserResponse?>(null)
    val userData: StateFlow<UserResponse?> = _userData.asStateFlow()
    private val _followingList = MutableStateFlow<List<UserData>>(emptyList())
    val followingList: StateFlow<List<UserData>> = _followingList.asStateFlow()


    fun fetchUserInfo(){
        viewModelScope.launch {
            try {
                val response = repository.getUserInfo()
                if (response.isSuccessful){
                    _userData.value = response.body()
                }
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
    fun fetchFollowingList(){
        viewModelScope.launch {
            try {
                val response = repository.getFollowingList(1)
                if (response.isSuccessful){
                    _followingList.value = response.body()?.data ?: emptyList()
                }
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}