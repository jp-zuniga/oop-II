fun main() {
    val a = 2
    val b = 4

    println("$a / $b = ${div(5, 3)}")

    when (a) {
        2 -> println("a = $a")
    }

    for (i in 1..10 step 2) {
        println(i)
    }
}

fun sum(a: Int, b: Int) = a + b

fun sub(a: Int, b: Int) = a - b

fun mult(a: Int, b: Int) = a * b

fun div(a: Int, b: Int) = a / b
