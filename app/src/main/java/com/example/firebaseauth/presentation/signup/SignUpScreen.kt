package com.example.firebaseauth.presentation.signup

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firebaseauth.R
import com.example.firebaseauth.shared.components.BaseButton
import com.example.firebaseauth.shared.components.HeaderSignIn
import com.example.firebaseauth.shared.components.LoadingOverlay
import com.example.firebaseauth.shared.components.TextFields
import com.example.firebaseauth.shared.components.TextView

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    SignUpScreen()
}

@Composable
fun SignUpScreen(
    state: SignupState = SignupState(),
    onIntent: (SignUpIntent) -> Unit = {}
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFFF9F9F9),
                            Color(0xFFF9F9F9),
                            Color.White
                        )
                    )
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            HeaderSignIn(
                img = R.drawable.ic_back_img,
                title = "Create Account",
                des = "Create your account and get started",
                modifier = Modifier.padding(bottom = 10.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 10.dp, end = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                TextFields(
                    value = state.email,
                    onValueChange = { onIntent(SignUpIntent.EmailChange(it)) },
                    label = "Email Address",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    trailingIcon = {},
                    isError = state.emailError != null
                )

                AnimatedVisibility(visible = state.emailError != null) {
                    TextView(
                        text = state.emailError.orEmpty(),
                        color = MaterialTheme.colorScheme.error,
                        size = 12.sp,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Start
                    )
                }
                TextFields(
                    value = state.password,
                    onValueChange = { onIntent(SignUpIntent.PassWordChange(it)) },
                    label = "Enter password",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = if (state.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        Icon(
                            imageVector = if (state.isPasswordVisible) {
                                Icons.Default.VisibilityOff
                            } else {
                                Icons.Default.Visibility
                            },
                            contentDescription = "Toggle Password Visibility",
                            modifier = Modifier.clickable {
                                onIntent(SignUpIntent.TogglePasswordVisibility)
                            }
                        )
                    },
                    isError = state.passwordError != null
                )
                AnimatedVisibility(visible = state.passwordError != null) {
                    TextView(
                        text = state.passwordError.orEmpty(),
                        color = MaterialTheme.colorScheme.error,
                        size = 12.sp,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Start
                    )
                }

                TextFields(
                    value = state.confirmPassword,
                    onValueChange = { onIntent(SignUpIntent.ConfirmPassWordChange(it)) },
                    label = "Enter password",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = PasswordVisualTransformation(),
                    trailingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.ic_password_),
                            contentDescription = null
                        )
                    },
                    isError = state.confirmPasswordError != null
                )
                AnimatedVisibility(visible = state.confirmPasswordError != null) {
                    TextView(
                        text = state.confirmPasswordError.orEmpty(),
                        color = MaterialTheme.colorScheme.error,
                        size = 12.sp,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Start
                    )
                }
                TextView(
                    text = "By signing up, you agree to Ignite's Terms of Service and \n" +
                            "Privacy Policy.",
                )
                BaseButton(
                    onClick = { onIntent(SignUpIntent.Submit) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    TextView(
                        text = "Create Account",
                        color = Color.White,
                        modifier = Modifier.padding(12.dp)
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextView(
                        text = "Already have an account?",
                        size = 17.sp
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    TextView(
                        text = "Sign In",
                        color = Color.Red,
                        size = 17.sp,
                        modifier = Modifier.clickable {
                            onIntent(SignUpIntent.NavigateToLogin)
                        }
                    )
                }
            }
            LoadingOverlay(isLoading = state.isLoading)
        }
    }
}

