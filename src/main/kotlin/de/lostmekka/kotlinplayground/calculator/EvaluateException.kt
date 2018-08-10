package de.lostmekka.kotlinplayground.calculator

class EvaluateException(
    message: String = "no message given",
    cause: Exception? = null
) : Exception(message, cause)
