package ru.netology

fun main() {
    println("Тест 1: Visa " + calculateCommission("Visa", 50_000, 34_000))
    println("Тест 2: Visa " + calculateCommission("Visa", 100_000, 200_000)) //превышен дневной лимит
    println()

    println("Тест 1: Мир " + calculateCommission("Мир", 80_000, 45_000))
    println("Тест 2: Мир " + calculateCommission("Мир", 550_000, 55_000)) //превышен месячный лимит
    println()

    println("Тест 1: MasterCard " + calculateCommission("MasterCard", 15_000, 25_000)) // Порог не превышен
    println("Тест 2: MasterCard " + calculateCommission("MasterCard", 74_000, 2_000)) // Превышение происходит при переводе
    println("Тест 3: MasterCard " + calculateCommission("MasterCard", 75_000, 2_000)) // Лимит исчерпан
    println()

    println("Тест 1: NoNameCard " + calculateCommission("NoNameCard", 20_000, 35_000))
}

fun calculateCommission(
    cardtype:String = "Visa",
    previousTransfer: Int = 0,
    transferAmount: Int = 10_000
): Int? {

    val dayLimit = 150_000
    val monthLimit = 600_000
    val freeLimitOnMonth = 75_000
    val minVisaCommission = 35
    val masterCardBase = 20
    val masterCardPercent = 0.006
    val visaPercent = 0.0075

    if (transferAmount > dayLimit) {
        println("Превышен суточный лимит $dayLimit руб.")
        return null
    }

    if (previousTransfer + transferAmount > monthLimit) {
        println("Превышен месячный лимит $monthLimit руб.")
        return null
    }

    return when (cardtype) {
        "MasterCard" -> {
            val total = previousTransfer + transferAmount

//            if (amountLimitPlus <= freeLimitOnMonth) {
//                return 0
//            } else {
//                val commission = (amountLimitPlus * masterCardPercent).toInt() + masterCardBase
//                commission
//            }

            when {
                // Порог не превышен
                total <= freeLimitOnMonth -> 0

                // Превышение происходит при переводе
                previousTransfer < freeLimitOnMonth -> {
                    val freeRemaining = freeLimitOnMonth - previousTransfer
                    val chargedAmount = transferAmount - freeRemaining
                    (chargedAmount * masterCardPercent).toInt() + masterCardBase
                }
                // Лимит исчерпан
                else -> (transferAmount * masterCardPercent).toInt() + masterCardBase
            }
        }

        "Visa" -> {
            val commission = (transferAmount * visaPercent).toInt()
            if (commission <= minVisaCommission) {
                return minVisaCommission
            } else {
                commission
            }
        }

        "Мир" -> {
            return 0
        }

        else -> {
            println("Неизвестный тип карты '$cardtype'")
            return null
        }
    }
}