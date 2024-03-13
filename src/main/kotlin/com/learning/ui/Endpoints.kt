package com.learning.ui

enum class Endpoints(val url: String) {
    LOGIN("/html/login"),
    LOGOUT("/html/logout"),
    DOLOGIN("/html/dologin"),
    HOME("/html/home"),
    BOOKS("/html/books"),
    CART("/html/cart"),
    DOBOOKSEARCH("/html/books/search"),
    DOADDTOCART("/html/cart/add"),
    DOREMOVEFROMCART("/html/cart/remove"),
    CHECKOUT("/html/checkout")
}