package org.pedidos.models

data class FormularioErrors(
    val nombreErr: Boolean,
    val telefonoErr: Boolean,
    val direccionErr: Boolean,
    val cantidadErr: Boolean,
) {
    fun hayError(): Boolean {
        return !nombreErr || !telefonoErr || !direccionErr || !cantidadErr
    }
}
