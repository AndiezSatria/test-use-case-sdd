package org.andiez.common.domain.model

/**
 * Created by AndiezSatria on 12/10/2023.
 */
data class ImagePromo(
    val id: Int,
    val name: String,
    val alternativeText: String,
    val caption: String,
    val formats: Formats,
)

data class Formats(
    val smallUrl: String,
    val mediumUrl: String,
    val thumbnailUrl: String
)