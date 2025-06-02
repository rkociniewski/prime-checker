package rk.powermilk

import kotlin.math.sqrt

private const val THREE = 3

/**
 * Extension function to check if a number is prime.
 *
 * @return `true` if the number is prime, `false` otherwise.
 * @throws ArithmeticException if the number is zero or negative.
 */
fun Int.isPrime(): Boolean {
    if (this <= 0) throw ArithmeticException("The number should be non-negative")

    val result = when {
        this == 1 -> false
        this == 2 -> true
        this % 2 == 0 -> false
        else -> {
            val limit = sqrt(this.toDouble()).toInt()
            (THREE..limit step 2).none { this % it == 0 }
        }
    }
    return result
}

/**
 * Generates an array of prime numbers from 2 to the given [number] (inclusive).
 *
 * @return an array of primes up to [number].
 */
fun generatePrimes(number: Int): IntArray =
    if (number < 2) intArrayOf()
    else (2..number).asSequence().filter { it.isPrime() }.toList().toIntArray()

/**
 * Extension function to filter only prime numbers from an array.
 *
 * @return an array containing only prime numbers greater than zero.
 */
fun IntArray.filterNonPrimes(): IntArray =
    asSequence().filter { it > 1 && it.isPrime() }.toList().toIntArray()

/**
 * Extension function to check if all numbers in the array are prime.
 *
 * @return `true` if the array is not empty and all values are prime numbers.
 */
fun IntArray.areAllPrimes() = isNotEmpty() && all { it > 0 && it.isPrime() }
