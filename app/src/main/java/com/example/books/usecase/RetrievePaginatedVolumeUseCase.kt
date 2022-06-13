package com.example.books.usecase

import androidx.paging.PagingSource
import com.example.books.model.FilterCategory
import com.example.books.model.FilterOrder
import com.example.books.model.FilterOrder.*
import com.example.books.model.Volume
import com.example.books.model.VolumeFilter
import com.example.books.network.DataMessage
import com.example.books.network.GenericFailureReason
import com.example.books.network.Operation
import com.example.books.repository.VolumeRepository
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class RetrievePaginatedVolumeUseCase @Inject constructor(
    private val volumeRepository: VolumeRepository
) {
    suspend operator fun invoke(
        filter: VolumeFilter,
        params: PagingSource.LoadParams<Int>,
        listener: (message: DataMessage) -> Unit
    ): PagingSource.LoadResult<Int, Volume> {
        try {
            listener(DataMessage.Waiting)

            if (filter.query == null) {
                listener(DataMessage.Success(operation = Operation.GET))
                return PagingSource.LoadResult.Page(
                    listOf(),
                    null,
                    null
                )
            }

            val currentKey = params.key ?: 0

            val volumes = volumeRepository.retrieveBook(
                filter = filter,
                startIndex = params.key ?: 0,
                maxResult = 40
            )

            val sortedResult = with(volumes) {
                when {
                    filter.order == ASCENDING && filter.category == FilterCategory.AUTHOR -> {
                        sortedBy { it.volumeInfo?.authors?.firstOrNull() }
                    }
                    filter.order == DESCENDING && filter.category == FilterCategory.AUTHOR -> {
                        sortedByDescending { it.volumeInfo?.authors?.firstOrNull() }
                    }
                    filter.order == ASCENDING && filter.category == FilterCategory.ALPHABETICALLY -> {
                        sortedBy { it.volumeInfo?.title }
                    }
                    filter.order == DESCENDING && filter.category == FilterCategory.ALPHABETICALLY -> {
                        sortedByDescending { it.volumeInfo?.title }
                    }
                    else -> this
                }
            }

            listener(DataMessage.Success(operation = Operation.GET))

            val resultSize = volumes.size

            val nextKey = when (volumes.isEmpty()) {
                true -> null
                false -> currentKey + resultSize
            }

            val prevKey = when (currentKey == 0) {
                true -> null
                false -> (currentKey - params.loadSize).coerceAtLeast(0)
            }

            Timber.tag("VolumeUseCase").e("prev key: %s", prevKey)
            Timber.tag("VolumeUseCase").e("key: %s", currentKey)
            Timber.tag("VolumeUseCase").e("next key: %s", nextKey)

            return PagingSource.LoadResult.Page(
                sortedResult,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: HttpException) {
            listener(
                DataMessage.Failure(
                    operation = Operation.GET,
                    reason = GenericFailureReason.CONNECTION
                )
            )

            return PagingSource.LoadResult.Page(
                listOf(),
                null,
                null
            )
        } catch (e: IOException) {
            listener(
                DataMessage.Failure(
                    operation = Operation.GET,
                    reason = GenericFailureReason.CONNECTION
                )
            )
            return PagingSource.LoadResult.Error(e)
        }
    }
}