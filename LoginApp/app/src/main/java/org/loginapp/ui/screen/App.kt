package org.loginapp.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import org.loginapp.ui.theme.LoginAppTheme

@Composable
fun App() {
    var logged by remember { mutableStateOf(false) }

    AnimatedVisibility(
        visible = !logged,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        LoginScreen(onLoginSuccess = { logged = true })
    }

    AnimatedVisibility(
        visible = logged,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
         HomeScreen(onLogout = { logged = false })
    }
}

@Composable
@Preview(showBackground = true)
fun AppPreview() {
    LoginAppTheme { App() }
}
