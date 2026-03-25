import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

fun main() {
    menu()

    val t = readln()

    t.forEachIndexed { i, c -> println("$i => $c") }
    if (t.any { it.isLetter() }) {
        println("$t solo tiene letras.")
    } else {
        println("$t no es solo letras")
    }
}

fun calcular(a: Int, b: Int, op: String): Int {
    return when (op) {
        "+" -> a + b
        "-" -> a - b
        "*" -> a * b
        "/" -> if (b == 0) 0 else a / b
        else -> 0
    }
}

fun menu() {
    var op = 0

    while (op != 4) {
        println()
        println("Escoger:")
        println("1. Saludar")
        println("2. ¿Qué hora es?")
        println("3. Hacer un cálculo")
        println("4. Saludar")
        print("=> ")

        op = readln().trim().toInt()

        when (op) {
            1 -> println("¡Hola!")
            2 -> println(
                "La hora es ${
                    ZonedDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm a"))
                }."
            )

            3 -> {
                print("Ingrese el primer numero: ")
                val a = readln().trim().toInt()
                print("Ingrese el segundo numero: ")
                val b = readln().trim().toInt()
                print("Ingrese la operación (+, -, *, /): ")
                val op = readln().trim()

                if ("+-*/".contains(op)) {
                    println("$a $op $b = ${calcular(a, b, op)}")
                } else {
                    println("¡Operación inválida!")
                }
            }

            4 -> println("Saliendo del sistema...")
            else -> println("¡Esa opción no existe!")
        }
    }
}
