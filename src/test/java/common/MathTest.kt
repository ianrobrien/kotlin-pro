package common

import common.math.*
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import java.util.concurrent.ThreadLocalRandom
import kotlin.test.assertEquals
import kotlin.test.assertFails
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@RunWith(JUnitPlatform::class)
class MathTest : Spek({
    describe("A collection of mathematical functions") {
        on("calculating the factorial") {
            it("should return the correct value") {
                assertEquals(factorial(0), 1)
                assertEquals(factorial(1), 1)
                assertEquals(factorial(2), 2)
                assertEquals(factorial(3), 6)
                assertEquals(factorial(4), 24)
                assertEquals(factorial(5), 120)
                assertEquals(factorial(6), 720)
                assertEquals(factorial(7), 5040)
                assertEquals(factorial(8), 40320)
                assertEquals(factorial(9), 362880)
                assertEquals(factorial(10), 3628800)
            }
        }
        on("determining the parity of an integer") {
            it("should correctly distinguish odd and even numbers") {
                assertTrue(isOdd(17))
                assertTrue(isOdd(-3952351))
                assertTrue(isEven(323432432))
                assertTrue(isEven(-2352))
                assertTrue(isEven(0))
            }
        }
        on("determining the primality of an integer") {
            it("should correctly distinguish a prime number") {
                assertFalse(isPrime(-30))
                assertFalse(isPrime(0))
                assertFalse(isPrime(1))
                assertTrue(isPrime(2))
                assertTrue(isPrime(3))
                assertTrue(isPrime(5))
                assertTrue(isPrime(7))
                assertTrue(isPrime(2903))
                assertTrue(isPrime(7919))
                assertFalse(isPrime(7918))
            }
        }
        on("getting the nth value of a fibonacci sequenec") {
            it ("should return the correct value") {
                assertEquals(fibonacci(0), 0)
                assertEquals(fibonacci(1), 1)
                assertEquals(fibonacci(2), 1)
                assertEquals(fibonacci(3), 2)
                assertEquals(fibonacci(4), 3)
                assertEquals(fibonacci(5), 5)
                assertEquals(fibonacci(12), 144)
            }
        }
    }
})