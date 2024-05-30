package com.example.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.concurrent.ConcurrentLinkedQueue

val messageQueue = ConcurrentLinkedQueue<String>()

fun Application.configureRouting() {
    routing {
        post("/send") {
            val message = call.receive<String>()
            messageQueue.add(message)
            call.respondText("Message received", status = HttpStatusCode.OK)
        }

        get("/receive") {
            val response = if (messageQueue.isNotEmpty()) {
                val message = messageQueue.poll()
                "M'han dit: $message i jo responc: My name is pong"
            } else {
                "No messages"
            }
            call.respondText(response, status = HttpStatusCode.OK)
        }
    }
}
