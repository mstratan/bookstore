package com.learning

import com.learning.ui.Endpoints
import com.learning.ui.books.BookTemplate
import com.learning.ui.login.Session
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.html.*
import io.ktor.server.locations.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import kotlinx.css.i
import kotlinx.html.i
import org.slf4j.LoggerFactory

@Location("/book/list")
data class BookListLocation(val sortBy: String, val asc: Boolean)

fun Route.books() {
    val dataManager = DataManagerMongoDB

    post(Endpoints.DOBOOKSEARCH.url) {
        val log = LoggerFactory.getLogger("LoginView")
        val multipart = call.receiveMultipart()
        var search = ""
        while (true) {
            val part = multipart.readPart() ?: break
            when (part) {
                is PartData.FormItem -> {
                    log.info("FormItem: ${part.name} = ${part.value}")
                    if (part.name == "search")
                        search = part.value
                }
                else -> {}
            }
            part . dispose ()
        }
        val searchBooks = DataManagerMongoDB.searchBooks(search)
        call.respondHtmlTemplate(BookTemplate(call.sessions.get<Session>(), searchBooks)) {
            searchfilter {
                i {
                    +"Search filter: $search"
                }
            }
        }
    }
    get(Endpoints.BOOKS.url) {
        call.respondHtmlTemplate(BookTemplate(call.sessions.get<Session>(), DataManagerMongoDB.allBooks())) {}
    }

    authenticate("bookStoreAuth") {
        get<BookListLocation>() {
            call.respond(dataManager.sortedBooks(it.sortBy, it.asc))
        }
    }

    route("/book") {
        get {
            call.respond(dataManager.allBooks())
        }
        post("/{id}") {
            val id = call.parameters.get("id")
            val book = call.receive(Book::class)
            val updatedBook = dataManager.updateBook(book)
            call.respond(updatedBook)
        }
        put {
            val book = call.receive(Book::class)
            val newBook = dataManager.newBook(book)
            call.respond(newBook)
        }
        delete("/{id}") {
            val id = call.parameters.get("id").toString()
            val deletedBook = dataManager.deleteBook(id)
            call.respond(deletedBook)
        }
    }
}