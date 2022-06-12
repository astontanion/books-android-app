package com.example.books.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.books.model.Volume
import com.example.books.model.VolumeFilter
import com.example.books.network.DataMessage
import com.example.books.usecase.RetrievePaginatedVolumeUseCase

class SearchPagingSource constructor(
    private val volumePaginatedVolumeUseCase: RetrievePaginatedVolumeUseCase,
    private val filter: VolumeFilter,
    private val listener: (message: DataMessage) -> Unit
): PagingSource<Int, Volume>() {

    override fun getRefreshKey(state: PagingState<Int, Volume>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey ?: anchorPage?.nextKey
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Volume> {
        return volumePaginatedVolumeUseCase(
            filter = filter,
            listener = listener,
            params = params
        )
    }


}