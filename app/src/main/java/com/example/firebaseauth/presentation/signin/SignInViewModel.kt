package com.example.firebaseauth.presentation.signin

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebaseauth.domain.AuthResultCheck
import com.example.firebaseauth.domain.repository.AuthRepository
import com.example.firebaseauth.presentation.signup.SignUpEvent
import com.example.firebaseauth.shared.extentions.vmScopeMain
import com.example.firebaseauth.shared.validation.AuthValidator.validateEmail
import com.example.firebaseauth.shared.validation.AuthValidator.validatePassword
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SignInViewModel(private val authRepository: AuthRepository) : ViewModel() {
    var state = mutableStateOf(LoginState())
        private set
    val _signInEvents = MutableSharedFlow<OnEvent>()
    val signInEvents = _signInEvents.asSharedFlow()

    fun onIntent(onIntent: OnIntent) {
        when (onIntent) {
            OnIntent.NavigateToSignUp -> {
                viewModelScope.launch {
                    _signInEvents.emit(OnEvent.GoToSignUpScreen)
                }
            }

            is OnIntent.EmailChange -> {
                state.value = state.value.copy(
                    email = onIntent.value,
                    emailError = null
                )
            }

            is OnIntent.PasswordChange -> {
                state.value = state.value.copy(
                    passWord = onIntent.value,
                    passwordError = null
                )
            }
            OnIntent.Submit -> {
                login()
            }

            OnIntent.TogglePasswordVisibility -> {
                state.value = state.value.copy(
                    isPasswordVisible = !state.value.isPasswordVisible
                )
            }
        }
    }

    private fun login() {
        if (!validate()) return
        vmScopeMain {
            state.value = state.value.copy(isLoading = true)
            val result = authRepository.signIn(
                email = state.value.email.trim(),
                password = state.value.passWord.trim()
            )
            when (result) {
                is AuthResultCheck.Success -> {
                    state.value = state.value.copy(isLoading = false)
                    emit(OnEvent.Authenticate)
                }

                is AuthResultCheck.Error -> {
                    state.value = state.value.copy(isLoading = false)
                    emit(OnEvent.ShowError(result.message))
                }
            }
        }
    }

    private fun emit(onEvent: OnEvent) {
        vmScopeMain {
            _signInEvents.emit(onEvent)
        }
    }

    private fun validate(): Boolean {
        val current = state.value
        val emailError = validateEmail(current.email)
        val passwordError = validatePassword(current.passWord)
        state.value = state.value.copy(
            emailError = emailError,
            passwordError = passwordError
        )
        return emailError == null && passwordError == null
    }
}

data class LoginState(
    val email: String = "",
    val passWord: String = "",
    val isPasswordVisible: Boolean = false,
    val isLoading: Boolean = false,
    val emailError: String? = null,
    val passwordError: String? = null
)

sealed class OnIntent {
    data object NavigateToSignUp : OnIntent()
    data object Submit : OnIntent()
    data object TogglePasswordVisibility : OnIntent()
    data class EmailChange(val value: String) : OnIntent()
    data class PasswordChange(val value: String) : OnIntent()
}

sealed class OnEvent {
    data object GoToSignUpScreen : OnEvent()
    data object Authenticate : OnEvent()
    data class ShowError(val message: String) : OnEvent()
}