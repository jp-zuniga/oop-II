package org.formulario

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.formulario.ui.screen.FormularioScreen
import org.formulario.ui.theme.FormularioTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { FormularioTheme { FormularioScreen() } }
    }
}

@Composable
@Preview(showBackground = true)
fun FormularioScreenPreview() {
    FormularioTheme { FormularioScreen() }
}
