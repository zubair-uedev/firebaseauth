package com.example.firebaseauth.presentation.dashboard

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebaseauth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class DashBoardViewModel(private val authRepository: AuthRepository) : ViewModel() {
    var state = mutableStateOf(HomeState())
        private set
    private val _events = MutableSharedFlow<HomeEvent>()
    val events: SharedFlow<HomeEvent> = _events.asSharedFlow()

    fun onIntent(homeIntent: HomeIntent) {
        when (homeIntent) {
            HomeIntent.ConfirmLogout -> {
                state.value = state.value.copy(
                    isLogoutDialogVisible = false
                )
                viewModelScope.launch { authRepository.logOut() }
                viewModelScope.launch {
                    _events.emit(HomeEvent.NavigateToLogin)
                }
            }

            HomeIntent.DismissLogoutDialog -> {
                state.value = state.value.copy(
                    isLogoutDialogVisible = false
                )
            }

            HomeIntent.LogoutRequested -> {
                state.value = state.value.copy(
                    isLogoutDialogVisible = true
                )
            }
        }
    }
}
data class HomeState(
    val isLogoutDialogVisible: Boolean = false
)

sealed class HomeIntent {
    data object LogoutRequested : HomeIntent()
    data object DismissLogoutDialog : HomeIntent()
    data object ConfirmLogout : HomeIntent()
}

sealed class HomeEvent {
    data object NavigateToLogin : HomeEvent()
}