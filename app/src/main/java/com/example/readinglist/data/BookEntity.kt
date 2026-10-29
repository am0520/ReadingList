package com.example.readinglist.data

import androidx.room3.ColumnInfo
import androidx.room3.Entity
import androidx.room3.PrimaryKey
import com.example.readinglist.model.Book
import com.example.readinglist.model.ReadingStatus

@Entity(tableName = "book")
data class BookEntity(
    @PrimaryKey
    val id : Int,
    val title: String,
    val author:String,
    @ColumnInfo(name = "reading_status")
    val readingStatus: ReadingStatus

)

fun BookEntity.toBook() = Book(
    id =  id,
    title = title,
    author = author,
    readingStatus = readingStatus
)