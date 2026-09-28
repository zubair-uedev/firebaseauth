package com.example.firebaseauth.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebaseauth.domain.repository.AuthRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SplashViewModel(private val authRepository: AuthRepository) : ViewModel() {
    private val _progress = MutableStateFlow(0f)
    val progress = _progress.asStateFlow()
    private val _splashEvent = MutableSharedFlow<SplashEvent>()
    val splashEvent = _splashEvent.asSharedFlow()

    init {
        loadProgress()
    }

    private fun loadProgress() {
        viewModelScope.launch {
            for (i in 1..100) {
                _progress.value = i.toFloat() / 100
                delay(50)
            }
            val user = authRepository.observerCheckUser().first()
            if (user != null) {
                _splashEvent.emit(
                    SplashEvent.NavigateToHome
                )
            } else {
                _splashEvent.emit(
                    SplashEvent.NavigateToLogin
                )
            }

        }
    }
}

sealed class SplashEvent {
    data object NavigateToOnBoard : SplashEvent()
    data object NavigateToHome : SplashEvent()
    data object NavigateToLogin : SplashEvent()
}