package com.example.books.repository

import com.example.books.model.Volume
import com.example.books.model.VolumeDto
import retrofit2.http.GET
import retrofit2.http.Query

interface VolumeEndPoint {

    @GET("volumes")
    suspend fun retrieveVolumes(
        @Query("q") query: String,
        @Query("projection") projection: String = "lite"
    ): List<VolumeDto>
}