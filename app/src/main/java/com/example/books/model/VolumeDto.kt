package com.example.books.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class VolumeDto (
    @Expose
    @SerializedName("kind")
    val kind: String,

    @Expose
    @SerializedName("id")
    val id: String,

    @Expose
    @SerializedName("volumeInfo")
    val volumeInfoDto: VolumeInfoDto?
) {
    fun toVolume(): Volume {
        return Volume(
            kind = kind,
            id = id,
            volumeInfo  = volumeInfoDto?.toVolumeInfo()
        )
    }
}
