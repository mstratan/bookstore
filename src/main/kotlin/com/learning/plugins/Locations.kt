package com.learning.plugins

import io.ktor.server.application.*
import io.ktor.server.locations.*

fun Application.configureLocation() {
    install(Locations)
}
