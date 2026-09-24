package com.example.firebaseauth.domain

import com.google.firebase.auth.FirebaseUser

sealed interface AuthResultCheck {
    data class Success(val firebase: FirebaseUser) : AuthResultCheck
    data class Error(val message: String) : AuthResultCheck
}