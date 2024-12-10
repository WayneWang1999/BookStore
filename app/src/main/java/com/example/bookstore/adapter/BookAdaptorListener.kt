package com.example.bookstore.adapter

import com.example.bookstore.data.Book

interface BookAdaptorListener {
    fun onUpdateBook(book: Book)
    fun onDeleteBook(book: Book)
}