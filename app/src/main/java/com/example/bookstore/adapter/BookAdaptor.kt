package com.example.bookstore.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bookstore.R
import com.example.bookstore.data.Book
import com.example.bookstore.databinding.BooklistBinding

class BookAdapter(
    private var books: List<Book>,
    private val bookActionListener: BookAdaptorListener
) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    class BookViewHolder(val binding: BooklistBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = BooklistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]

        with(holder.binding) {
            // Bind data to the views using View Binding
            tvPrice.text = "Price: $${String.format("%.2f", book.price)}"
            tvAuthor.text = "Author: ${book.author}"
            tvTitle.text = book.title
            tvQuantity.text = "Quantity: ${book.quantity}"

            // Load image using Glide
            Glide.with(holder.itemView.context)
                .load(book.imageUrl) // Load the picture from URL
                .placeholder(R.drawable.ic_launcher_background) // Placeholder while loading
                .into(ivBookImage)

            // Handle button clicks for Add and Delete
            btnUpdate.setOnClickListener {
                bookActionListener.onUpdateBook(book)
            }

            btnDelete.setOnClickListener {
                bookActionListener.onDeleteBook(book)
            }
        }
    }

    override fun getItemCount(): Int = books.size

    // Update data dynamically
    fun updateData(newBooks: List<Book>) {
        books = newBooks
        notifyDataSetChanged()
    }
}
