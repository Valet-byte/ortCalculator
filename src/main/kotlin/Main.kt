package org.example

import org.example.model.Vector

fun main() {
    val e1 = Vector(3, -1, -1, 0)

    val a2 = Vector(2, -1, 0, 1)
    val a3 = Vector(1, 1, -2, 3)

    val lam21 = ((a2 * e1) / (e1 * e1)) * (-1)
    val lam31 = ((a3 * e1) / (e1 * e1)) * (-1)

    val e2 = a2 + e1 * lam21

    val lam32 = ((a3 * e2) / (e2 * e2)) * (-1)

    val e3 = a3 + e1 * lam31 + e2 * lam32

    println(e1)
    println(e2)
    println(e3)

    println()

    println(e1.normalizeCords())
    println(e2.normalizeCords())
    println(e3.normalizeCords())

    println()

    println(e1.normalize())
    println(e2.normalize())
    println(e3.normalize())

    println()

    println(e1 * e2)
    println(e1 * e3)
    println(e2 * e3)
}
