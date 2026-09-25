package com.example.firebaseauth.presentation.signup

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.firebaseauth.domain.AuthResultCheck
import com.example.firebaseauth.domain.repository.AuthRepository
import com.example.firebaseauth.shared.extentions.vmScopeMain
import com.example.firebaseauth.shared.validation.AuthValidator.validateConfirmPassword
import com.example.firebaseauth.shared.validation.AuthValidator.validateEmail
import com.example.firebaseauth.shared.validation.AuthValidator.validatePassword
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class SignUpViewModel(private val authRepository: AuthRepository) : ViewModel() {
    var state = mutableStateOf(SignupState())
        private set
    private val _events = MutableSharedFlow<SignUpEvent>()
    val event = _events.asSharedFlow()
    fun onIntent(signUpIntent: SignUpIntent) {
        when (signUpIntent) {
            is SignUpIntent.EmailChange -> {
                state.value = state.value.copy(email = signUpIntent.value, emailError = null)
            }

            is SignUpIntent.PassWordChange -> {
                state.value = state.value.copy(password = signUpIntent.value, passwordError = null)
            }

            is SignUpIntent.ConfirmPassWordChange -> {
                state.value = state.value.copy(
                    confirmPassword = signUpIntent.value,
                    confirmPasswordError = null
                )
            }

            SignUpIntent.NavigateToLogin -> {
                emit(SignUpEvent.NavigateToLogin)
            }
            SignUpIntent.TogglePasswordVisibility -> {
                state.value = state.value.copy(isPasswordVisible = !state.value.isPasswordVisible)
            }
            SignUpIntent.Submit -> {
                signUp()
            }
        }
    }

    private fun signUp() {
        if (!validate()) return
        vmScopeMain {
            state.value = state.value.copy(isLoading = true)
            val result = authRepository.signUp(
                email = state.value.email.trim(),
                password = state.value.password
            )
            when (result) {
                is AuthResultCheck.Success -> {
                    state.value = state.value.copy(isLoading = false)
                    emit(SignUpEvent.Authenticate)
                }

                is AuthResultCheck.Error -> {
                    state.value = state.value.copy(isLoading = false)
                    emit(SignUpEvent.ShowError(result.message))
                }
            }
        }
    }


    fun validate(): Boolean {
        val current = state.value
        val emailError = validateEmail(current.email)
        val passwordError = validatePassword(current.password)
        val confirmPasswordError = validateConfirmPassword(
            current.password, current.confirmPassword
        )
        state.value = current.copy(
            emailError = emailError,
            passwordError = passwordError,
            confirmPasswordError = confirmPasswordError
        )
        return listOf(emailError, passwordError, confirmPasswordError).all { it == null }
    }

    private fun emit(event: SignUpEvent) {
        vmScopeMain {
            _events.emit(event)
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

