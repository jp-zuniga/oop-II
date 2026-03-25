import models.Estudiante

fun main() {
    val nombres = mutableListOf("Ana", "Pablo", "Alicia", "Franya")

    println(nombres.find { it.length == 3 })

    val filter = nombres.filter { it.startsWith("A") }

    println(filter)

    maps()
    classes()
}

fun maps() {
    val estudiantes = mutableMapOf(
        "23012271" to "Joaquín",
        "23021809" to "Franya",
    )

    estudiantes["23021184"] = "Angie"

    // for (p in estudiantes) {
    //     println(p)
    // }

    for ((k, v) in estudiantes) {
        println("$k: $v")
    }
}

fun classes() {
    val estudiantes = mutableListOf<Estudiante>()

    val e1 = Estudiante(1, "Alicia", "12345678")
    val e2 = Estudiante(1, "Cuajin", "17221032")

    estudiantes.add(e1)
    estudiantes.add(e2)

    println("$estudiantes")
}
