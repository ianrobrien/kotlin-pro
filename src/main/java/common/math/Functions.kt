package common.math

fun factorial(n: Int): Int {
    return when (n) {
        0, 1 -> 1
        else -> n * factorial(n - 1)
    }
}

fun isOdd(n: Int): Boolean {
    return !isEven(n)
}

fun isEven(n: Int): Boolean {
    return n % 2 == 0
}

fun isPrime(n: Int): Boolean {
    return when (n) {
        in Integer.MIN_VALUE..1 -> false
        2, 3 -> true
        else -> {
            if (n % 2 == 0 || n % 3 == 0) return false
            else {
                var i = 5
                while (i * i <= n) {
                    if (n % i == 0 || n % (i + 2) == 0) {
                        return false
                    }
                    i += 6
                }
                return true
            }
        }
    }
}