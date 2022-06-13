package com.example.books.model

import androidx.annotation.IntegerRes
import com.example.books.R

enum class FilterCategory(@IntegerRes val title: Int) {
    NONE(R.string.order_by),
    AUTHOR(R.string.filter_category_author),
    ALPHABETICALLY(R.string.filter_category_alphabetically)
}

enum class FilterOrder(@IntegerRes val title: Int) {
    NONE(R.string.sort_in),
    ASCENDING(R.string.filter_order_ascending),
    DESCENDING(R.string.filter_order_descending)
}

data class VolumeFilter(
    val query: String? = null,
    val order: FilterOrder = FilterOrder.NONE,
    val category: FilterCategory = FilterCategory.NONE
)
