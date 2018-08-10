package de.lostmekka.kotlinplayground.calculator

class ParseException(
    message: String = "no message given",
    cause: Exception? = null
) : Exception(message, cause)
