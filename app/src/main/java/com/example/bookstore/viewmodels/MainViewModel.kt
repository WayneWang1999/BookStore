package com.example.bookstore.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.bookstore.data.Book
import com.example.bookstore.data.BookDao
import com.example.bookstore.data.BookDatabase
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val bookDao: BookDao = BookDatabase.getInstance(application).bookDao()

    // LiveData to hold the list of books
    val books: LiveData<List<Book>> = bookDao.getAllBooksLiveData()

    // Fetch books from the database (already provided by the DAO using LiveData)
    fun refreshBooks() {
        viewModelScope.launch {
            // Trigger a refresh (if you use Room with LiveData, this will update automatically)
        }
    }

    // Function to delete a book
    fun deleteBook(bookId: Long) {
        viewModelScope.launch {
            bookDao.deleteBookById(bookId)
        }
    }
}