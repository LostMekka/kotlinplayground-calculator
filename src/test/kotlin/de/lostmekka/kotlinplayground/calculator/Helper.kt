package de.lostmekka.kotlinplayground.calculator

import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.reflect.KClass
import kotlin.reflect.jvm.jvmName
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal fun SpecBody.testCalculator(
    calculator: ICalculator = Calculator(),
    init: CalculatorTestBody.() -> Unit
) {
    CalculatorTestBody(this, calculator, init)
}

internal fun SpecBody.testCalculator(
    contextDescription: String,
    calculator: ICalculator = Calculator(),
    init: CalculatorTestBody.() -> Unit
) {
    context(contextDescription) { testCalculator(calculator, init) }
}

internal class CalculatorTestBody(
    private val specBody: SpecBody,
    private val calculator: ICalculator,
    init: CalculatorTestBody.() -> Unit
) {
    private val expectedFailures = mutableListOf<ExpectedFailure>()

    init {
        init()
        expectedFailures.forEach { it.check() }
    }

    internal infix fun String.shouldBe(expectedResult: Number) {
        val input = this
        val expected = expectedResult.toDouble()

        specBody.on("putting in '$input'") {
            val answer = calculator.evaluate(input)
            it("should output the number $expectedResult") {
                assertEquals(expected, answer)
            }
        }
    }

    internal infix fun <T : Exception> String.shouldFailWith(exceptionClass: KClass<T>) =
        ExpectedFailure(this, exceptionClass)
            .also { expectedFailures += it }

    internal infix fun ExpectedFailure.because(reason: String) {
        this.reason = reason
    }

    private fun ExpectedFailure.check() {
        specBody.on("putting in '$input'") {
            val exception = try {
                calculator.evaluate(input)
                null
            } catch (e: Exception) {
                e
            }

            val suffix = if (reason == null) "" else " because $reason"
            it("should fail$suffix") {
                assertTrue(exception != null, "there was no exception, but expected one")
            }

            val expectedName = exceptionClass.simpleName ?: exceptionClass.jvmName
            val actualName = exception?.javaClass?.simpleName ?: "[no class name available]"
            it("should throw an instance of $expectedName") {
                assertTrue(
                    exceptionClass.isInstance(exception),
                    "exception is of wrong type. expected $expectedName but got $actualName"
                )
            }
        }
    }
}

internal data class ExpectedFailure(
    val input: String,
    val exceptionClass: KClass<*>,
    var reason: String? = null
)
