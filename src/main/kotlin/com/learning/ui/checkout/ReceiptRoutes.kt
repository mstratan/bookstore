package com.learning.ui.checkout

import com.learning.model.DataManagerMongoDB
import com.learning.ui.Endpoints
import com.learning.ui.login.Session
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*

fun Route.receipt(){
    get(Endpoints.RECEIPT.url){
        val session = call.sessions.get<Session>()
        call.respondHtmlTemplate(ReceiptTemplate(session, DataManagerMongoDB.cartForUser(session))){
        }
    }
}