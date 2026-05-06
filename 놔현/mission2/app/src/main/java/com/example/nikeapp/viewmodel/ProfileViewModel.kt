package com.example.nikeapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nikeapp.UserData
import com.example.nikeapp.repository.RemoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

    private val repository = RemoteRepository()

    private val _user = MutableStateFlow<UserData?>(null)
    val user: StateFlow<UserData?> = _user

    private val _userList = MutableStateFlow<List<UserData>>(emptyList())
    val userList: StateFlow<List<UserData>> = _userList

    fun loadUser() {
        viewModelScope.launch {
            _user.value = repository.getUser()
        }
    }

    fun loadUserList() {
        viewModelScope.launch {
            _userList.value = repository.getUserList()
        }
    }
}