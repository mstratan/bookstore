package com.learning

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.books() {
    val dataManager = DataManager
    route("/book") {
        get {
            call.respond(dataManager.books)
        }
        post("/{id}") {
            val id = call.parameters.get("id")
            val book = call.receive(Book::class)
            val updatedBook = dataManager.updateBook(book)
            call.respond(updatedBook)
        }
        put {
            val book = call.receive(Book::class)
            val newBook = DataManager.newBook(book)
            call.respond(newBook)
        }
        delete("/{id}") {
            val id = call.parameters.get("id").toString()
            val deletedBook = DataManager.deleteBook(id)
            call.respond(deletedBook)
        }
    }
}