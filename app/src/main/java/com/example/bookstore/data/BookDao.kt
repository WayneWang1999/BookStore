package com.example.bookstore.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BookDao {
    @Query("SELECT * FROM books")
    suspend fun getAllBooks(): List<Book>

    @Query("SELECT * FROM books WHERE bookId = :bookId")
    suspend fun getBookById(bookId: Long): Book?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBooks(books: List<Book>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: Book)

    @Query("DELETE FROM books")
    suspend fun deleteBooks()

    // Delete book by ID
    @Query("DELETE FROM books WHERE bookId = :bookId")
    suspend fun deleteBookById(bookId: Long)

    @Query("SELECT * FROM books ORDER BY title ASC")
    fun getAllBooksLiveData(): LiveData<List<Book>>
}