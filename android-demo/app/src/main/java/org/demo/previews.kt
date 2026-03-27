package org.demo

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.demo.components.Contador
import org.demo.ui.theme.AndroidDemoTheme

@Composable
@Preview(showBackground = true)
fun ContadorPreview() {
    AndroidDemoTheme(darkTheme = true) {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Contador(modifier = Modifier.padding(innerPadding))
        }
    }
}
