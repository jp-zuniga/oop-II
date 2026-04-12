package org.formulario.ui.screen

import android.util.Patterns
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun FormularioScreen() {
    var nombre by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }

    val nombreError = nombre.isBlank()
    val emailError = email.isBlank() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()
    val edadError = edad.isBlank() || edad.toIntOrNull() == null

    val noErrors = !(nombreError || emailError || edadError)

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
        ) {
            Text(
                style = MaterialTheme.typography.headlineLarge,
                text = "Formulario",
            )

            SetupField(
                error = nombreError,
                fieldName = "Nombre",
                fieldValue = nombre,
                onNewValue = { nombre = it }
            )

            SetupField(
                error = emailError,
                fieldName = "Email",
                fieldValue = email,
                onNewValue = { email = it }
            )

            SetupField(
                error = edadError,
                fieldName = "Edad",
                fieldValue = edad,
                onNewValue = { edad = it }
            )

            Spacer(modifier = Modifier.height(20.dp))
            Button(
                enabled = noErrors,
                onClick = {
                    scope.launch {
                        snackbarHostState.showSnackbar("¡Formulario enviado!")
                    }
                },
            ) { Text("Enviar") }
        }
    }
}

@Composable
fun SetupField(
    error: Boolean,
    fieldName: String,
    fieldValue: String,
    onNewValue: (String) -> Unit,
) {
    Spacer(modifier = Modifier.height(20.dp))
    OutlinedTextField(
        isError = error,
        label = { Text(fieldName) },
        onValueChange = onNewValue,
        supportingText = {
            if (error) {
                Text("¡${fieldName} requerido!")
            }
        },
        value = fieldValue,
    )
}
