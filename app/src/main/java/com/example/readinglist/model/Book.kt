package com.example.readinglist.model

data class Book(
    val id: Int,
    val title: String,
    val author: String,
    val readingStatus: ReadingStatus,
)

enum class ReadingStatus {
    WANT_TO_READ,
    READING,
    FINISHED,
}

fun ReadingStatus.displayName(): String = when (this) {
    ReadingStatus.WANT_TO_READ -> "Want to Read"
    ReadingStatus.READING -> "Reading"
    ReadingStatus.FINISHED -> "Finished"
}
