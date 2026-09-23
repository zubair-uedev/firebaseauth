package com.example.firebaseauth.presentation.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SignInViewModel() : ViewModel() {
    val _signInEvents = MutableSharedFlow<OnEvent>()
    val signInEvents = _signInEvents.asSharedFlow()

    fun onIntent(onIntent: OnIntent) {
        when (onIntent) {
            OnIntent.NavigateToSignUp -> {
                viewModelScope.launch {
                    _signInEvents.emit(OnEvent.GoToSignUpScreen)
                }
            }
        }
    }
}

sealed class OnIntent {
    data object NavigateToSignUp : OnIntent()
}

sealed class OnEvent {
    data object GoToSignUpScreen : OnEvent()
}