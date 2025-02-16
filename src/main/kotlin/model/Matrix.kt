package org.example.model

data class Matrix(
    val values: MutableList<MutableList<Fraction>>,
) {

    fun getLine(index: Int): Vector {
        return Vector(values[index])
    }
    fun getColumn(index: Int): Vector {
        val list = mutableListOf<Fraction>()
        for (i in values) {
            list.add(i[index])
        }
        return Vector(list)
    }
    fun getValue(index1: Int, index2: Int): Fraction {
        return values[index1][index2]
    }
    fun plusLine(line: Vector, lineIndex: Int): Matrix {
        val matrix = this.copy()

        matrix.values[lineIndex] = (Vector(matrix.values[lineIndex]) + line).numbers

        return matrix
    }
    fun plusColumn(column: Vector, columnIndex: Int): Matrix {
        val matrix = this.copy()

        val values = matrix.values

        for ((i, value) in values.withIndex()) {
            value[columnIndex] = value[columnIndex] + column.get(i)
        }

        return matrix
    }
    companion object {
        fun getE(): Matrix {
            return Matrix(mutableListOf(
                mutableListOf(Fraction(1), Fraction(0), Fraction(0)),
                mutableListOf(Fraction(0), Fraction(1), Fraction(0)),
                mutableListOf(Fraction(0), Fraction(0), Fraction(1)),
            ))
        }
    }
}
