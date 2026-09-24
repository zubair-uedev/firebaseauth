package com.example.firebaseauth.shared.validation

import com.example.firebaseauth.shared.extentions.isValidEmail

object AuthValidator {
    fun validateEmail(email: String): String? = when {
        email.isBlank() -> "Email cannot be empty"
        !email.isValidEmail() -> "Enter a valid email"
        else -> null
    }

    fun validatePassword(password: String): String? = when {
        password.isBlank() -> "Password cannot be empty"
        password.length < 6 -> "Password must be at least 6 characters"
        else -> null
    }

    fun validateConfirmPassword(password: String, confirm: String): String? =
        if (password != confirm) "Passwords do not match" else null
}