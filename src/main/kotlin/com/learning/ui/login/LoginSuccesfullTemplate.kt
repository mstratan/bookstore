package com.learning.ui.login

import com.learning.GeneralViewTemplate
import com.learning.ui.Endpoints
import io.ktor.server.html.*
import kotlinx.html.*

class LoginSuccesfulTemplate(val session: Session?) : Template<HTML> {

    val basicTemplate: GeneralViewTemplate = GeneralViewTemplate(session)
    val greeting = Placeholder<FlowContent>()
    override fun HTML.apply() {
        insert(basicTemplate) {
            menu {
                menuitems {
                    a(classes = "nav-link", href = Endpoints.HOME.url) { +"Home" }
                }
                menuitems {
                    a(classes = "nav-link", href = Endpoints.LOGOUT.url) { +"Logout" }
                }
            }
            content {
                div(classes = "mt-2") {
                    h2() {
                        +"You have been logged in!"
                    }
                    p{
                        insert(greeting)
                    }
                }
            }
        }
    }
}