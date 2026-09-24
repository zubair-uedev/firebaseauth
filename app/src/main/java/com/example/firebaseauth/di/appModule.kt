package com.example.firebaseauth.di

import com.example.firebaseauth.data.repository.AuthRepositoryImp
import com.example.firebaseauth.domain.repository.AuthRepository
import com.example.firebaseauth.presentation.onboarding.OnBoardingViewModel
import com.example.firebaseauth.presentation.signin.SignInViewModel
import com.example.firebaseauth.presentation.signup.SignUpViewModel
import com.example.firebaseauth.presentation.splash.SplashViewModel
import com.google.firebase.auth.FirebaseAuth
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::SplashViewModel)
    viewModelOf(::OnBoardingViewModel)
    viewModelOf(::SignInViewModel)
    viewModelOf(::SignUpViewModel)
    single<FirebaseAuth> { FirebaseAuth.getInstance() }
    single<AuthRepository> { AuthRepositoryImp(get()) }
}