package com.example.readinglist.data

import androidx.room3.RoomDatabase
import androidx.sqlite.SQLiteConnection

object InitialBooksCallback: RoomDatabase.Callback() {
    override suspend fun onCreate(connection: SQLiteConnection) {
        connection.prepare(
            """
                INSERT INTO book
                (id, title, author, reading_status)
                VALUES (?,?,?,?)
            """.trimIndent()
        ).use { statement ->
            initialBooks.forEach { book ->
                statement.bindInt(1, book.id)
                statement.bindText(2, book.title)
                statement.bindText(3, book.author)
                statement.bindText(4, book.readingStatus.name)

                statement.step()
                statement.reset()
            }
        }
    }
}