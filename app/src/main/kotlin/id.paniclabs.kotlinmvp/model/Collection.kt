package id.paniclabs.kotlinmvp.model

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * @author ali@pergikuliner
 * @created 5/23/17.
 * @project BelajarKotlinMVP.
 */
data class Collection (
        @SerializedName("id") val id: Int? = null,
        @SerializedName("published_at") val publishedAt: Date? = null,
        @SerializedName("title") val title: String? = null,
        @SerializedName("curated") val curated: Boolean? = null,
        @SerializedName("user") val user: User? = null,
        @SerializedName("links") val links: Links? = null
        )