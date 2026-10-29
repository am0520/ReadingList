package com.example.readinglist.data

import androidx.room3.Dao
import androidx.room3.Query
import androidx.room3.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Query("SELECT * FROM book")
    fun getAll(): Flow<List<BookEntity>>

    @Update
    suspend fun update(book: BookEntity)
}