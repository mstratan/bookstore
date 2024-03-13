package com.learning.model

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

class Book(id: ObjectId?, title: String, author: String, price: Float) {

    @BsonId
    var id: ObjectId?
    var title: String
    var author: String
    var price: Float

    constructor() : this(null, "not_set", "not_set", 0f)

    init {
        this.id = id
        this.title = title
        this.author = author
        this.price = price
    }
}

data class ShoppingCart(var id: String, var userId: String, var items: ArrayList<ShoppingItem>)
data class ShoppingItem(var bookid: String, var qty: Int)
data class User(var id: String, var name: String, var username: String, var password: String)