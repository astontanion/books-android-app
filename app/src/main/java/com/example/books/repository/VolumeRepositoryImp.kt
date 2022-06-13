package com.example.books.repository

import android.util.Log
import com.example.books.model.Volume
import com.example.books.model.VolumeFilter
import com.example.books.network.ApiService
import com.google.gson.Gson
import javax.inject.Inject

class VolumeRepositoryImp @Inject constructor(
    private val apiService: ApiService<VolumeEndPoint>
): VolumeRepository {

    override suspend fun retrieveBook(filter: VolumeFilter, startIndex: Int, maxResult: Int): List<Volume> {
        if (filter.query.isNullOrBlank()) return listOf()

        val volumesDto = apiService.createService(VolumeEndPoint::class.java)
            .retrieveVolumes(
                query = filter.query,
                startIndex = startIndex,
                maxResult = maxResult.coerceIn(0, 40)
            ).items

        return volumesDto.map { it.toVolume() }
    }

}