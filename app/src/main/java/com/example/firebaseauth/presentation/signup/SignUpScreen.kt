package com.example.firebaseauth.presentation.signup

import androidx.compose.foundation.background
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
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firebaseauth.R
import com.example.firebaseauth.shared.components.BaseButton
import com.example.firebaseauth.shared.components.HeaderSignIn
import com.example.firebaseauth.shared.components.TextFields
import com.example.firebaseauth.shared.components.TextView

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    SignUpScreen()
}

@Composable
fun SignUpScreen() {
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
                    value = "brola@gmail.com",
                    onValueChange = {},
                    label = "Email Address",
                    visualTransformation = VisualTransformation.None,
                    trailingIcon = {}
                )
                TextFields(
                    value = "123456",
                    onValueChange = {},
                    label = "Enter password",
                    visualTransformation = VisualTransformation.None,
                    trailingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.ic_password_),
                            contentDescription = null
                        )
                    }
                )
                TextFields(
                    value = "123456",
                    onValueChange = {},
                    label = "Enter password",
                    visualTransformation = VisualTransformation.None,
                    trailingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.ic_password_),
                            contentDescription = null
                        )
                    }
                )
                TextView(
                    text = "By signing up, you agree to Ignite's Terms of Service and \n" +
                            "Privacy Policy.",
                )
                BaseButton(
                    onClick = {},
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
                        size = 17.sp
                    )
                }
            }
        }
    }
}

