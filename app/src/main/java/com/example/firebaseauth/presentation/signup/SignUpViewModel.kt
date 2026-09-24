package com.example.firebaseauth.presentation.signup

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.firebaseauth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class SignUpViewModel(private val authRepository: AuthRepository) : ViewModel() {
    var state = mutableStateOf(SignupState())
        private set
    private val _events = MutableSharedFlow<SignUpEvent>()
    val event = _events.asSharedFlow()
    fun onIntents(signUpIntent: SignUpIntent) {
        when (signUpIntent) {
            is SignUpIntent.EmailChange -> {
                state.value = state.value.copy(email = signUpIntent.value, emailError = null)
            }
            is SignUpIntent.PassWordChange -> {
                state.value = state.value.copy(password = signUpIntent.value, passwordError = null)
            }
            is SignUpIntent.ConfirmPassWordChange -> {
                state.value = state.value.copy(confirmPassword = signUpIntent.value, confirmPasswordError = null)
            }
            SignUpIntent.NavigateToLogin -> {
                _events.emit(

                )
            }

            SignUpIntent.TogglePasswordVisibility -> {
                state.value = state.value.copy(isPasswordVisible = !state.value.isPasswordVisible)
            }

            SignUpIntent.Submit -> {

            }
        }
    }
}

data class SignupState(
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isPasswordVisible: Boolean = false,
    val isLoading: Boolean = false,
    val emailError: String? = null,
    val passwordError: String? = null,
    val confirmPasswordError: String? = null
)

sealed interface SignUpIntent {
    data class EmailChange(val value: String) : SignUpIntent
    data class PassWordChange(val value: String) : SignUpIntent
    data class ConfirmPassWordChange(val value: String) : SignUpIntent
    data object TogglePasswordVisibility : SignUpIntent
    data object Submit : SignUpIntent
    data object NavigateToLogin : SignUpIntent
}

sealed interface SignUpEvent {
    data object Authenticate : SignUpEvent
    data object NavigateToLogin : SignUpEvent
    data class ShowError(val message: String) : SignUpEvent
}

