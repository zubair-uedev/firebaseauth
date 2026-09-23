package com.example.firebaseauth.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class OnBoardingViewModel() : ViewModel() {
    val _onBoardState = MutableSharedFlow<OnBoardingEvent>()
    val onBoardState = _onBoardState.asSharedFlow()

    fun onIntent(onBoardingIntent: OnBoardingIntent) {
        when (onBoardingIntent) {
            OnBoardingIntent.OnIntentsOnBoarding -> {
                viewModelScope.launch {
                    _onBoardState.emit(OnBoardingEvent.NavigateToSignIn)
                }
            }
        }
    }
}

sealed class OnBoardingIntent {
    data object OnIntentsOnBoarding : OnBoardingIntent()
}

sealed class OnBoardingEvent {
    data object NavigateToSignIn : OnBoardingEvent()
}