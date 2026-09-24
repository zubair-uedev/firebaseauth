package com.example.firebaseauth.shared.extentions

import android.util.Patterns

fun String.isValidEmail(): Boolean = Patterns.EMAIL_ADDRESS.matcher(this.trim()).matches()