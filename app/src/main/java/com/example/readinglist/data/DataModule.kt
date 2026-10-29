package com.example.readinglist.data

import android.content.Context
import androidx.room3.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideBooksDatabase(
        @ApplicationContext
        context: Context
    ) = Room.databaseBuilder<BooksDatabase>(context, "books")
        .addCallback(InitialBooksCallback)
        .build()

    @Provides
    fun provideBookDao(
        database: BooksDatabase,
    ) = database.bookDao()
}