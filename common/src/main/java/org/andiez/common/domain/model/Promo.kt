package org.andiez.common.domain.model

import org.andiez.common.data.source.local.entity.PromoEntity

/**
 * Created by AndiezSatria on 12/10/2023.
 */
data class Promo(
    val id: Int,
    val name: String,
    val desc: String,
    val location: String,
    val count: Int,
    val image: ImagePromo,
)

fun List<Promo>.toEntities(): List<PromoEntity> = this.map {
    PromoEntity(
        id = it.id,
        name = it.name,
        desc = it.desc,
        location = it.location,
        count = it.count,
        imageId = it.image.id,
        imageName = it.image.name,
        alternativeText = it.image.alternativeText,
        caption = it.image.caption,
        smallUrl = it.image.formats.smallUrl,
        mediumUrl = it.image.formats.mediumUrl,
        thumbnailUrl = it.image.formats.thumbnailUrl,
    )
}