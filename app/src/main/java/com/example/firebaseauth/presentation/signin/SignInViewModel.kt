package com.example.firebaseauth.presentation.signin

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class SignInViewModel() : ViewModel() {
    val _signInEvents = MutableSharedFlow<OnEvent>()
    val signInEvents = _signInEvents.asSharedFlow()

    fun onIntent(){

    }
}

sealed class OnIntent {
    data object NavigateToSignIn : OnIntent()
}

sealed class OnEvent {
    data object GoToSignInScreen : OnEvent()
}