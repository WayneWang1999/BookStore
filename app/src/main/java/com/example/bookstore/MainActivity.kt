package com.example.bookstore

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookstore.adapter.BookAdapter
import com.example.bookstore.adapter.BookAdaptorListener
import com.example.bookstore.data.Book
import com.example.bookstore.databinding.ActivityMainBinding
import com.example.bookstore.viewmodels.MainViewModel

class MainActivity : AppCompatActivity(), BookAdaptorListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bookAdapter: BookAdapter
    private val mainViewModel: MainViewModel by viewModels()  // Initialize ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Set up RecyclerView layout manager
        binding.rvBookStore.layoutManager = LinearLayoutManager(this)

        // Initialize the adapter with an empty list initially
        bookAdapter = BookAdapter(emptyList(), this@MainActivity)
        binding.rvBookStore.adapter = bookAdapter

        // Observe the books LiveData from the ViewModel
        mainViewModel.books.observe(this, Observer { books ->
            bookAdapter.updateData(books)  // Update the adapter when the data changes
        })

        // Button for adding a new book
        binding.btnAdd.setOnClickListener {
            val intent = Intent(this, BookUpdateActivity::class.java)
            startActivity(intent)
        }
    }

    // Handle Add Book action
    override fun onUpdateBook(book: Book) {
        val intent = Intent(this, BookUpdateActivity::class.java)
        intent.putExtra("bookId", book.bookId) // Pass book ID for editing
        startActivity(intent)
    }

    // Handle Delete Book action
    override fun onDeleteBook(book: Book) {
        mainViewModel.deleteBook(book.bookId)
    }
}
