class MathOperations {

    /* Function returns LARGEST common multiplier */
    fun findLcm(a: Int, b: Int): Int {/* First we determine min and max values */
        val max: Int = kotlin.math.max(a, b)
        val min: Int = kotlin.math.min(a, b)

        /*
         * Starting from 1 to min value we multiply max number until
         * The result is divisible by min
         */
        var candidateLCM: Int = 0
        for (i in 1..min) {
            candidateLCM = i * max
            if (candidateLCM % min == 0) break
        }

        return candidateLCM
    }

    /* Function returns the GREATEST common divisor */
    fun findGcd(a: Int, b: Int): Int {/* Base case */
        if (b == 0) return a

        /* b is replaced with a % b and eventually is divided without reminder which means a is Gcd*/
        return findGcd(b, a % b)
    }

    fun hasDollarSign(s: String) = '$' in s

    /* This function calls helper function, passes next biggest even number after 100
     * Returns sum of all even numbers below 100
     */
    fun sumOfEvenSub100(): Int = recursiveSumOfEvenSub100(100 - 2)

    private fun recursiveSumOfEvenSub100(n: Int): Int {
        return if (n <= 2) {
            n
        } else {
            n + recursiveSumOfEvenSub100(n - 2)
        }
    }

    fun reverseNum(num: Int, reversed: Int = 0): Int {
        return if (num == 0) {
            reversed
        } else {
            val reminder = num % 10
            return reverseNum(num / 10, reversed * 10 + reminder)
        }
    }

    /* Consecutively shortens passed string from both sides and checks if chars are the same */
    fun isPalindrome(text: String): Boolean {
        return text.length < 2 || (text.first() == text.last()) && isPalindrome(
            text.substring(
                1, text.length - 1
            )
        )
    }
}


fun String.capitalizeWords(): String {
    var result = ""

    for (i in indices) {
        val ch = get(i)
        result += if (i == 0 || result.last().isWhitespace()) ch.uppercaseChar() else ch
    }

    return result
}

fun main() {
    println("whitespace after word ".capitalizeWords())  // "Whitespace After Word "
    println("to even to upper".capitalizeWords())       //  "To Even To Upper"
    println("two  whitespaces".capitalizeWords())      //   "Two  Whitespaces"
    println("words with 1 num".capitalizeWords())     //    "Words With 1 Num"
    println("".capitalizeWords())                    //     ""
}