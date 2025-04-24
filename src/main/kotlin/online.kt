package ru.netology

fun main() {
    agoToText(30)      // был(а) только что
    agoToText(120)     // был(а) 2 минуты назад
    agoToText(3600)    // был(а) 1 час назад
    agoToText(7200)    // был(а) 2 часа назад
    agoToText(86400)   // был(а) вчера
    agoToText(172800)  // был(а) позавчера
    agoToText(259200)  // был(а) давно
    agoToText(43200)  // был(а) 12 часов назад
    agoToText(720)  // был(а) 12 минут назад
}

fun agoToText(time: Int) {
    val text = when (time) {
        in 0..60 -> "Был(а) только что"
        in 61..(60*59) -> "Был(а) ${getMinutes(time/60)} назад"
        in (60*60)..(24*60*59) -> "Был(а) ${getHours(time/(60*60))} назад"
        in (24*60*60)..(2*24*60*59) -> "Был(а) вчера"
        in (2*24*60*60)..(3*24*60*59) -> "Был(а) позавчера"
        else -> "Был(а) давно"
    }
    println(text)
}

fun getMinutes(minutes:Int): String {
    return when {
        minutes % 100 in 11..14 -> "$minutes минут"

        minutes % 10 == 1 -> "$minutes минуту"
        minutes % 10 in 2..4 -> "$minutes минуты"
        else -> "$minutes минут"
    }

}

fun getHours(hours: Int): String {
    return when {
        hours % 100 in 11..14 -> "$hours часов"

        hours % 10 == 1 -> "$hours час"
        hours % 10 in 2..4 -> "$hours часа"
        else -> "$hours часов"
    }
}