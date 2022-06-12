package com.example.books.model

import java.time.LocalDate

data class VolumeInfo(
    val title: String,
    val subtitle: String,
    val authors: List<String>,
    val publisher: String,
    val publishDate: LocalDate,
    val description: String,
    val imageLinks: ImageLinks
)
