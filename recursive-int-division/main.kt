fun main() {
    println(divide(12, 3)) // 4
    println(divide(-12, 3)) // -4
    println(divide(12, -3)) // -4
    println(divide(-12, -3)) // 4
}

fun divide(dividend: Int, divisor: Int): Int {
    if (divisor == 0) {
        throw IllegalArgumentException("Cannot divide by zero")
    }

    if (dividend == 0) {
        return 0
    }

    val isNegative = (dividend < 0) xor (divisor < 0)
    return (if (isNegative) -1 else 1) * divideRecursively(Math.abs(dividend), Math.abs(divisor))
}

fun divideRecursively(dividend: Int, divisor: Int): Int {
    return if (dividend < divisor) 0 else 1 + divideRecursively(dividend - divisor, divisor)
}
