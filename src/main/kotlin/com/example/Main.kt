package com.example

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import kotlinx.coroutines.*
import java.time.LocalTime
import java.time.format.DateTimeFormatter

suspend fun main() {
    val client = HttpClient(CIO)
    val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")

    while (true) {
        val time = LocalTime.now().format(formatter)
        val message = "$time - Hola"

        println("Envío a pong: $message")
        client.post<String>("http://localhost:9292/send") {
            body = message
        }

        val response = client.get<String>("http://localhost:9292/receive")
        println("Rebut de pong: $response")

        delay(1000)
    }
}