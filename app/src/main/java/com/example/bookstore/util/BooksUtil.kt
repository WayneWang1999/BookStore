package com.example.bookstore.util

import com.example.bookstore.data.Book
import com.example.bookstore.data.BookDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.random.Random

class BooksUtil {

    suspend fun insertRandomBooks(bookDao: BookDao) {
        CoroutineScope(Dispatchers.IO).launch {
            val randomBooks = List(100) { generateRandomBook() }
            bookDao.insertBooks(randomBooks)
        }
    }

    fun generateRandomBook(): Book {
        val imageUrls = listOf(
            "https://images.unsplash.com/photo-1535398089889-dd807df1dfaa?q=80&w=1964&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            "https://images.unsplash.com/photo-1521123845560-14093637aa7d?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            "https://images.unsplash.com/photo-1590419317889-431a81812635?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            "https://images.unsplash.com/photo-1543278418-7028dd55b221?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            "https://images.unsplash.com/photo-1499332251574-a76a01d733fc?q=80&w=2033&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            "https://images.unsplash.com/photo-1620482060657-38b4bb0ab9c8?q=80&w=1984&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            "https://images.unsplash.com/photo-1576688790064-9cc041feb836?q=80&w=1935&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        )
        val titles = listOf(
            "The Art of Programming",
            "Clean Code",
            "Effective Java",
            "Design Patterns",
            "Kotlin in Action",
            "Android Development",
            "Game Development",
            "Database Management",
            "Artificial Intelligence",
            "Machine Learning Essentials"
        )
        val authors= listOf(
            "George",
            "Henreque",
            "Bob",
            "Colin",
            "Wayne",
            "Tom",



        )

        val despcrition="Many corporations have slick, flashy mission statements that ultimately do " +
                "little to motivate employees and less to impress customers, investors, and partners.\n" +
                "Vivid Vision is a revolutionary tool that will help owners, CEOs, " +
                "and senior managers create inspirational, detailed, and actionable three-year mission" +
                " statements for their companies. In this easy-to-follow guide, Herold walks organization" +
                " leaders through the simple steps to creating their own Vivid Vision, from brainstorming to " +
                "sharing the ideas to using the document to drive progress in the years to come."
        val author=authors.random()
        val randomImageUrl = imageUrls.random()
        val randomTitle = titles.random() // Randomly pick a title
        val randomPrice = Random.nextDouble(5.0, 50.0) // Random price between $5 and $50
        val randomQuantity = Random.nextInt(1, 20) // Random quantity between 1 and 20
        return Book(
            author=author,
            title = randomTitle,
            price = randomPrice,
            quantity = randomQuantity,
            imageUrl = randomImageUrl,
            description=despcrition
        )
    }


}