package com.example.books.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class VolumeInfoDto(
    @Expose
    @SerializedName("title")
    val title: String,

    @Expose
    @SerializedName("subtitle")
    val subtitle: String,

    @Expose
    @SerializedName("authors")
    val authors: List<String>,

    @Expose
    @SerializedName("publisher")
    val publisher: String,

    @Expose
    @SerializedName("publishDate")
    val publishDate: LocalDate,

    @Expose
    @SerializedName("description")
    val description: String,

    @Expose
    @SerializedName("imageLinks")
    val imageLinksDto: ImageLinksDto
) {
    fun toVolumeInfo(): VolumeInfo {
        return VolumeInfo(
            title = title,
            subtitle = subtitle,
            authors = authors,
            publisher = publisher,
            publishDate = publishDate,
            description = description,
            imageLinks = imageLinksDto.toImageLinks()
        )
    }
}
