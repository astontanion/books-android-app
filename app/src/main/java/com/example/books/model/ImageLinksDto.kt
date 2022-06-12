package com.example.books.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ImageLinksDto(
    @Expose
    @SerializedName("smallThumbnail")
    val smallThumbnail: String,

    @Expose
    @SerializedName("thumbnail")
    val thumbnail: String
) {
    fun toImageLinks(): ImageLinks {
        return ImageLinks(
            smallThumbnail = smallThumbnail,
            thumbnail = thumbnail
        )
    }
}
