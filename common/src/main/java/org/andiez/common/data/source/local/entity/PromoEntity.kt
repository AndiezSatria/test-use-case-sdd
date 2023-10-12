package org.andiez.common.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.andiez.common.domain.model.Formats
import org.andiez.common.domain.model.ImagePromo
import org.andiez.common.domain.model.Promo

/**
 * Created by AndiezSatria on 12/10/2023.
 */

@Entity(tableName = "PromoTable")
data class PromoEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val desc: String,
    val location: String,
    val count: Int,
    val imageId: Int,
    val imageName: String,
    val alternativeText: String,
    val caption: String,
    val smallUrl: String,
    val mediumUrl: String,
    val thumbnailUrl: String
)

fun List<PromoEntity>.toDomains(): List<Promo> = this.map { entity ->
    val formats = Formats(
        smallUrl = entity.smallUrl,
        mediumUrl = entity.mediumUrl,
        thumbnailUrl = entity.thumbnailUrl,
    )
    val imagePromo = ImagePromo(
        id = entity.imageId,
        name = entity.imageName,
        alternativeText = entity.alternativeText,
        caption = entity.caption,
        formats = formats,
    )

    Promo(
        id = entity.id,
        name = entity.name,
        desc = entity.desc,
        location = entity.location,
        count = entity.count,
        image = imagePromo,
    )

}

fun PromoEntity.toDomain(): Promo {
    val formats = Formats(
        smallUrl = this.smallUrl,
        mediumUrl = this.mediumUrl,
        thumbnailUrl = this.thumbnailUrl,
    )
    val imagePromo = ImagePromo(
        id = this.imageId,
        name = this.imageName,
        alternativeText = this.alternativeText,
        caption = this.caption,
        formats = formats,
    )

    return Promo(
        id = this.id,
        name = this.name,
        desc = this.desc,
        location = this.location,
        count = this.count,
        image = imagePromo,
    )
}
