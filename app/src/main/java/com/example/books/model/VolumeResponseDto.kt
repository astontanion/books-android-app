package com.example.books.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class VolumeResponseDto(
    @Expose
    @SerializedName("kind")
    val kind: String,

    @Expose
    @SerializedName("items")
    val items: List<VolumeDto>,

    @Expose
    @SerializedName("totalItems")
    val totalItems: Int
)
