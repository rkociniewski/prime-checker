package org.powermilk

/**
 * Function extension to if number ([this]) is prime.
 *
 * @return `true` if number is prime number, `false` otherwise
 */
fun Int.isPrime() = when (this) {
    in Int.MIN_VALUE..0 -> throw ArithmeticException("The number should be non-negative")
    1 -> false
    else -> (2 until this).none { this % it == 0 }
}

/**
 * Function extension to generating array with prime numbers for 2 to n ([this]).
 *
 * @return array with prime numbers
 */
fun Int.generatePrimes() = (2..this).filter { it.isPrime() }.toIntArray()

/**
 *  Function extension to filter non-prime numbers from array ([this]).
 *
 *  @return filtered array with non-prime numbers.
 */
fun IntArray.filterNonPrimes() = filter { it != 0 && it.isPrime() }.toIntArray()

/**
 * Function extension to check if all numbers in array are prime numbers.
 */
fun IntArray.areAllPrimes() = isNotEmpty() && all { it != 0 && it.isPrime() }
