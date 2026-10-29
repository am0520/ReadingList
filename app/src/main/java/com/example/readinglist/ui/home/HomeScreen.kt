package com.example.readinglist.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.plus
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.readinglist.core.theme.AppTheme
import com.example.readinglist.data.initialBooks
import com.example.readinglist.model.Book
import com.example.readinglist.model.ReadingStatus
import com.example.readinglist.model.displayName

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val booksUiState by viewModel.booksUiState.collectAsStateWithLifecycle()

    HomeScreen(
        booksUiState = booksUiState,
        onStatusChange = { book, status ->
            viewModel.updateReadingStatus(book = book, status = status)
        },
        modifier = modifier
    )
}

@Composable
private fun HomeScreen(
    booksUiState: BooksUiState,
    modifier: Modifier = Modifier,
    onStatusChange: (Book, ReadingStatus) -> Unit = { _, _ -> },
) {
    when (booksUiState) {
        is BooksUiState.Loading -> {}

        is BooksUiState.Success -> BooksList(
            books = booksUiState.books,
            onStatusChange = onStatusChange,
            modifier = modifier
        )

        is BooksUiState.Error -> {}
    }
}

@Composable
private fun BooksList(
    books: List<Book>,
    modifier: Modifier = Modifier,
    onStatusChange: (Book, ReadingStatus) -> Unit = { _, _ -> },
) {
    val contentPadding =
        WindowInsets.safeDrawing.asPaddingValues() + PaddingValues(8.dp)

    LazyColumn(
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = modifier
    ) {
        items(
            items = books,
            key = { it.id },
        ) { book ->
            BookItem(
                book = book,
                onStatusChange = { status ->
                    onStatusChange(book, status)
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
private fun BookItem(
    book: Book,
    modifier: Modifier = Modifier,
    onStatusChange: (ReadingStatus) -> Unit = { },
) {
    Card(modifier = modifier) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = book.title,
                style = MaterialTheme.typography.titleMedium,
            )
            Text(
                text = book.author,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            ReadingStatusDropdown(
                readingStatus = book.readingStatus,
                onStatusChange = onStatusChange,
            )
        }
    }
}

@Composable
private fun ReadingStatusDropdown(
    readingStatus: ReadingStatus,
    modifier: Modifier = Modifier,
    onStatusChange: (ReadingStatus) -> Unit = {},
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        OutlinedButton(
            onClick = { expanded = true }
        ) {
            Text(readingStatus.displayName())
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            ReadingStatus.entries.forEach { status ->
                DropdownMenuItem(
                    text = { Text(status.displayName()) },
                    onClick = {
                        onStatusChange(status)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    AppTheme {
        HomeScreen(
            booksUiState = BooksUiState.Success(books = initialBooks),
        )
    }
}
