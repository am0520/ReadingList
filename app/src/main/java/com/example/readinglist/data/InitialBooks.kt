package com.example.readinglist.data

import com.example.readinglist.model.Book
import com.example.readinglist.model.ReadingStatus

val initialBooks: List<Book> = listOf(
    Book(1, "The Quiet Island", "Emma Brooks", ReadingStatus.WANT_TO_READ),
    Book(2, "Beyond the Pines", "Daniel Rivers", ReadingStatus.READING),
    Book(3, "The Glass Orchard", "Nina Calder", ReadingStatus.FINISHED),
    Book(4, "Winter at Red Harbor", "Tessa Rowan", ReadingStatus.WANT_TO_READ),
    Book(5, "A Lantern in June", "Owen Finch", ReadingStatus.READING),
    Book(6, "The Paper Bridge", "Elena Moss", ReadingStatus.FINISHED),
    Book(7, "Songs for Tomorrow", "Noah Bell", ReadingStatus.WANT_TO_READ),
    Book(8, "The Amber Path", "Iris West", ReadingStatus.READING),
    Book(9, "Letters from Northwind", "Theo Quinn", ReadingStatus.FINISHED),
    Book(10, "The Last Observatory", "Maya Ellis", ReadingStatus.WANT_TO_READ)
)
