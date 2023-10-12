package org.andiez.common.data.source.remote.model

import com.google.gson.annotations.SerializedName

/**
 * Created by AndiezSatria on 12/10/2023.
 */
data class ImageResponse<T>(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("alternativeText")
    val alternativeText: String? = null,
    @SerializedName("caption")
    val caption: String? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("formats")
    val formats: T? = null,
)

data class FormatResponse<T>(
    @SerializedName("small")
    val small: T? = null,
    @SerializedName("medium")
    val medium: T? = null,
    @SerializedName("thumbnail")
    val thumbnail: T? = null
)

data class ImageFormatResponse(
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("name")
    val name: String? = null,
)
