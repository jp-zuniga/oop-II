package org.loginapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.loginapp.ui.theme.LoginAppTheme

@Composable
fun DetailView(
    userId: Int,
    onBack: () -> Unit,
) {
    Column {
        Text(text = "Detalle de Usuario #${userId}")
    }

    Button(onClick = onBack) {
        Text(text = "Regresar")
    }
}

@Composable
@Preview
fun DetailViewPreview() {
    LoginAppTheme { DetailView(1) {  } }
}
