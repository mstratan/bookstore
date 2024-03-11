package com.learning

import io.ktor.server.html.*
import kotlinx.html.ButtonType
import kotlinx.html.FlowContent
import kotlinx.html.UL
import kotlinx.html.a
import kotlinx.html.button
import kotlinx.html.div
import kotlinx.html.id
import kotlinx.html.li
import kotlinx.html.nav
import kotlinx.html.span
import kotlinx.html.ul

class NavigationTemplate() : Template<FlowContent> {
    val menuitems = PlaceholderList<UL, FlowContent>()

    override fun FlowContent.apply() {
        div {
            nav(classes = "navbar navbar-expand-md navbar-dark bg-dark ") {
                a(classes = "navbar-brand", href = "#") { +"My Bookstore" }
                button(
                    classes = "navbar-toggler",
                    type = ButtonType.button
                ) {
                    this.attributes.put("data-toggle", "collapse")
                    this.attributes.put("data-target", "#navbarsExampleDefault")
                    this.attributes.put("aria-controls", "navbarsExampleDefault")
                    this.attributes.put("aria-expanded", "false")
                    this.attributes.put("aria-label", "Toggle navigation")
                    span(classes = "navbar-toggler-icon") {}
                }
                div(classes = "collapse navbar-collapse") {
                    this.id = "navbarsExampleDefault"
                    ul(classes = "navbar-nav mr-auto") {
                        each(menuitems) {
                            li {
                                insert(it)
                            }
                        }
                    }
                }
            }
        }
    }
}