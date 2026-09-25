package com.example.firebaseauth.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.firebaseauth.presentation.dashboard.DashBoardScreen
import com.example.firebaseauth.presentation.onboarding.OnBoardingEvent
import com.example.firebaseauth.presentation.onboarding.OnBoardingScreen
import com.example.firebaseauth.presentation.onboarding.OnBoardingViewModel
import com.example.firebaseauth.presentation.signin.OnEvent
import com.example.firebaseauth.presentation.signin.SignInScreen
import com.example.firebaseauth.presentation.signin.SignInViewModel
import com.example.firebaseauth.presentation.signup.SignUpEvent
import com.example.firebaseauth.presentation.signup.SignUpScreen
import com.example.firebaseauth.presentation.signup.SignUpViewModel
import com.example.firebaseauth.presentation.splash.SplashEvent
import com.example.firebaseauth.presentation.splash.SplashScreen
import com.example.firebaseauth.presentation.splash.SplashViewModel
import com.example.firebaseauth.routes.Routes
import org.koin.androidx.compose.koinViewModel

@Composable
fun Navigation() {
    val backStack = rememberNavBackStack(Routes.SplashRoute)
    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<Routes.SplashRoute> {
                val splashViewModel: SplashViewModel = koinViewModel()
                val progress by splashViewModel.progress.collectAsState()
                LaunchedEffect(Unit) {
                    splashViewModel.splashEvent.collect { events ->
                        when (events) {
                            SplashEvent.NavigateToOnBoard -> {
                                backStack.add(
                                    Routes.OnBoardRoute
                                )
                            }
                        }
                    }
                }
                SplashScreen(progress = progress)
            }
            entry<Routes.OnBoardRoute> {
                val onBoardingViewModel: OnBoardingViewModel = koinViewModel()
                LaunchedEffect(Unit) {
                    onBoardingViewModel._onBoardState.collect { event ->
                        when (event) {
                            OnBoardingEvent.NavigateToSignIn -> {
                                backStack.add(
                                    Routes.SignInRoute
                                )
                            }
                        }
                    }
                }
                OnBoardingScreen(onIntent = onBoardingViewModel::onIntent)
            }
            entry<Routes.SignInRoute> {
                val signInViewModel: SignInViewModel = koinViewModel()
                LaunchedEffect(Unit) {
                    signInViewModel.signInEvents.collect { event ->
                        when (event) {
                            OnEvent.GoToSignUpScreen -> {
                                backStack.add(
                                    Routes.SignUpRoute
                                )
                            }
                        }
                    }
                }
                SignInScreen(onIntent = signInViewModel::onIntent)
            }
            entry<Routes.SignUpRoute> {
                val signUpViewModel: SignUpViewModel = koinViewModel()
                LaunchedEffect(Unit)
                {
                    signUpViewModel.event.collect { event ->
                        when (event) {
                            SignUpEvent.Authenticate -> {}
                            SignUpEvent.NavigateToLogin -> {
                                backStack.add(Routes.SignInRoute)
                            }
                            is SignUpEvent.ShowError -> {}
                        }
                    }
                }
                SignUpScreen(
                    onIntent = signUpViewModel::onIntent
                )
            }
            entry<Routes.DashBoardRoute> {
                DashBoardScreen()
            }
        }
    )
}