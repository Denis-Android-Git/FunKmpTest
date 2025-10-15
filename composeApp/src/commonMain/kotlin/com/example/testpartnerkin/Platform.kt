package com.example.testpartnerkin

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform