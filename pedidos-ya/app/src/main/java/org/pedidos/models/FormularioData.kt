package org.pedidos.models

import android.util.Patterns
import kotlin.text.isBlank
import kotlin.text.toInt
import kotlin.text.toIntOrNull

data class FormularioData(
    val nombre: String,
    val telefono: String,
    val direccion: String,
    val producto: String,
    val cantidad: String,
    val notas: String?,
) {
    private fun validarNombre(): Boolean {
        return !nombre.isBlank() && nombre.length > 3
    }

    private fun validarTelefono(): Boolean {
        return !telefono.isBlank()
            && Patterns.PHONE.matcher(telefono).matches()
            && telefono.length >= 8
    }

    private fun validarDireccion(): Boolean {
        return !direccion.isBlank()
    }

    private fun validarCantidad(): Boolean {
        return !cantidad.isBlank()
            && cantidad.toIntOrNull() != null
            && cantidad.toInt() > 0
    }

    fun errores(): FormularioErrors {
        return FormularioErrors(
            validarNombre(),
            validarTelefono(),
            validarDireccion(),
            validarCantidad()
        )
    }
}
