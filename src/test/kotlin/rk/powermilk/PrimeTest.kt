package rk.powermilk

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
        0 to intArrayOf(),
        1 to intArrayOf(),
        2 to intArrayOf(2),
        3 to intArrayOf(2, 3),
        4 to intArrayOf(2, 3),
        5 to intArrayOf(2, 3, 5)
    )

    @ParameterizedTest
    @MethodSource("validPrimeProvider")
    fun `should check if number is prime number`(number: Int, expected: Boolean) {
        assertEquals(expected, number.isPrime())
    }

    @ParameterizedTest
    @MethodSource("nonPositiveProvider")
    fun `should throw ArithmeticException for non-positive number`(number: Int) {
        val exception = assertThrows<ArithmeticException> { number.isPrime() }
        assertEquals("The number should be non-negative", exception.message)
    }

    @ParameterizedTest(name = "[{index}] Should generate primes array for {0}")
    @MethodSource("arrayProvider")
    fun `should generate prime array for max number`(number: Int, expected: IntArray) =
        assertEquals(expected.contentToString(), generatePrimes(number).contentToString())

    @ParameterizedTest(name = "[{index}] Should filter array for {0}")
    @MethodSource("arrayProvider")
    fun `should filter array from non prime numbers`(number: Int, expected: IntArray) {
        val input = (0..number).toList().toIntArray()
        assertEquals(expected.contentToString(), input.filterNonPrimes().contentToString())
    }

    @Test
    fun `should filter empty array`() =
        assertEquals(intArrayOf().contentToString(), intArrayOf().filterNonPrimes().contentToString())

    @Test
    fun `Should return empty array if all elements are non primes`() {
        val array = intArrayOf(1, 4, 6, 8, 9, 10)
        assertTrue(array.filterNonPrimes().isEmpty())
    }

    @ParameterizedTest(name = "[{index}] Should return false for check if all numbers in array are primes for {0}")
    @MethodSource("arrayProvider")
    fun `Should return false check if all numbers in array are primes`(number: Int) {
        val array = (0..number).toList().toIntArray()
        assertFalse(array.areAllPrimes())
    }

    @Test
    fun `should return false if check all numbers in empty array`() =
        assertFalse(intArrayOf().areAllPrimes())

    @ParameterizedTest(name = "[{index}] Should return false for check if all numbers in array are primes for {0}")
    @MethodSource("primeArrayProvider")
    fun `Should return true check if all numbers in array are primes`(array: IntArray) =
        assertTrue(array.areAllPrimes())

    @Test
    fun `should return false if one element is not prime`() {
        val array = intArrayOf(2, 3, 4, 5)
        assertFalse(array.areAllPrimes())
    }

    @ParameterizedTest
    @MethodSource("generateEmptyPrimesProvider")
    fun `should return empty array for numbers less than 2`(number: Int) {
        assertTrue(generatePrimes(number).isEmpty())
    }

    // --- Data Providers ---

    private fun validPrimeProvider() = listOf(
        Arguments.of(1, false),
        Arguments.of(2, true),
        Arguments.of(3, true),
        Arguments.of(4, false),
        Arguments.of(5, true),
        Arguments.of(1300843, true),
        Arguments.of(1300844, false)
    )

    private fun nonPositiveProvider() = (-10..0).map { Arguments.of(it) }

    private fun arrayProvider() = mapIntToArray.map { Arguments.of(it.key, it.value) }

    private fun primeArrayProvider() = mapIntToArray.values
        .filter { it.isNotEmpty() }
        .map { Arguments.of(it) }

    private fun generateEmptyPrimesProvider() = listOf(
        Arguments.of(0),
        Arguments.of(1),
        Arguments.of(-5)
    )
}
