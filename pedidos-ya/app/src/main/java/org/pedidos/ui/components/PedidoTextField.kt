package org.pedidos.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PedidoTextField(
    error: Boolean?,
    fieldName: String,
    fieldValue: String,
    onNewValue: (String) -> Unit,
) {
    Spacer(modifier = Modifier.height(10.dp))
    OutlinedTextField(
        isError = error == true,
        label = { Text(fieldName) },
        onValueChange = onNewValue,
        supportingText = {
            if (error == true) {
                Text("¡Este campo es requerido!")
            }
        },
        value = fieldValue,
    )
}
