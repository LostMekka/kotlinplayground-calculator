package de.lostmekka.kotlinplayground.calculator

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.*

object CalculatorTest : Spek({
    describe("a calculator") {
        on("instantiation") {
            it("should not throw up") {
                Calculator()
            }
        }

        testCalculator("simple number values") {
            listOf(0.0, -5.0, 666.0, 38741584.42, -87657265.666)
                .forEach { it.toString() shouldBe it }
        }

        testCalculator("addition and subtraction only") {
            "1+2" shouldBe 3
            "1-2" shouldBe -1
            "1+1+1" shouldBe 3
            "1+2-4+7" shouldBe 6
            "+1+2-4+7" shouldBe 6
            "-4+1+2+7" shouldBe 6
        }

        testCalculator("multiplication and division only") {
            "4*4" shouldBe 16
            "2*3.5" shouldBe 7
            "9/2" shouldBe 4.5
            "2*2/3*3/4" shouldBe 1
        }

        testCalculator("all 4 basic operations") {
            "-2.5*4 - 2 + 100/2/5 + 44" shouldBe 42
            // TODO: don't be lazy and add more test cases
        }
    }
})
