package com.example.bookstore.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.bookstore.data.Book
import com.example.bookstore.data.BookDao
import com.example.bookstore.data.BookDatabase
import kotlinx.coroutines.launch

class BookUpdateViewModel(application: Application) : AndroidViewModel(application) {

    private val bookDao: BookDao = BookDatabase.getInstance(application).bookDao()

    val bookLiveData = MutableLiveData<Book?>() // To store book data for UI update
    val operationStatus = MutableLiveData<String>() // To communicate success or failure

    // Load the book details based on the bookId
    fun loadBookDetails(bookId: Long) {
        viewModelScope.launch {
            val book = bookDao.getBookById(bookId)
            bookLiveData.postValue(book) // Update UI with book details
        }
    }

    // Add a new book
    fun addBook(title: String, price: String, quantity: String, author: String, description: String) {
        viewModelScope.launch {
            if (validateInputs(title, price, quantity, author, description)) {
                val newBook = Book(
                    bookId = 0, // Let Room auto-generate the ID
                    title = title,
                    price = price.toDouble(),
                    quantity = quantity.toInt(),
                    author = author,
                    description = description,
                    imageUrl = "https://cdn.pixabay.com/photo/2016/08/24/16/20/books-1617327_1280.jpg"
                )

                bookDao.insertBook(newBook)
                operationStatus.postValue("Book added successfully")
            } else {
                operationStatus.postValue("Invalid inputs")
            }
        }
    }

    // Update an existing book
    fun updateBook(bookId: Long, title: String, price: String, quantity: String, author: String, description: String) {
        viewModelScope.launch {
            if (validateInputs(title, price, quantity, author, description)) {
                val existingBook = bookDao.getBookById(bookId)

                if (existingBook != null) {
                    val updatedBook = Book(
                        bookId = bookId,
                        title = title,
                        price = price.toDouble(),
                        quantity = quantity.toInt(),
                        author = author,
                        description = description,
                        imageUrl = existingBook.imageUrl // Keep the same image URL
                    )

                    bookDao.insertBook(updatedBook)
                    operationStatus.postValue("Book updated successfully")
                } else {
                    operationStatus.postValue("Book not found")
                }
            } else {
                operationStatus.postValue("Invalid inputs")
            }
        }
    }

    private fun validateInputs(title: String, price: String, quantity: String, author: String, description: String): Boolean {
        return !(title.isEmpty() || price.isEmpty() || quantity.isEmpty() || author.isEmpty() || description.isEmpty()) &&
                price.toDoubleOrNull() != null && quantity.toIntOrNull() != null
    }
}
