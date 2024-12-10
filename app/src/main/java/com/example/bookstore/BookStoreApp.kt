package com.example.bookstore

import android.app.Application
import com.example.bookstore.data.BookDatabase
import com.example.bookstore.util.BooksUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookstoreApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val bookDao = BookDatabase.getInstance(this).bookDao()
        val booksUtil = BooksUtil()

        CoroutineScope(Dispatchers.IO).launch {
            if (bookDao.getAllBooks().isEmpty()) {
                booksUtil.insertRandomBooks(bookDao)
            }
        }
    }
}