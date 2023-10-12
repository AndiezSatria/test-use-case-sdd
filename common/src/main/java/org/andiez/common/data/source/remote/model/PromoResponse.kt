package org.andiez.common.data.source.remote.model

import com.github.ajalt.timberkt.d
import com.google.gson.annotations.SerializedName
import org.andiez.common.domain.model.Formats
import org.andiez.common.domain.model.ImagePromo
import org.andiez.common.domain.model.Promo

/**
 * Created by AndiezSatria on 12/10/2023.
 */
data class PromoResponse<T>(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("nama")
    val name: String? = null,
    @SerializedName("desc")
    val desc: String? = null,
    @SerializedName("lokasi")
    val location: String? = null,
    @SerializedName("count")
    val count: Int? = null,
    @SerializedName("img")
    val img: T? = null,
)

fun List<PromoResponse<ImageResponse<FormatResponse<ImageFormatResponse>>>>.toDomains(): List<Promo> = this.map { response ->
    d { "URL : " + response.img?.formats?.small?.url.toString() }
    val formats = Formats(
        smallUrl = response.img?.formats?.small?.url ?: "",
        mediumUrl = response.img?.formats?.small?.url ?: "",
        thumbnailUrl = response.img?.formats?.small?.url ?: "",
    )
    val imagePromo = ImagePromo(
        id = response.img?.id ?: 0,
        name = response.img?.name ?: "",
        alternativeText = response.img?.alternativeText ?: "",
        caption = response.img?.caption ?: "",
        formats = formats,
    )

    Promo(
        id = response.id ?: 0,
        name = response.name ?: "",
        desc = response.desc ?: "",
        location = response.location ?: "",
        count = response.count ?: 0,
        image = imagePromo,
    )
}
