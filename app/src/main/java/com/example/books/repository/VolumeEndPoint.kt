package com.example.books.repository

import com.example.books.model.VolumeResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface VolumeEndPoint {

    @GET("volumes")
    suspend fun retrieveVolumes(
        @Query("q") query: String,
        @Query("projection") projection: String = "lite",
        @Query("maxResults") maxResult: Int = 40,
        @Query("startIndex") startIndex: Int = 0
    ): VolumeResponseDto
}