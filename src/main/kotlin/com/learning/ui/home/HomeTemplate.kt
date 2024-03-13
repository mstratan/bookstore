package com.learning.ui.home

import com.learning.GeneralViewTemplate
import com.learning.ui.Endpoints
import com.learning.ui.login.Session
import io.ktor.server.html.*
import kotlinx.html.*


class HomeTemplate(val session: Session?) : Template<HTML> {

    val basicTemplate: GeneralViewTemplate = GeneralViewTemplate(session)
    override fun HTML.apply() {
        insert(basicTemplate) {
            menu {
                menuitems {
                    a(classes = "nav-link", href = Endpoints.HOME.url) {
                        +"Home"
                    }
                }
                menuitems {
                    a(classes = "nav-link", href = Endpoints.LOGIN.url) {
                        +"Login"
                    }
                }
                menuitems {
                    a(classes = "nav-link", href = Endpoints.LOGOUT.url) {
                        +"Logout"
                    }
                }
            }
            content {
                div(classes = "mt-2") {
                    h2 {
                        +"Welcome to the Bookstore"
                    }
                    p {
                        +"- We have many good deals on a lot of different topics"
                    }
                    p {
                        +"Let us know if you are looking for something that we do not currently have."
                    }
                }
            }
        }
    }
}