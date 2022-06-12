package com.example.books.repository

import com.example.books.model.VolumeFilter
import com.example.books.model.Volume
import com.example.books.network.ApiService
import javax.inject.Inject

class VolumeRepositoryImp @Inject constructor(
    val apiService: ApiService<VolumeEndPoint>
): VolumeRepository {

    override suspend fun retrieveBook(filter: VolumeFilter): List<Volume> {
        val volumesDto = apiService.createService(VolumeEndPoint::class.java)
            .retrieveVolumes(
                query = filter.query
            )

        return volumesDto.map { it.toVolume() }
    }

}