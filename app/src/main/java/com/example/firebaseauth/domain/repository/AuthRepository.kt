package com.example.firebaseauth.domain.repository

import com.example.firebaseauth.domain.AuthResultCheck
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun observerCheckUser(): Flow<AuthResultCheck?>
    suspend fun signUp( email: String, password: String): AuthResultCheck
    suspend fun signIn(email: String, password: String): AuthResultCheck
    suspend fun logOut()
}