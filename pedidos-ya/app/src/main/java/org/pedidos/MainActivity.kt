package org.pedidos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.pedidos.ui.screen.PedidosYaScreen
import org.pedidos.ui.theme.PedidosYaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { PedidosYaTheme { PedidosYaScreen() } }
    }
}

@Composable
@Preview(showBackground = true)
fun PedidosYaScreenPreview() {
    PedidosYaTheme { PedidosYaScreen() }
}
