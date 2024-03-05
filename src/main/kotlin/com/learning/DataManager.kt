package com.learning

import org.slf4j.LoggerFactory
import kotlin.reflect.full.declaredMemberProperties

object DataManager {

    val log = LoggerFactory.getLogger(DataManager::class.java)

    val books = ArrayList<Book>()
    fun gimmeId(): String {
        return books.size.toString()
    }

    init {
        newBook(Book(gimmeId(), "How to grow apples", "Mr. Appleton", 100f))
        newBook(Book(gimmeId(), "How to grow oranges", "Mr. Orangeton", 90f))
        newBook(Book(gimmeId(), "How to grow lemons", "Mr. Lemon", 110f))
        newBook(Book(gimmeId(), "How to grow pineapples", "Mr. Pineapple", 100f))
        newBook(Book(gimmeId(), "How to grow pears", "Mr. Pears", 110f))
        newBook(Book(gimmeId(), "How to grow coconuts", "Mr. Coconut", 130f))
        newBook(Book(gimmeId(), "How to grow bananas", "Mr. Appleton", 120f))
    }

    fun newBook(book: Book): Book {
        books.add(book)
        return book
    }

    fun updateBook(book: Book): Book {
        val foundBook = books.find {
            it.id == book.id
        }
        foundBook?.title = book.title
        foundBook?.author = book.author
        foundBook?.price = book.price
        return foundBook!!
    }

    fun deleteBook(book: Book): Book {
        val foundBook = books.find {
            it.id == book.id
        }
        books.remove(foundBook)
        return foundBook!!
    }

    fun deleteBook(bookId: String): Book {
        val foundBook = books.find {
            it.id == bookId
        }
        books.remove(foundBook)
        return foundBook!!
    }

    fun sortedBooks(sortBy: String, asc: Boolean): List<Book> {
        val member = Book::class.declaredMemberProperties.find { it.name.equals(sortBy) }
        if (member == null) {
            log.info("The field to sort by does not exist")
            return books
        }
        if (asc)
            return  books.sortedBy {  member?.get(it).toString() }
        else
            return books.sortedByDescending { member?.get(it).toString()
        }
    }
}