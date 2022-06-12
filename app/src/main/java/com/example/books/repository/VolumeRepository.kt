package com.example.books.repository

import com.example.books.model.Volume
import com.example.books.model.VolumeFilter

interface VolumeRepository {
    suspend fun retrieveBook(filter: VolumeFilter): List<Volume>
}