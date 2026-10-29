package com.example.readinglist.data

import androidx.room3.Database
import androidx.room3.RoomDatabase

@Database(
    entities = [BookEntity::class],
    version = 1,
    exportSchema = false
)
abstract class BooksDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
}