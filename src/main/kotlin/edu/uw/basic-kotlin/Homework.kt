package edu.uw.basickotlin

class Library {
    // This is just here as a placeholder, to make sure tests run and pass
    // before you add any code
    fun someLibraryMethod(): Boolean {
        return true
    }
}

// write a "whenFn" that takes an arg of type "Any" and returns a String
fun whenFn(arg: Any): String {
    val res = when (arg) {
        "Hello" -> "world"
        "Howdy", "Bonjour" -> "Say what?"
        0 -> "zero"
        1 -> "one"
        in 2..10 -> "low number"
        is Int -> "a number"
        else -> "I don't understand"
    }

    return res;
}

// write an "add" function that takes two Ints, returns an Int, and adds the values
fun add(first: Int, second: Int): Int {
    return first + second;
}

// write a "sub" function that takes two Ints, returns an Int, and subtracts the values
fun sub(first: Int, second: Int): Int {
    return first - second;
}

// write a "mathOp" function that takes two Ints and a function (that takes two Ints and returns an Int), returns an Int, and applies the passed-in-function to the arguments
fun mathOp(first: Int, second: Int, op: (Int, Int) -> Int): Int {
    return op(first, second);
}

// write a class "Person" with first name, last name and age
class Person(val firstName: String, val lastName: String, val age: Int) {
    val debugString: String = "[Person firstName:$firstName lastName:$lastName age:$age]";
}

// write a class "Money" with amount and currency, and define a convert() method and the "+" operator
class Money(val amount: Int, val currency: String) {
    init {
        require(amount >= 0) { "Amount can never be less than 0." }
        require(currency in setOf("USD", "EUR", "CAN", "GBP")) { "Currency can only be one of USD, EUR, CAN and GBP." }
    }

    public fun convert(newCurrency: String): Money {
        if (currency == newCurrency) return this;

        val conversionRates = mapOf(
            "USD" to mapOf("EUR" to 1.5, "CAN" to 1.25, "GBP" to 0.5),
            "EUR" to mapOf("USD" to 0.67, "CAN" to 0.83, "GBP" to 0.33),
            "CAN" to mapOf("USD" to 0.8, "EUR" to 1.2, "GBP" to 0.4),
            "GBP" to mapOf("USD" to 2.0, "EUR" to 3.0, "CAN" to 2.5)
        )

        val rate: Double = conversionRates[this.currency]?.get(newCurrency) ?: 1.0;
        val newAmount: Int = Math.ceil(this.amount.toDouble() * rate).toInt();

        return Money(newAmount, newCurrency);
    }

    operator fun plus(other: Money): Money {
        val convertedOther: Money = other.convert(this.currency);
        return Money(this.amount + convertedOther.amount, this.currency);
    }
}