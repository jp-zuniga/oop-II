package org.pedidos.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import org.pedidos.models.FormularioData
import org.pedidos.models.Producto

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun PedidoFormulario(
    modifier: Modifier = Modifier,
    data: FormularioData,
    dropdownExpanded: Boolean,
    isLoading: Boolean,
    showDialog: Boolean,
    onConfirmSubmit: () -> Unit,
    onDataChange: (FormularioData) -> Unit,
    onDismissDialog: () -> Unit,
    onDropdownChange: (Boolean) -> Unit,
    onRequestSubmit: () -> Unit,
) {
    val errores = data.errores()

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier
                .fillMaxSize()
                .padding(20.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            Text(
                style = MaterialTheme.typography.headlineLarge,
                text = "Formulario de Pedido",
            )

            Spacer(modifier = Modifier.height(20.dp))
            Text(
                style = MaterialTheme.typography.headlineSmall,
                text = "Cliente",
            )

            PedidoTextField(
                error = !errores.nombreErr,
                fieldName = "Nombre",
                fieldValue = data.nombre,
                onNewValue = { onDataChange(data.copy(nombre = it)) }
            )

            PedidoTextField(
                error = !errores.telefonoErr,
                fieldName = "Teléfono",
                fieldValue = data.telefono,
                onNewValue = { onDataChange(data.copy(telefono = it)) }
            )

            PedidoTextField(
                error = !errores.direccionErr,
                fieldName = "Dirección",
                fieldValue = data.direccion,
                onNewValue = { onDataChange(data.copy(direccion = it)) }
            )

            Spacer(modifier = Modifier.height(20.dp))
            Text(
                style = MaterialTheme.typography.headlineSmall,
                text = "Producto",
            )

            Spacer(modifier = Modifier.height(10.dp))
            ExposedDropdownMenuBox(
                expanded = dropdownExpanded,
                onExpandedChange = onDropdownChange,
            ) {
                TextField(
                    label = { Text("Producto") },
                    modifier = Modifier.menuAnchor(
                        enabled = true,
                        type = MenuAnchorType.PrimaryEditable,
                    ),
                    onValueChange = {},
                    readOnly = true,
                    value = data.producto,
                )

                ExposedDropdownMenu(
                    expanded = dropdownExpanded,
                    onDismissRequest = { onDropdownChange(false) },
                ) {
                    Producto.entries.forEach {
                        DropdownMenuItem(
                            onClick = {
                                onDataChange(data.copy(producto = it.name))
                                onDropdownChange(false)
                            },
                            text = { Text(text = it.name) },
                        )
                    }
                }
            }

            PedidoTextField(
                error = !errores.cantidadErr,
                fieldName = "Cantidad",
                fieldValue = data.cantidad,
                onNewValue = { onDataChange(data.copy(cantidad = it)) }
            )

            PedidoTextField(
                error = false,
                fieldName = "Notas Adicionales",
                fieldValue = data.notas ?: "",
                onNewValue = { onDataChange(data.copy(notas = it)) },
            )

            Spacer(modifier = Modifier.height(20.dp))
            Button(
                enabled = !errores.hayError() && !isLoading,
                onClick = onRequestSubmit,
            ) { Text("Enviar") }
        }

        if (showDialog) {
            AlertDialog(
                confirmButton = {
                    TextButton(onClick = onConfirmSubmit) {
                        Text("Confirmar")
                    }
                },
                dismissButton = {
                    TextButton(onClick = onDismissDialog) {
                        Text("Cancelar")
                    }
                },
                text = {
                    Text(
                        text = "¿Estás seguro de que deseas enviar este pedido? " +
                            "Por favor, verifica que los datos sean correctos."
                    )
                },
                title = { Text("Confirmar Pedido") },
                onDismissRequest = onDismissDialog,
            )
        }

        if (isLoading) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .pointerInput(Unit) {}
            )

            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}
