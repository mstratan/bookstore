package com.learning.plugins

import com.learning.ui.Constants
import com.learning.ui.login.Session
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*

fun Application.configureSecurity() {

    install(Sessions) {
        cookie<Session>(Constants.COOKIE_NAME.value)
    }

    val users = listOf("shopper1", "shopper2", "shopper3")
    val admins = listOf("admin")

    authentication {
        basic(name = "myauth1") {
            realm = "Ktor Server"
            validate { credentials ->
                if (credentials.name == credentials.password) {
                    UserIdPrincipal(credentials.name)
                } else {
                    null
                }
            }
        }

        form(name = "myauth2") {
            userParamName = "user"
            passwordParamName = "password"
            challenge {
                /**/
            }
        }

        basic(name = "bookStoreAuth") {
            realm = "Book store"
            validate {
                if ((users.contains(it.name) || admins.contains(it.name)) && it.password == "password")
                    UserIdPrincipal(it.name)
                else null
            }
        }
    }
    routing {
        authenticate("myauth1") {
            get("/protected/route/basic") {
                val principal = call.principal<UserIdPrincipal>()!!
                call.respondText("Hello ${principal.name}")
            }
        }
        authenticate("myauth2") {
            get("/protected/route/form") {
                val principal = call.principal<UserIdPrincipal>()!!
                call.respondText("Hello ${principal.name}")
            }
        }
    }
}
