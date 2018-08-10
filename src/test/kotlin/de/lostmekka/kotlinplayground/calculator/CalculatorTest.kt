package de.lostmekka.kotlinplayground.calculator

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.assertEquals

object CalculatorTest : Spek({
    describe("a calculator") {

        on("instantiation") {
            it("should not throw up") {
                Calculator()
            }
        }

        val calculator = Calculator()

        context("simple number values") {

            for(number in listOf(0.0, -5.0, 666.0, 38741584.42, -87657265.666)) {
                val numberAsString = number.toString()

                on("putting in '$numberAsString'") {
                    val answer = calculator.evaluate(numberAsString)

                    it("should output the number $number") {
                        assertEquals(number, answer)
                    }
                }
            }
        }
    }
})


