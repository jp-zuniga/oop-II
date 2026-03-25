package models

class Estudiante(
    val id: Int,
    val name: String,
    val cif: String,
) {
    override fun toString(): String {
        return "Estudiante(id=$id, name='$name', cif='$cif')"
    }
}
