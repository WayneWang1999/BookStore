package com.example.bookstore.data

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "books")
data class Book(
    @PrimaryKey(autoGenerate = true)
    var bookId:Long=0,
    var title:String = "",
    var author:String="",
    val price: Double = 0.0,
    val quantity: Int = 0,
    val imageUrl:String ="",
    val description:String=""
)
