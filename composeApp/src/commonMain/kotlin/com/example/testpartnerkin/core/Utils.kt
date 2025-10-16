package com.example.testpartnerkin.core

import kotlinx.datetime.LocalDate


fun convertDate(date: String): String {
    val month = date.substring(5, 7)
    val monthName = when (month) {
        "01" -> "января"
        "02" -> "февраля"
        "03" -> "марта"
        "04" -> "апреля"
        "05" -> "мая"
        "06" -> "июня"
        "07" -> "июля"
        "08" -> "августа"
        "09" -> "сентября"
        "10" -> "октября"
        "11" -> "ноября"
        "12" -> "декабря"
        else -> ""
    }
    val day = date.substring(8, 10)
    val year = date.substring(0, 4)
    return "$day $monthName $year"
}

fun calculateDaysBetween(startDate: String, endDate: String, inclusive: Boolean = true): Int {
    val start = LocalDate.parse(startDate)
    val end = LocalDate.parse(endDate)
    val days = end.toEpochDays() - start.toEpochDays()
    return if (inclusive) days.toInt() + 1 else days.toInt()
}


fun createDate(date: String): String {
    val list = date.split("-")
    val year = list[0]
    val month = fromDigitToMonth(date)
    return "$month, $year"
}

fun takeDay(date: String): String {
    return date.substring(8, 10)
}

fun fromDigitToEngMonth(date: String): String {
    val month = date.substring(5, 7)
    return when (month) {
        "01" -> "JAN"
        "02" -> "FEB"
        "03" -> "MAR"
        "04" -> "APR"
        "05" -> "MAY"
        "06" -> "JUN"
        "07" -> "JUL"
        "08" -> "AUG"
        "09" -> "SEP"
        "10" -> "OCT"
        "11" -> "NOV"
        "12" -> "DEC"
        else -> ""
    }
}

fun fromDigitToMonth(date: String): String {
    val month = date.substring(5, 7)
    return when (month) {
        "01" -> "Январь"
        "02" -> "Февраль"
        "03" -> "Март"
        "04" -> "Апрель"
        "05" -> "Май"
        "06" -> "Июнь"
        "07" -> "Июль"
        "08" -> "Август"
        "09" -> "Сентябрь"
        "10" -> "Октябрь"
        "11" -> "Ноябрь"
        "12" -> "Декабоь"
        else -> ""
    }
}