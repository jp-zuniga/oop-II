package org.pedidos.ui.screen

import android.util.Patterns
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.pedidos.models.FormularioData
import org.pedidos.models.Producto
import org.pedidos.ui.components.PedidoFormulario
import org.pedidos.ui.components.PedidoTextField

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun PedidosYaScreen() {
    var formData by remember {
        mutableStateOf(
            FormularioData(
                nombre = "",
                telefono = "",
                direccion = "",
                producto = Producto.Americano.toString(),
                cantidad = "",
                notas = "",
            )
        )
    }

    var dropdownExpanded by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }) { padding ->
        PedidoFormulario(
            modifier = Modifier.padding(padding),
            data = formData,
            dropdownExpanded = dropdownExpanded,
            isLoading = isLoading,
            showDialog = showDialog,
            onConfirmSubmit = {
                showDialog = false

                scope.launch {
                    isLoading = true

                    delay(2000)

                    isLoading = false
                    formData = FormularioData(
                        nombre = "",
                        telefono = "",
                        direccion = "",
                        producto = Producto.Americano.toString(),
                        cantidad = "",
                        notas = "",
                    )

                    snackbarHostState.showSnackbar("¡Pedido enviado exitosamente!")
                }
            },
            onDataChange = { formData = it },
            onDismissDialog = { showDialog = false },
            onDropdownChange = { dropdownExpanded = it },
            onRequestSubmit = { showDialog = true },
        )
    }
}

