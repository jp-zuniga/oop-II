package org.loginapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.loginapp.ui.theme.LoginAppTheme

@Composable
fun HomeView(onNavigationToDetail: (Int) -> Unit) {
    Column {
        Button(
            onClick = { onNavigationToDetail(1) },
        ) {
            Text(text = "Ir al detalle")
        }
    }
}

@Composable
@Preview
fun HomeViewPreview() {
    LoginAppTheme { HomeView {  } }
}
