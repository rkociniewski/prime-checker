package org.powermilk

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PrimeTest {
    private val mapIntToArray = mapOf(
        0 to intArrayOf(), 1 to intArrayOf(), 2 to intArrayOf(2), 3 to intArrayOf(2, 3), 4 to intArrayOf(2, 3),
        5 to intArrayOf(2, 3, 5)
    )

    @ParameterizedTest(name = "[{index}] Should return {1} for {0}")
    @MethodSource("primesProvider")
    fun `should check if number is prime number`(number: Int, expected: Boolean) =
        assertEquals(expected, number.isPrime())

    @ParameterizedTest(name = "[{index}] Should throw ArithmeticException for {0}")
    @MethodSource("nonPositiveProvider")
    fun `should throw ArithmeticException for non-positive number`(number: Int) =
        assertEquals(
            "The number should be non-negative",
            assertThrows<ArithmeticException> { number.isPrime() }.message
        )

    @ParameterizedTest(name = "[{index}] Should generate primes array for {0}")
    @MethodSource("arrayProvider")
    fun `should generate prime array for max number`(number: Int, expected: IntArray) =
        assertEquals(expected.contentToString(), number.generatePrimes().contentToString())

    @ParameterizedTest(name = "[{index}] Should filter array for {0}")
    @MethodSource("arrayProvider")
    fun `should filter array from non prime numbers`(number: Int, expected: IntArray) =
        assertEquals(expected.contentToString(), (0..number).toList().toIntArray().filterNonPrimes().contentToString())

    @Test
    fun `should filter empty array`() =
        assertEquals(intArrayOf().contentToString(), intArrayOf().filterNonPrimes().contentToString())

    @ParameterizedTest(name = "[{index}] Should return false for check if all numbers in array are primes for {0}")
    @MethodSource("arrayProvider")
    fun `Should return false check if all numbers in array are primes`(number: Int) =
        assertFalse((0..number).toList().toIntArray().areAllPrimes())

    @Test
    fun `should return false if check all numbers in empty array`() =
        assertFalse(intArrayOf().areAllPrimes())

    @ParameterizedTest(name = "[{index}] Should return false for check if all numbers in array are primes for {0}")
    @MethodSource("primeArrayProvider")
    fun `Should return true check if all numbers in array are primes`(array: IntArray) =
        assertTrue(array.areAllPrimes())

    private fun primesProvider() = listOf<Arguments>(
        Arguments.of(1, false), Arguments.of(2, true), Arguments.of(3, true), Arguments.of(4, false),
        Arguments.of(5, true), Arguments.of(1300843, true), Arguments.of(1300844, false)
    )

    private fun nonPositiveProvider() = (-10..0).map { Arguments.of(it) }
    private fun arrayProvider() = mapIntToArray.map { Arguments.of(it.key, it.value) }
    private fun primeArrayProvider() = mapIntToArray.values.filter { it.isNotEmpty() }.map { Arguments.of(it) }
}
