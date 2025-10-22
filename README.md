# Prime Utilities in Kotlin

[![version](https://img.shields.io/badge/version-1.0.12-yellow.svg)](https://semver.org)
[![Awesome Kotlin Badge](https://kotlin.link/awesome-kotlin.svg)](https://github.com/KotlinBy/awesome-kotlin)
[![Build](https://github.com/rkociniewski/prime-checker/actions/workflows/main.yml/badge.svg)](https://github.com/rkociniewski/prime-checker/actions/workflows/main.yml)
[![codecov](https://codecov.io/gh/rkociniewski/prime-checker/branch/main/graph/badge.svg)](https://codecov.io/gh/rkociniewski/prime-checker)
[![Kotlin](https://img.shields.io/badge/Kotlin-2.20-blueviolet?logo=kotlin)](https://kotlinlang.org/)
[![Gradle](https://img.shields.io/badge/Gradle-9.10-blue?logo=gradle)](https://gradle.org/)
[![License: MIT](https://img.shields.io/badge/License-MIT-greem.svg)](https://opensource.org/licenses/MIT)

A small Kotlin utility library for working with prime numbers. Includes efficient and idiomatic functions for:

- Checking if a number is prime
- Generating a list of primes up to a given value
- Filtering prime numbers from an array
- Checking if all elements in an array are prime

---

## 🔧 Features

### `Int.isPrime(): Boolean`

Checks whether an integer is a prime number.

```kotlin
5.isPrime() // true
10.isPrime() // false
````

> ❗ Throws `ArithmeticException` if the number is zero or negative.

---

### `generatePrimes(n: Int): IntArray`

Returns an array of all prime numbers from `2` to `n` (inclusive).

```kotlin
generatePrimes(10) // [2, 3, 5, 7]
```

---

### `IntArray.filterNonPrimes(): IntArray`

Filters all non-prime or invalid values (e.g. 0, 1, negatives) from an array.

```kotlin
intArrayOf(2, 3, 4, 5, 6).filterNonPrimes() // [2, 3, 5]
```

---

### `IntArray.areAllPrimes(): Boolean`

Checks if all values in the array are prime.

```kotlin
intArrayOf(2, 3, 5).areAllPrimes() // true
intArrayOf(2, 4, 5).areAllPrimes() // false
```

---

## ✅ Test Coverage

Tests are written with **JUnit 5** and include:

* Prime detection
* Negative and edge cases
* Array filtering
* Prime generation
* Empty input checks

You can find them in `src/test/kotlin/rk/powermilk/PrimeTest.kt`.

---

## 📦 Project Structure

```
rk.powermilk
├── Prime.kt           // Prime-related utility functions
└── PrimeTest.kt       // Unit tests
```

---

## License

This project is licensed under the MIT License.

## Built With

* [Gradle](https://gradle.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Rafał Kociniewski** - [PowerMilk](https://github.com/rkociniewski)
