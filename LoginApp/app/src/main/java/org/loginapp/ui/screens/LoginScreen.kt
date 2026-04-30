package org.loginapp.ui.screens

import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.loginapp.ui.theme.BluePrimary
import org.loginapp.ui.theme.BlueSecondary
import org.loginapp.ui.theme.White
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.loginapp.ui.theme.LoginAppTheme

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun LoginScreen(onLoginSuccess: () -> Unit) {
    val scope = rememberCoroutineScope()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var loading by remember { mutableStateOf(false) }

    val emailError = !Patterns.EMAIL_ADDRESS.matcher(email).matches()
    val emailBlankError = email.isBlank()

    val passError = password.length < 8
    val passBlankError = password.isBlank()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(BluePrimary, BlueSecondary)
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(White),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "UAM",
                    color = BluePrimary,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                color = White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                text = "Universidad Americana",
            )
        }

        Surface(
            color = White,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.75f),
            shape = RoundedCornerShape(topStart = 200.dp, topEnd = 200.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp, vertical = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(65.dp))
                Text(
                    color = BluePrimary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 26.sp,
                    text = "¡Bienvenido de nuevo!",
                )

                Spacer(modifier = Modifier.height(32.dp))
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = { Text("Correo", color = Color.Gray) },
                    modifier = Modifier.fillMaxWidth(),
                    isError = emailError,
                    supportingText = {
                        if (emailError) {
                            Text("¡El correo debe ser válido!")
                        }
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        errorBorderColor = Color.LightGray,
                        errorCursorColor = BluePrimary,
                        errorLabelColor = BluePrimary,
                        errorSupportingTextColor = BluePrimary,
                        focusedBorderColor = Color.LightGray,
                        unfocusedBorderColor = Color.LightGray,
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))
                var showPassword by remember { mutableStateOf(false) }
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = { Text("Contraseña", color = Color.Gray) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { showPassword = !showPassword }) {
                            Icon(
                                imageVector = if (showPassword) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = null,
                                tint = Color.Gray
                            )
                        }
                    },
                    isError = passError,
                    supportingText = {
                        if (passError) {
                            Text("Contraseña debe contener 8 caracteres")
                        }
                    },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.LightGray,
                        unfocusedBorderColor = Color.LightGray,

                        errorBorderColor = Color.LightGray,
                        errorCursorColor = BluePrimary,
                        errorLabelColor = BluePrimary,
                        errorSupportingTextColor = BluePrimary
                    )
                )

                Spacer(modifier = Modifier.height(40.dp))
                Button(
                    onClick = {
                        scope.launch {
                            loading = true
                            delay(1500)
                            loading = false
                            onLoginSuccess()
                        }
                    },
                    enabled = !emailError && !emailBlankError && !passError && !passBlankError && !loading,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BluePrimary

                    )
                ) {
                    if (loading) {
                        CircularProgressIndicator(
                            color = White,
                            modifier = Modifier.size(24.dp)
                        )
                    } else {
                        Text(
                            "Ingresar",
                            color = White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                TextButton(
                    onClick = { },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = buildAnnotatedString {
                            append("No tengo cuenta. ")
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("Crearme una.")
                            }
                        },
                        color = BluePrimary
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun LoginScreenPreview() {
    LoginAppTheme { LoginScreen { } }
}
