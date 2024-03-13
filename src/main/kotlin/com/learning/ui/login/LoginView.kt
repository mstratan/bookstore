package com.learning.ui.login

import com.learning.SecurityHandler
import com.learning.ui.Constants
import com.learning.ui.Endpoints
import com.learning.ui.home.HomeTemplate
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import org.slf4j.LoggerFactory
import java.util.*

data class Session(val username: String)

fun Route.loginView() {
    get(Endpoints.LOGIN.url) {
        call.respondHtmlTemplate(LoginTemplate(call.sessions.get<Session>())) {

        }
    }
    get(Endpoints.HOME.url) {
        call.respondHtmlTemplate(HomeTemplate(call.sessions.get<Session>())) {

        }
    }
    get(Endpoints.LOGOUT.url) {
        call.sessions.clear(Constants.COOKIE_NAME.value)
        call.respondHtmlTemplate(LogoutTemplate(call.sessions.get<Session>())) {

        }
    }

    post(Endpoints.DOLOGIN.url) {
        val log = LoggerFactory.getLogger("LoginView")
        val multipart = call.receiveMultipart()

        call.request.headers.forEach { s, list ->
            log.info("key $s values $list")
        }
        var username: String = ""
        var password: String = ""
        while (true) {
            val part = multipart.readPart() ?:break
            when (part) {
                is PartData.FormItem -> {
                    log.info("FormItem: ${part.name} = ${part.value}")
                    if (part.name == "username") {
                        username = part.value
                    }
                    if (part.name == "password") {
                        password = part.value
                    }
                }
                is PartData.FileItem -> {
                    log.info("FileItem: ${part.name} -> ${part.originalFileName} of ${part.contentType}")
                }
                else -> {
                }
            }
            part.dispose()
        }
        if(SecurityHandler().isValid(username, password)) {
            call.sessions.set(Constants.COOKIE_NAME.value, Session(username))
            call.respondHtmlTemplate(
                LoginSuccesfulTemplate(call.sessions.get<Session>())
            ) {
                greeting {
                    +"You are logged in as $username and a cookie has been created"
                }
            }
        } else {
            call.respondHtmlTemplate(LoginTemplate(call.sessions.get<Session>())) {
                greeting {
                    +"Username or password was invalid... Try again."
                }
            }
        }
    }
}