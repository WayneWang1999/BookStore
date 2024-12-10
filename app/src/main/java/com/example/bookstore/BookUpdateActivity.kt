package com.example.bookstore

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.bookstore.databinding.ActivityBookUpdateBinding
import com.example.bookstore.viewmodels.BookUpdateViewModel

class BookUpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookUpdateBinding
    private val bookUpdateViewModel: BookUpdateViewModel by viewModels() // ViewModel instance

    private var bookId: Long? = null // Track if this is an update or add operation

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityBookUpdateBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Retrieve the bookId from the intent
        bookId = intent.getLongExtra("bookId", -1L).takeIf { it != -1L }

        // Observe LiveData from the ViewModel
        bookUpdateViewModel.bookLiveData.observe(this, { book ->
            book?.let {
                // Populate fields with the book's data
                binding.etTitle.setText(book.title)
                binding.etPrice.setText(String.format("%.2f", book.price))
                binding.etQuantity.setText(book.quantity.toString())
                binding.etAuthor.setText(book.author)
                if (!book.imageUrl.isNullOrEmpty()) {
                    Glide.with(this@BookUpdateActivity)
                        .load(book.imageUrl)
                        .into(binding.ivBook)
                }
                binding.etDescription.setText(book.description)
            }
        })

        // Observe operation status (add/update)
        bookUpdateViewModel.operationStatus.observe(this, { status ->
            Toast.makeText(this, status, Toast.LENGTH_SHORT).show()
            if (status.contains("successfully")) {
                finish() // Return to the previous screen after success
            }
        })

        // If bookId exists, load the book details for editing
        bookId?.let { bookUpdateViewModel.loadBookDetails(it) }

        // Set up the button click listener for submitting the form
        binding.btnSubmit.setOnClickListener {
            val title = binding.etTitle.text.toString().trim()
            val price = binding.etPrice.text.toString().trim()
            val quantity = binding.etQuantity.text.toString().trim()
            val author = binding.etAuthor.text.toString().trim()
            val description = binding.etDescription.text.toString().trim()

            if (bookId != null) {
                bookUpdateViewModel.updateBook(bookId!!, title, price, quantity, author, description)
            } else {
                bookUpdateViewModel.addBook(title, price, quantity, author, description)
            }
        }
    }
}
