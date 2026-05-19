package com.example.umcweek2.presentation.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.umcweek2.data.repository.remote.UserRemoteRepository
import com.example.umcweek2.network.ReqresUserDto
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class UserUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val profile: ReqresUserDto? = null,
    val following: List<ReqresUserDto> = emptyList()
)

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRemoteRepository: UserRemoteRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(UserUiState())
    val uiState: StateFlow<UserUiState> = _uiState.asStateFlow()

    fun loadUserPage() {
        _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)

        viewModelScope.launch {
            runCatching {
                val profileDeferred = async { userRemoteRepository.getProfile(1) }
                val followingDeferred = async { userRemoteRepository.getFollowing(page = 1, perPage = 3) }
                profileDeferred.await() to followingDeferred.await()
            }.onSuccess { (profile, following) ->
                _uiState.value = UserUiState(
                    isLoading = false,
                    errorMessage = null,
                    profile = profile,
                    following = following
                )
            }.onFailure { throwable ->
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = throwable.message ?: "마이페이지 로드에 실패했습니다."
                )
            }
        }
    }
}
