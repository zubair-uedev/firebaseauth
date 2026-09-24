package com.example.firebaseauth.data.repository

import com.example.firebaseauth.domain.AuthResultCheck
import com.example.firebaseauth.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class AuthRepositoryImp(private val firebaseAuth: FirebaseAuth) : AuthRepository {
    override fun observerCheckUser(): Flow<AuthResultCheck?> = callbackFlow {
        val listener = FirebaseAuth.AuthStateListener { auth ->
            val user = auth.currentUser
            trySend(
                if (user != null) AuthResultCheck.Success(firebase = user)
                else null
            )
        }
        firebaseAuth.addAuthStateListener(listener)
        awaitClose {
            firebaseAuth.removeAuthStateListener(listener)
        }
    }

    override suspend fun signUp(
        email: String,
        password: String
    ): AuthResultCheck {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            val user = result.user
            if (user != null) AuthResultCheck.Success(firebase = user)
            else AuthResultCheck.Error("Sign up failed, please try again")
        } catch (E: Exception) {
            AuthResultCheck.Error(E.message ?: "Something went wrong")
        }
    }

    override suspend fun signIn(
        email: String,
        password: String
    ): AuthResultCheck {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            val user = result.user
            if (user != null) AuthResultCheck.Success(firebase = user)
            else AuthResultCheck.Error("Login failed, please try again")
        } catch (E: Exception) {
            AuthResultCheck.Error(E.message ?: "Something went wrong")
        }
    }
    override suspend fun logOut() {
        firebaseAuth.signOut()
    }

}