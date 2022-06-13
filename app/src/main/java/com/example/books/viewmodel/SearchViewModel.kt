package com.example.books.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.books.model.FilterCategory
import com.example.books.model.FilterOrder
import com.example.books.model.VolumeFilter
import com.example.books.network.DataMessage
import com.example.books.repository.SearchPagingSource
import com.example.books.usecase.RetrievePaginatedVolumeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import retrofit2.http.Query
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val volumeUseCase: RetrievePaginatedVolumeUseCase
): ViewModel() {

    private val _message = MutableStateFlow<DataMessage>(DataMessage.None)
    val message: SharedFlow<DataMessage> = _message

    private val _volumeFilter = MutableStateFlow(VolumeFilter())
    val volumeFilter: StateFlow<VolumeFilter> = _volumeFilter
    val volumePagingItems = _volumeFilter.flatMapLatest { filter ->
        Pager(
            PagingConfig(
                pageSize = 12,
                maxSize = 36,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                SearchPagingSource(
                    filter = filter,
                    volumePaginatedVolumeUseCase = volumeUseCase,
                    listener = { msg -> _message.value = msg }
                )
            }
        ).flow.cachedIn(viewModelScope)
    }

    fun onQueryChange(query: String) {
        _volumeFilter.value = _volumeFilter.value.copy(
            query = query
        )
    }

    fun onFilterCategoryChange(category: FilterCategory) {
        _volumeFilter.value = _volumeFilter.value.copy(
            category = category
        )
    }

    fun onFilterOrderChange(order: FilterOrder) {
        _volumeFilter.value = _volumeFilter.value.copy(
            order = order
        )
    }
}