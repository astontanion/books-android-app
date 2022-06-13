package com.example.books.model

data class VolumeInfo(
    val title: String?,
    val subtitle: String?,
    val authors: List<String>,
    val publisher: String?,
    val description: String?,
    val imageLinks: ImageLinks?
)
