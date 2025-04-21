package ru.netology

fun main() {
    println(calculateCommision("Visa", 50_000, 34_000))
    println(calculateCommision("Мир", 80_000, 45_000))
    println(calculateCommision("MasterCard", 550_000, 120_000))
    println(calculateCommision("NoNameCard", 20_000, 35_000))
}

fun calculateCommision(
    cardtype:String,
    previousTransfer: Int,
    transferAmount: Int
): Int? {

    val dayLimit = 150_000
    val monthLimit = 600_000
    val freeLimitOnMonth = 75_000
    val minVisaCommision = 35

    if (transferAmount > dayLimit) {
        println("Превышен суточный лимит перевода")
        return null
    }

    if (previousTransfer + transferAmount > monthLimit) {
        println("Превышен месячный лимит перевода")
        return null
    }

    return when (cardtype) {
        "MasterCard" -> {

            val amountLimitPlus = (previousTransfer + transferAmount) - freeLimitOnMonth

            if (amountLimitPlus <= 0) {
                return 0
            } else {
                val commision = (amountLimitPlus*0.006).toInt() + 20
                commision
            }
        }

        "Visa" -> {
            val commision = (transferAmount * 0.0075).toInt()
            if (commision < minVisaCommision) {
                return minVisaCommision
            } else {
                commision
            }
        }

        "Мир" -> {
            return 0
        }

        else -> {
            println("Неизвестный тип карты")
            return null
        }
    }
}