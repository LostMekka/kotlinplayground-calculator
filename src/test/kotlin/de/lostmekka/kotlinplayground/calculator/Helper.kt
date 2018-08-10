package de.lostmekka.kotlinplayground.calculator

import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.assertEquals

internal fun SpecBody.testCalculator(
    calculator: ICalculator = Calculator(),
    init: CalculatorTestBody.() -> Unit
) {
    CalculatorTestBody(this, calculator).init()
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
    private val calculator: ICalculator
) {
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
}
