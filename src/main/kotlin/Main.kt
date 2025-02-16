package org.example

import org.example.model.Fraction
import org.example.model.Matrix

fun main() {
    /*val e1 = Vector(3, 1, 1, 1)

    val a2 = Vector(1, 1, 3, 1)
    val a3 = Vector(1, 3, 1, 1)

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
    println(e2 * e3)*/

    var e = Matrix.getE()

    var matrix = Matrix(mutableListOf(
        mutableListOf(Fraction(2), Fraction(-1), Fraction(2)),
        mutableListOf(Fraction(-1), Fraction(1), Fraction(4)),
        mutableListOf(Fraction(2), Fraction(4), Fraction(3)),
    ))

    println(matrix)

    var multiplier = matrix.getValue(0,2) / matrix.getValue(0,0)

    var line = (matrix.getLine(0) * multiplier) * -1
    var column = (matrix.getColumn(0) * multiplier) * -1

    matrix = matrix.plusLine(line, 2)
    matrix = matrix.plusColumn(column, 2)

    line = (e.getLine(0) * multiplier) * -1
    column = (e.getColumn(0) * multiplier) * -1
    e = e.plusLine(line, 2)
    e = e.plusColumn(column, 2)

    multiplier = matrix.getValue(0,1) / matrix.getValue(0,0)

    line = (matrix.getLine(0) * multiplier) * -1
    column = (matrix.getColumn(0) * multiplier) * -1
    matrix = matrix.plusLine(line, 1)
    matrix = matrix.plusColumn(column, 1)

    line = (e.getLine(0) * multiplier) * -1
    column = (e.getColumn(0) * multiplier) * -1
    e = e.plusLine(line, 1)
    e = e.plusColumn(column, 1)

    line = matrix.getLine(2)
    column = matrix.getColumn(2)
    matrix = matrix.plusLine(line, 1)
    matrix = matrix.plusColumn(column, 1)

    line = e.getLine(2)
    column = e.getColumn(2)
    e = e.plusLine(line, 1)
    e = e.plusColumn(column, 1)

    multiplier = matrix.getValue(1,2) / matrix.getValue(1,1)

    line = (matrix.getLine(1) * multiplier) * -1
    column = (matrix.getColumn(1) * multiplier) * -1
    matrix = matrix.plusLine(line, 2)
    matrix = matrix.plusColumn(column, 2)

    line = (e.getLine(1) * multiplier) * -1
    column = (e.getColumn(1) * multiplier) * -1
    e = e.plusLine(line, 2)
    e = e.plusColumn(column, 2)

    println(matrix)
    println(e)
}
