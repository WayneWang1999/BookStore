package com.example.bookstore.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Book::class],version=2)
abstract class BookDatabase:RoomDatabase(){
    abstract fun bookDao():BookDao

    companion object {
        @Volatile
        private var INSTANCE: BookDatabase? = null

        fun getInstance(context: Context): BookDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BookDatabase::class.java,
                    "book_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}