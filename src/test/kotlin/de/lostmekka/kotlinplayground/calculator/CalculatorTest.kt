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

        testCalculator {
            "" shouldFailWith ParseException::class because "the input is empty"
        }

        testCalculator("simple number values") {
            listOf(0.0, -0.0, -5.0, 666.0, 38741584.42, -87657265.666)
                .forEach { it.toString() shouldBe it }

            ".666" shouldBe 0.666

            "0..1" shouldFailWith ParseException::class because "there are two decimal points"
            "0.1." shouldFailWith ParseException::class because "there are two decimal points"
            ".0.1" shouldFailWith ParseException::class because "there are two decimal points"
        }

        testCalculator("ignoring whitespaces") {
            repeat(3) { prefixSize ->
                val prefix = " ".repeat(prefixSize)
                repeat(3) { suffixSize ->
                    val suffix = " ".repeat(suffixSize)
                    "${prefix}666$suffix" shouldBe 666
                }
            }
        }

        testCalculator("addition and subtraction only") {
            "1+2" shouldBe 3
            "1-2" shouldBe -1
            "1+1+1" shouldBe 3
            "1+2-4+7" shouldBe 6
            "-4+1+2+7" shouldBe 6

            "+1+2-4+7" shouldFailWith ParseException::class because "there is a leading + operator"
            "-4+1+2+7+" shouldFailWith ParseException::class because "there is a trailing + operator"
            "-4+1+2+7-" shouldFailWith ParseException::class because "there is a trailing - operator"
        }

        testCalculator("weird negations") {
            repeat(10) {
                val n = it + 1
                val minusSigns = "-".repeat(n)
                val totalSign = Math.pow(-1.0, n.toDouble())
                "${minusSigns}1" shouldBe totalSign
                "0${minusSigns}1" shouldBe totalSign
            }
        }

        testCalculator("multiplication and division only") {
            "4*4" shouldBe 16
            "2*3.5" shouldBe 7
            "9/2" shouldBe 4.5
            "2*2/3*3/4" shouldBe 1
            "-2*2/3*3/4" shouldBe -1
            "2*2/3*-3/4" shouldBe -1
            "2*2/3*3/-4" shouldBe -1

            "*7*3*2" shouldFailWith ParseException::class because "there is a leading * operator"
            "/7*3*2" shouldFailWith ParseException::class because "there is a leading / operator"
            "7*3*2*" shouldFailWith ParseException::class because "there is a trailing * operator"
            "7*3*2/" shouldFailWith ParseException::class because "there is a trailing / operator"

            "666/0" shouldFailWith EvaluateException::class because "it is a division by zero"
        }

        testCalculator("all 4 basic operations") {
            "1*5+5" shouldBe 10
            "5/1-5" shouldBe 0
            "20/4*3 + 4*4 - 4/-2 - 1" shouldBe 32
            "-2.5*4 - 2 + 100/2/5 + 44" shouldBe 42
        }

        testCalculator("grouping with parentheses") {
            var grouped42 = "42"
            repeat(10) {
                grouped42 shouldBe 42
                grouped42 = "($grouped42)"
            }

            " 1 + 2 + 3 " shouldBe 6
            "(1)+ 2 + 3 " shouldBe 6
            " 1 +(2)+ 3 " shouldBe 6
            "(1)+(2)+ 3 " shouldBe 6
            " 1 + 2 +(3)" shouldBe 6
            "(1)+ 2 +(3)" shouldBe 6
            " 1 +(2)+(3)" shouldBe 6
            "(1)+(2)+(3)" shouldBe 6

            "(1 + 2)+ 3 " shouldBe 6
            "(1 + 2)+(3)" shouldBe 6
            " 1 +(2 + 3)" shouldBe 6
            "(1)+(2 + 3)" shouldBe 6

            "(1 + 2 + 3)" shouldBe 6

            " 1 + 2 * 3 " shouldBe 7
            " 1 +(2 * 3)" shouldBe 7
            "(1 + 2)* 3 " shouldBe 9
            "  1 -(2 * 3)" shouldBe -5
            "-(1 + 2)* 3 " shouldBe -9

            "42 / (-4+2-1+3)" shouldFailWith EvaluateException::class because "it is a division by zero"

            "()" shouldFailWith ParseException::class because "empty parenthesis are not allowed"

            " 1 + 3)" shouldFailWith ParseException::class because "there is a opening parenthesis missing"
            ")1 + 3 " shouldFailWith ParseException::class because "there is a opening parenthesis missing"
            "(1 + 3 " shouldFailWith ParseException::class because "there is a closing parenthesis missing"
            " 1 + 3(" shouldFailWith ParseException::class because "there is a closing parenthesis missing"

            " 1)+(3 " shouldFailWith ParseException::class because "the parenthesis do not connect"
        }
    }
})
