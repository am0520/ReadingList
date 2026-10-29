package com.example.readinglist.ui.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.readinglist.data.BookDao
import com.example.readinglist.data.BookEntity
import com.example.readinglist.data.initialBooks
import com.example.readinglist.data.toBook
import com.example.readinglist.model.Book
import com.example.readinglist.model.ReadingStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val bookDao: BookDao) : ViewModel() {

    val booksUiState = bookDao.getAll()
        .map<List<BookEntity>, BooksUiState> { entityList ->
            val books = entityList.map { it.toBook() }
            BooksUiState.Success(books)
        }
        .catch { emit(BooksUiState.Error) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = BooksUiState.Loading
        )
    fun updateReadingStatus(
        book: Book,
        status: ReadingStatus
    ) {
        viewModelScope.launch {
            bookDao.update(
                BookEntity(
                    id = book.id,
                    title = book.title,
                    author = book.author,
                    readingStatus = status
                )
            )
        }
    }
}

sealed interface BooksUiState {
    data class Success(val books: List<Book>) : BooksUiState
    data object Loading : BooksUiState
    data object Error : BooksUiState
}
