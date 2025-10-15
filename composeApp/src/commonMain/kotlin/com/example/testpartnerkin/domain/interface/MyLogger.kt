package com.example.testpartnerkin.domain.`interface`

interface MyLogger {
    fun debug(message: String)
    fun error(message: String, throwable: Throwable? = null)
    fun warning(message: String)
    fun info(message: String)
}