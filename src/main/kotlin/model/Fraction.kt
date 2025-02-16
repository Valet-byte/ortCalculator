package org.example.model

import java.math.BigDecimal

data class Fraction(
    val numerator: BigDecimal,
    val denominator: BigDecimal,
    val isDenominatorSqrt: Boolean = false
) {

    constructor(num: Int, den: Int) : this(num.toBigDecimal(), den.toBigDecimal())
    constructor(num: Int) : this(num.toBigDecimal(), BigDecimal.ONE)
    constructor(num: BigDecimal) : this(num, BigDecimal.ONE)
    constructor(num: Int, den: Int, isDenominatorSqrt: Boolean) : this(num.toBigDecimal(), den.toBigDecimal(), isDenominatorSqrt)

    operator fun plus(other: Fraction): Fraction {
        return Fraction(other.numerator * denominator + numerator * other.denominator, other.denominator * denominator).reduce()
    }

    operator fun plus(other: Int): Fraction {
        return Fraction(numerator + (other.toBigDecimal() * denominator), denominator).reduce()
    }

    operator fun minus(other: Fraction): Fraction {
        return Fraction(other.numerator * denominator - numerator * other.denominator, other.denominator * denominator).reduce()
    }

    operator fun minus(other: Int): Fraction {
        return Fraction(numerator - (other.toBigDecimal() * denominator), denominator).reduce()
    }

    operator fun times(other: Fraction): Fraction {
        return Fraction(numerator * other.numerator, denominator * other.denominator).reduce()
    }

    operator fun times(other: Int): Fraction {
        return Fraction(numerator * other.toBigDecimal(), denominator).reduce()
    }

    operator fun times(other: BigDecimal): Fraction {
        return Fraction(numerator * other, denominator).reduce()
    }

    operator fun div(other: Fraction): Fraction {
        return Fraction(numerator * other.denominator, denominator * other.numerator).reduce()
    }

    operator fun div(other: BigDecimal): Fraction {
        return this / Fraction(other, BigDecimal.ONE)
    }

    operator fun div(other: Double): Fraction {
        return this / Fraction(other.toBigDecimal(), BigDecimal.ONE)
    }

    fun reduce(): Fraction {
        try {
            val gcd = gcd(numerator, denominator)
            return Fraction(numerator / gcd, denominator / gcd)
        } catch (e: ArithmeticException) {
            return this
        }

    }

    private fun gcd(a: BigDecimal, b: BigDecimal): BigDecimal {
        var num1 = b
        var num2 = a

        while (num2 != BigDecimal.ZERO) {
            val temp = num2
            num2 = num1 % num2
            num1 = temp
        }

        return num1
    }

    override fun toString(): String {
        return if (numerator == denominator) "1"
        else if (denominator == BigDecimal.ONE) numerator.toString()
        else if (numerator % denominator == BigDecimal.ZERO) {
            if (denominator < BigDecimal.ZERO) "-$numerator"
            else numerator.toString()
        }
        else if (isDenominatorSqrt) "($numerator/√$denominator)"
        else "($numerator/$denominator)"
    }

    fun equals(other: Int): Boolean {
        return this == (other div 1)
    }
}


infix fun Int.div(other: Int): Fraction {
    return Fraction(this.toBigDecimal(), other.toBigDecimal()).reduce()
}

fun Int.fraction(): Fraction {
    return Fraction(this.toBigDecimal(), BigDecimal.ONE)
}
