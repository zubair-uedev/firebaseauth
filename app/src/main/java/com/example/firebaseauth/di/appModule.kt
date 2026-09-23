package com.example.firebaseauth.di

import com.example.firebaseauth.presentation.onboarding.OnBoardingViewModel
import com.example.firebaseauth.presentation.signin.SignInViewModel
import com.example.firebaseauth.presentation.splash.SplashViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::SplashViewModel)
    viewModelOf(::OnBoardingViewModel)
    viewModelOf(::SignInViewModel)
}