package org.example.model

import java.math.BigDecimal
import java.math.MathContext

data class Vector(
    val numbers: List<Fraction>,
) {
    constructor(vararg nums: Int) : this(nums.map { it.fraction() }.toList())

    operator fun plus(other: Vector): Vector {
        if (other.numbers.size != this.numbers.size) {
            throw MatchException("Vectors have different size", null)
        }

        val nums = ArrayList<Fraction>(other.numbers.size)

        for (i in 0 until other.numbers.size) {
            nums.add(i, other.numbers[i] + numbers[i])
        }

        return Vector(nums)
    }

    operator fun minus(other: Vector): Vector {
        if (other.numbers.size != this.numbers.size) {
            throw MatchException("Vectors have different size", null)
        }

        val nums = ArrayList<Fraction>(other.numbers.size)

        for (i in 0 until other.numbers.size) {
            nums.add(i, other.numbers[i] - numbers[i])
        }

        return Vector(nums)
    }

    operator fun times(other: Vector): Fraction {
        if (other.numbers.size != this.numbers.size) {
            throw MatchException("Vectors have different size", null)
        }

        var result = Fraction(0, 1)

        for (i in 0 until other.numbers.size) {
            result += numbers[i] * other.numbers[i]
        }

        return result
    }

    operator fun times(other: Int): Vector {
        val nums = ArrayList<Fraction>(numbers.size)

        for (i in numbers.indices) {
            nums.add(i, numbers[i] * other)
        }

        return Vector(nums)
    }

    operator fun times(other: Fraction): Vector {
        val nums = ArrayList<Fraction>(numbers.size)

        for (i in numbers.indices) {
            nums.add(i, numbers[i] * other)
        }

        return Vector(nums)
    }

    fun normalize(): Vector {
        var nums = this.numbers
        if (!isNormalizedCords()){
            nums = normalizeCords().second.numbers
        }

        var sumCords = BigDecimal.ZERO
        for (number in nums) {
            sumCords += (number.numerator * number.numerator)
        }

        if (isPerfectSquare(sumCords)) {
            sumCords = sumCords.sqrt(MathContext.DECIMAL128)
            return Vector(nums.map { Fraction(it.numerator, sumCords) })
        }
        return Vector(nums.map { Fraction(it.numerator, sumCords, true) })
    }

    private fun isNormalizedCords(): Boolean {
        for (i in numbers.indices) {
            if (numbers[i].denominator != BigDecimal.ONE) {
                return false
            }
        }
        return true
    }

    fun  normalizeCords(): Pair<Fraction, Vector> {
        var maxDenominator = Int.MIN_VALUE.toBigDecimal()

        for (number in numbers) {
            if (number.denominator > maxDenominator) {
                maxDenominator = number.denominator
            }
        }

        if (maxDenominator == BigDecimal.ONE) {
            return Pair(Fraction(BigDecimal.ONE), this)
        }

        val delim = Fraction(BigDecimal.ONE, maxDenominator)

        val result = numbers.map { it / delim }

        return Pair(Fraction(BigDecimal.ONE, maxDenominator), Vector(result))

    }

    private fun isPerfectSquare(number: BigDecimal): Boolean {
        if (number < BigDecimal.ZERO) {
            return false
        }

        val sqrt = number.sqrt(MathContext.DECIMAL128)
        return sqrt.scale() == 0 && sqrt.multiply(sqrt)
            .compareTo(number) == 0
    }

    override fun toString(): String {
        return numbers.toString()
    }
}
